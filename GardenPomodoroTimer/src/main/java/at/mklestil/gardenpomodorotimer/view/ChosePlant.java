package at.mklestil.gardenpomodorotimer.view;

import at.mklestil.gardenpomodorotimer.model.AppModel;
import at.mklestil.gardenpomodorotimer.model.ImageViewWithPath;
import at.mklestil.gardenpomodorotimer.model.TagDAO;
import at.mklestil.gardenpomodorotimer.model.Tags;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * ChosePlant View, a view to chose a plant.
 */

public class ChosePlant {
    private VBox root;
    private String chose = "/images/start/start.png";
    private int timeChose = 25;
    private String tagChose = "learn";
    private ArrayList<String> tagsList = new ArrayList<>();
    private ArrayList<String> plantList;
    private Image plantImage;
    private ImageView choseImageView;
    private ArrayList<ImageViewWithPath> listOfImageViews = new ArrayList<>();
    private ArrayList<TimesButton> times;
    private Slider timeSlider =  new Slider(1.0, 120.0, timeChose);
    private StringProperty chosenStringProperty = new SimpleStringProperty("" + timeChose);
    private HBox tagsContainer = new HBox(5);

    private Button startBtn;

    public ChosePlant(){
        root = new VBox();
        FlowPane plantContainer = getPlantContainer();
        VBox fokusTimeContainer = getFocusTimeContainer();
        tagsContainer = getTagsContainer(tagsList);
        FlowPane choseContainer = getChoseContainer();

        root.getChildren().add(plantContainer);
        root.getChildren().add(fokusTimeContainer);
        root.getChildren().add(tagsContainer);
        root.getChildren().add(choseContainer);

    }

    private FlowPane getPlantContainer(){
        FlowPane plantContainer = new FlowPane();
        plantList = new ArrayList<>();
        //Todo:: dynamic plants form db
        plantList.add("/images/tree/6_tree.png");
        plantList.add("/images/tree/fall.png");
        plantList.add("/images/tree/greenLeaves.png");
        plantList.add("/images/tree/pointedTree.png");
        plantList.add("/images/tree/roundTree.png");


        for(String name : plantList){
            InputStream inputStream1 = getClass().getResourceAsStream(name);
            Image plantImg1 = new Image(inputStream1);
            ImageView plantView1 = new ImageView(plantImg1);
            plantView1.setFitWidth(50);
            plantView1.setFitHeight(50);
            listOfImageViews.add(new ImageViewWithPath(plantView1, name));
            plantContainer.getChildren().add(plantView1);
        }

        return plantContainer;
    }

    private FlowPane getChoseContainer (){
        FlowPane choseContainer = new FlowPane();
        Label timeLabel = new Label("Test");
        timeLabel.textProperty().bind(chosenStringProperty);
        Label tagLabel = new Label(tagChose);
        timeLabel.getStyleClass().add("label");
        tagLabel.getStyleClass().add("label");
        InputStream inputStream = getClass().getResourceAsStream(AppModel.getInstance().getSelectedPlant());
        plantImage = new Image(inputStream);
        choseImageView = new ImageView(plantImage);
        choseImageView.setFitWidth(50);
        choseImageView.setFitHeight(50);
        choseImageView.getStyleClass().add("image-view");


        choseContainer.getStyleClass().add("root");
        choseContainer.getChildren().add(timeLabel);
        choseContainer.getChildren().add(choseImageView);
        choseContainer.getChildren().add(tagLabel);

        startBtn = new Button("start");
        startBtn.getStyleClass().add("start-button");
        choseContainer.getChildren().add(startBtn);

        return choseContainer;
    }

    /**
     * Methode give me buttons with time to chose
     * @return
     */
    private VBox getFocusTimeContainer (){
        VBox timesContainer = new VBox();
        FlowPane buttonsContainer = new FlowPane();

        times = new ArrayList<TimesButton>();
        times.add(new TimesButton("10", 10));
        times.add(new TimesButton("25", 25));
        times.add(new TimesButton("30", 30));
        times.add(new TimesButton("50", 50));
        times.add(new TimesButton("60", 60));
        times.add(new TimesButton("90",90));
        times.add(new TimesButton("120",120));
        for(TimesButton btn : times){
            buttonsContainer.getChildren().add(btn);
        }
        Label tempLabel = new Label();
        tempLabel.textProperty().bind(chosenStringProperty);
        buttonsContainer.getStyleClass().add("container");
        timesContainer.getChildren().add(buttonsContainer);
        timesContainer.getChildren().add(new Separator());
        timesContainer.getChildren().add(timeSlider);
        timesContainer.getChildren().add(tempLabel);
        timesContainer.getStyleClass().add("container");

        return timesContainer;
    }

    private HBox getTagsContainer(ArrayList<String> tagsList){
        tagsContainer.getChildren().clear();
        System.out.println("Tags List Size: " + tagsList.size());
        if(tagsList.size() == 0 || tagsList == null){
            tagsContainer.getChildren().add(new TagsLabel("no tag"));
        }else{
            for(String tags : tagsList){
                TagsLabel temp = new TagsLabel(tags);
                tagsContainer.getChildren().add(temp);
            }
        }
        tagsContainer.getStyleClass().add("container");
        return tagsContainer;
    }

    public void updateTagList(ArrayList<String> tagsList){
        //Update List
        getTagsContainer(tagsList);
    }

    public VBox getRoot() {
        return root;
    }

    public Button getStartBtn(){
        return startBtn;
    }

    public String getChose() {
        return chose;
    }

    /**
     * Set Chose to update ui / image
     * @param chose
     */
    public void setChoseUpdateImage(String chose) {
        this.chose = chose;
        InputStream inputStream = getClass().getResourceAsStream(chose);
        Image tempImage = new Image(inputStream);
        choseImageView.setImage(tempImage);

    }

    public int getTimeChose() {
        return timeChose;
    }

    public void setTimeChose(int timeChose) {
        this.timeChose = timeChose;
        chosenStringProperty.set("" + this.timeChose);
    }

    public String getTagChose() {
        return tagChose;
    }

    public void setTagChose(String tagChose) {
        this.tagChose = tagChose;
    }

    public ArrayList<ImageViewWithPath> getListOfImageViews() {
        return listOfImageViews;
    }

    public ArrayList<TimesButton> getTimes() {
        return times;
    }

    public Slider getTimeSlider() {
        return timeSlider;
    }
}

