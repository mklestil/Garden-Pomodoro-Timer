package at.tintildev.gardenpomodorotimer.ui.controller;

import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import at.tintildev.gardenpomodorotimer.tags.TagSelectionService;
import at.tintildev.gardenpomodorotimer.ui.view.StartWindow;
import at.tintildev.gardenpomodorotimer.ui.view.TagAddDialog;
import at.tintildev.gardenpomodorotimer.ui.view.TagSelectionDialog;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StartWindowController {
    private final StartWindow view;
    private int workTime = 25 * 60; // 25 Minuten
    private int remainingTime = workTime;
    private Timeline timeline;
    private String formatTime;
    private int progress = 0;
    private MainController mainController;
    private ApplicationState model;
    private boolean switchCheck = true;
    private String sessionObject = "test";



    public StartWindowController(StartWindow view, MainController sManger) {
        this.view = view;
        mainController = sManger;
        model = mainController.getModel();
    }

    private void addData() {
        workTime = model.getTime() * 60;
        remainingTime = workTime;
        sessionObject = model.getSelectedPlant();
    }

    public void initialize(){
        formatTime = view.getTimeLabel().getText();
        view.getStartButton().setOnAction(e -> startTimer());
        view.getBreakButton().setOnAction(e -> pauseTimer());
        view.getResetButton().setOnAction(e -> resetTimer());
        view.getSelectTagButton().setOnMouseClicked(event -> showTagSelectionDialog());
        view.getAddTagsButton().setOnAction(event -> addTagDialog());
        view.getSettingsTagBtn().setOnAction(event -> editTags());

        view.getPlus().setOnAction(e -> plusTime());
        view.getMinus().setOnAction(e -> minusTime());
        view.getSelectTagButton().setText(model.getTag());
        //Todo:: InitialTrees with Data from DB, dynamic growth state
        view.initialTrees(new ArrayList<String>());

        //Add Data
        addData();

        //Switch Scene Event Management
        switchScene();
    }

    /**
     * Method to mangage Events to switch the scene
     */
    private void switchScene() {
        // create EventHanlder, to check switchCheck
        EventHandler<javafx.scene.input.MouseEvent> switchHandler = event -> {
            if (switchCheck) {
                mainController.switchTo("chose");
            } else {
                System.out.println("Scene changes not allowed!");
            }
        };
        view.getPlantImageView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, switchHandler);
        view.getBtnSettings().setOnAction(event -> {
            mainController.switchTo("settings");
        });
        view.getBtnChart().setOnAction(event -> {
            mainController.switchTo("chart");
        });

    }

    private void updateTimer() {
        if (remainingTime > 0) {
            remainingTime--;
            double progressTime = (double) (workTime - remainingTime) / workTime;
            view.getProgress().setProgress(progressTime);
            view.getTimeLabel().setText(formatTime(remainingTime));

            //Change Images
            if (workTime > 0) {  // Fortschritt erhöhen
                progress = (int) (progressTime * 100);
                // calculate image stage with the progress
                int stage = progress / 20;  // 5 images, at all 20%
                //System.out.println("% : " + progress / 20);

                // check stage and image length
                if (stage >= 0 && stage < view.getPlantStages().length) {
                    //set ImageView image stage
                    //Todo:: set DB Data (arraylist ?) imagepaths
                    view.getPlantImageView().setImage(view.getPlantStages()[stage]);
                }
            }
        }
        else if (remainingTime == 0){
            // Finish Timer
            view.getStatus().setText("Great Job!");
            mainController.saveSession(workTime / 60, sessionObject, model.getTag()); // save data in db
            timeline.stop(); // stop timeline
            resetButtonsToStart();
        }else {
            // Timer-Ende
            pauseTimer();
        }
    }

    private void resetButtonsToStart(){
        view.getStartButton().setDisable(false);
        view.getBreakButton().setDisable(true);
    }

    public void startTimer(){
        if (timeline == null || timeline.getStatus() == Timeline.Status.STOPPED) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            view.getStatus().setText("Do not give up!");
            view.getPlantImageView().setImage(view.getPlantStages()[0]);
            view.getStartButton().setDisable(true);
            view.getBreakButton().setDisable(false);
        }else {
            //Pause
            timeline.play();
            view.getStatus().setText("Pause ? Do not give up!");
        }
        switchCheck = false;
    }

    public void pauseTimer() {
        if (timeline != null) {
            timeline.pause();
            view.getStatus().setText("Wait");
        }
        view.getBreakButton().setDisable(true);
        view.getStartButton().setDisable(false);
    }

    public void resetTimer() {
        if (timeline != null) {
            timeline.stop();
            view.getStatus().setText("Ready to start!");
        }
        remainingTime = workTime;  // Reset remainingTime to workTime
        progress = 0;
        view.getTimeLabel().setText(formatTime(remainingTime)); //reset
        view.getProgress().setProgress(progress); // reset progress
        view.getPlantImageView().setImage(view.getPlantStages()[0]); //reset image
        switchCheck = true; //allaow switch scene
        resetButtonsToStart();
    }

    // Tags

    /** Method to show dialog to add new tag
     *
     */
    public void addTagDialog(){
        TagAddDialog addTagDialog = new TagAddDialog();
        Optional<String> result = addTagDialog.showAndWait();
        result.ifPresent(value -> mainController.saveTag(value));

    }

    /** Method to show dialog to select tag
     *
     */
    private void showTagSelectionDialog() {
        TagSelectionService tagService = new TagSelectionService(mainController, model);
        List<String> tags = tagService.getAvailableTags();
        TagSelectionDialog tagDialog = new TagSelectionDialog(tags);


        tagDialog.show(tags).ifPresent(selectedTag -> {
            model.setTag(selectedTag);
            view.getSelectTagButton().setText(selectedTag);
        });
    }

    private void editTags(){
        System.out.println("Edit Tags ...");
        TextInputDialog dialog = new TextInputDialog(model.getTag());
        dialog.showAndWait();
        String tempTag = dialog.getResult();
        if (tempTag != null && !tempTag.trim().isEmpty()) {
            mainController.saveEditTag(model.getTag(), tempTag);
            view.getSelectTagButton().setText(model.getTag());
        }




    }

    public void plusTime() {
        workTime = workTime + 60; // 1 min;
        remainingTime = workTime;
        model.setTime(remainingTime);
        view.getTimeLabel().setText(formatTime(model.getTime()));
    }
    public void minusTime() {
        // no negativ time
        if (workTime > 60) {
            workTime = workTime - 60; // 1 min;
            remainingTime = workTime;
            model.setTime(remainingTime);
            view.getTimeLabel().setText(formatTime(model.getTime()));
        }
    }

    public String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        formatTime = String.format("%02d:%02d", minutes, secs);
        return formatTime;
    }

    public void setWorkTime(int time){
        workTime = time;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void updateView(){
        System.out.println("Scene switch to Start, update view!");
        view.getSelectTagButton().setText(model.getTag());
        setWorkTime(model.getTime() * 60);
        remainingTime = workTime;
        view.getTimeLabel().setText(formatTime(remainingTime));
        view.updateColor();
        view.updateImage(); // update image
    }
}
