package at.mklestil.gardenpomodorotimer.view;

import at.mklestil.gardenpomodorotimer.model.AppModel;
import at.mklestil.gardenpomodorotimer.service.LanguageManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartWindow {
    private BorderPane root;
    private Label status;
    private Label selectedTagLabel;
    private Button settingsTagBtn;
    private Button plus = new Button("+");
    private Button minus = new Button("-");
    private ProgressBar progress;
    private Button selectTagButton = new Button("");
    private Button startButton;
    private Button breakButton;
    private Button resetButton;
    private Label timeLabel = new Label("25:00");
    private final Image[] plantStages = new Image[5];
    private ImageView plantImageView;
    private Button btnSettings;
    private Button btnChart;
    private Button addTagsButton = new Button("+");


    /** * Constructor for StartWindow.
     * Initializes the UI components and layout.
     */

    public StartWindow() {
        root = new BorderPane();
        initialize();
    }

    /** Initializes the UI components and layout of the StartWindow. */

    private void initialize() {
        //Text
        status = new Label(LanguageManager.getInstance().getBundle().getString("status"));
        startButton = new Button(LanguageManager.getInstance().getBundle().getString("startButton"));
        breakButton = new Button(LanguageManager.getInstance().getBundle().getString("breakButton"));
        resetButton = new Button(LanguageManager.getInstance().getBundle().getString("resetButton"));

        //Timer
        VBox vBox = new VBox(5);
        HBox hbox = new HBox(5);
        startTree();
        plantImageView = new ImageView(plantStages[4]);
        plantImageView.setFitWidth(150);
        plantImageView.setFitHeight(150);
        progress = new ProgressBar();
        hbox.getChildren().addAll(plus, progress, minus);
        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(startButton, breakButton, resetButton);

        //Tags
        HBox tagsBox = new HBox(5);
        selectedTagLabel = new Label(LanguageManager.getInstance().getBundle().getString("selectedTag"));

        tagsBox.getChildren().add(selectedTagLabel);
        tagsBox.getChildren().add(selectTagButton);
        HBox tagsBoxChange = new HBox(5);
        tagsBoxChange.getChildren().add(addTagsButton);
        tagsBoxChange.getChildren().add(loadTagEditBtn()); //load edit tags btn and add to hbox

        vBox.getChildren().addAll(plantImageView, tagsBox, tagsBoxChange, hbox, buttonBox);

        //Status and Time
        BorderPane topPane = new BorderPane();
        topPane.setLeft(loadChartBtn());
        topPane.setCenter(status);
        topPane.setRight(loadSettingsBtn());

        //updateTexts
        updateTexts();
        LanguageManager.getInstance().addLanguageChangeListener(this::updateTexts);

        //Add root
        root.setTop(topPane);
        root.setCenter(vBox);
        root.setBottom(timeLabel);

        //Center Elements
        hbox.setStyle("-fx-alignment: center;");
        buttonBox.setStyle("-fx-alignment: center;");
        vBox.setStyle("-fx-alignment: center;");
        tagsBox.setStyle("-fx-alignment: center;");
        tagsBoxChange.setStyle("-fx-alignment: center;");
        BorderPane.setAlignment(status, Pos.CENTER);
        BorderPane.setAlignment(timeLabel, Pos.CENTER);

        // create Background
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.web(AppModel.getInstance().getBackgroundColor()),  // color
                null,  // Ecken (CornerRadii)
                null   // Rand (Insets)
        );

        // CSS Classes
        selectTagButton.getStyleClass().add("tagLabel");
        status.getStyleClass().add("statusLabel");
        timeLabel.getStyleClass().add("timeLabel");

        // set Background
        root.setBackground(new Background(backgroundFill));

        // disable
        breakButton.setDisable(true);

        //add Listener
        LanguageManager.getInstance().addLanguageChangeListener(this::updateTexts);
    }

    public void updateTexts(){
        ResourceBundle bundle = LanguageManager.getInstance().getBundle();
        status.setText(bundle.getString("status"));
        startButton.setText(bundle.getString("startButton"));
        breakButton.setText(bundle.getString("breakButton"));
        resetButton.setText(bundle.getString("resetButton"));
        selectedTagLabel.setText(bundle.getString("selectedTag"));


    }

    public void updateColor(){
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.web(AppModel.getInstance().getBackgroundColor()),  // color
                null,  // Ecken (CornerRadii)
                null);   // Rand (Insets)
        root.setBackground(new Background(backgroundFill));
    }

    public void updateImage(){
        String pathString = AppModel.getInstance().getSelectedPlant();
        InputStream inputStream = getClass().getResourceAsStream(pathString);
        if(inputStream != null){
            //Check Image is correct
            Image image = new Image(inputStream);
            if(!image.isError()){
                plantStages[4] = image;
            }else {
                System.out.println("Error loading image: " + pathString);
            }
        }else{
            System.out.println("Image file not found.");
        }

    }

    public void startTree(){
        InputStream inputStream = getClass().getResourceAsStream("/images/start/start.png");
        if(inputStream != null){
            //Check Image is correct
            Image image = new Image(inputStream);
            if(!image.isError()){
                plantStages[4] = image;
            }else {
                System.out.println("Error loading image: " + "/images/start/start.png");
            }
        }else{
            System.out.println("Image file not found.");
        }
    }

    public Button loadSettingsBtn(){
        btnSettings = new Button("");
        InputStream inputStream = getClass().getResourceAsStream("/images/start/settings.png");
        if(inputStream != null){
            //Check Image is correct
            Image image = new Image(inputStream);
            if(!image.isError()){
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(24);
                imageView.setFitWidth(24);
                btnSettings.setGraphic(imageView);
                btnSettings.setStyle("-fx-background-color: transparent;");
               //Todo: Hover effect
            }else {
                System.out.println("Error loading image: " + "/images/start/settings.png");
            }
        }else{
            System.out.println("Image file not found.");
        }
        return btnSettings;
    }

    // Todo: load Btns into a separate class
    public Button loadChartBtn(){
        btnChart = new Button("");
        InputStream inputStream = getClass().getResourceAsStream("/images/start/chart.png");
        if(inputStream != null){
            //Check Image is correct
            Image image = new Image(inputStream);
            if(!image.isError()){
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(24);
                imageView.setFitWidth(24);
                btnChart.setGraphic(imageView);
                btnChart.setStyle("-fx-background-color: transparent;");
            }else {
                System.out.println("Error loading image: " + "/images/start/chart.png");
            }
        }else{
            System.out.println("Image file not found.");
        }
        return btnChart;
    }

    public Button loadTagEditBtn(){
        settingsTagBtn = new Button("");
        InputStream inputStream = getClass().getResourceAsStream("/images/start/pen.png");
        if(inputStream != null){
            //Check Image is correct
            Image image = new Image(inputStream);
            if(!image.isError()){
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(24);
                imageView.setFitWidth(24);
                settingsTagBtn.setGraphic(imageView);
                settingsTagBtn.setStyle("-fx-background-color: white;");
                settingsTagBtn.setStyle("-fx-hover-background-color: #e0e0e0");
            }else {
                System.out.println("Error loading image: " + "/images/start/pen.png");
            }
        }else{
            System.out.println("Image file not found.");
        }
        return settingsTagBtn;
    }

    public void initialTrees(ArrayList<String> listImagePath){
        ArrayList<String> imagePath = new ArrayList<>();
        imagePath = listImagePath;
        if(listImagePath.size() == 0){
            //Image Path, in the future delete hard codes
            imagePath.add("/images/tree/1_tree.png");
            imagePath.add("/images/tree/2_tree.png");
            imagePath.add("/images/tree/3_tree.png");
            imagePath.add("/images/tree/4_tree.png");
            imagePath.add("/images/start/start.png");
        }
        // Add img plantStages
        for (int i = 0; i < imagePath.size(); i++){
            //Check Stream Path
            InputStream inputStream = getClass().getResourceAsStream(imagePath.get(i));
            if(inputStream != null){
                //Check Image is correct
                Image image = new Image(inputStream);
                if(!image.isError()){
                    plantStages[i] = image;
                }else {
                    System.out.println("Error loading image: " + imagePath.get(i));
                }
            }else{
                System.out.println("Image file not found.");
            }
        }

    }

    public BorderPane getRoot() {
        return root;
    }

    public Label getStatus() {
        return status;
    }

    public Button getPlus() {
        return plus;
    }

    public Button getMinus() {
        return minus;
    }

    public ProgressBar getProgress() {
        return progress;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getBreakButton() {
        return breakButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Image[] getPlantStages() {
        return plantStages;
    }

    public ImageView getPlantImageView() {
        return plantImageView;
    }
    public Button getSettingsTagBtn() {
        return settingsTagBtn;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }
    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public void setPlantImageView(ImageView plantImageView) {
        this.plantImageView = plantImageView;
    }

    public Button getSelectTagButton() {
        return selectTagButton;
    }

    public Button getBtnSettings() {
        return btnSettings;
    }

    public Button getBtnChart() {
        return btnChart;
    }

    public Button getAddTagsButton() {
        return addTagsButton;
    }
}
