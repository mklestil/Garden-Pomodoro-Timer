package at.mklestil.gardenpomodorotimer.control;

import at.mklestil.gardenpomodorotimer.model.AppModel;
import at.mklestil.gardenpomodorotimer.model.ImageViewWithPath;
import at.mklestil.gardenpomodorotimer.view.ChosePlant;
import at.mklestil.gardenpomodorotimer.view.TimesButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class ChosePlantController {
    private final ChosePlant view;
    private final MainController mainController;
    private String chosenPlant = "";
    private AppModel model;


    public ChosePlantController(ChosePlant viewChosePlant, MainController scene){
        view = viewChosePlant;
        mainController = scene;
        model = mainController.getModel();
        chosenPlant = model.getSelectedPlant();
        loadTags();
        registerEventHandler();
    }

    private void registerEventHandler() {
        imageViewHandler();
        startButtonHandler();
        timeBtnHandler();
        timeSliderHandler();
        tagsBtnHandler();
    }

    private void tagsBtnHandler() {
        view.getTagsButtons().forEach(btn -> {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Tag button clicked: " + btn.getTagName());
                    view.setTagChose(btn.getTagName());
                }
            });
        });
    }

    private void timeBtnHandler() {
        for(TimesButton btn : view.getTimes()){
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    view.setTimeChose(btn.getTimeValue());
                }
            });
        }
    }

    private void timeSliderHandler(){
        view.getTimeSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                view.setTimeChose(newValue.intValue());
            }
        });
    }

    private void imageViewHandler(){
        ArrayList<ImageViewWithPath> images = view.getListOfImageViews();
        System.out.println("Size of images: " + images.size());
        for(ImageViewWithPath image: images){
            image.getImageView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                chosenPlant = image.getPath();
                view.setChoseUpdateImage(chosenPlant);
                System.out.println("Chose set: " + chosenPlant);
            });
        }
    }

    private void startButtonHandler(){
        view.getStartBtn().addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            model.setSelectedPlant(chosenPlant);
            model.setTag(view.getTagChose());
            model.setTime(view.getTimeChose());
            System.out.println("chosen values: " + view.getChose() + " " +  view.getTagChose()+ " " + view.getTimeChose());
            mainController.switchTo("start");
        });
    }

    private void loadTags(){
        mainController.loadAllTags();
        view.updateTagList(model.getTagsList());
    }


}
