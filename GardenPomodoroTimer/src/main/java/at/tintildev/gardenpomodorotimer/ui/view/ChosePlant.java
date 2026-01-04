package at.tintildev.gardenpomodorotimer.ui.view;

import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * ChosePlant View, a view to chose a plant.
 */

public class ChosePlant {
    private ScrollPane root;
    private String chose = "/images/start/start.png";
    private int timeChose = 25;
    private String tagChose = "learn";
    private StringProperty chosenTagProperty = new SimpleStringProperty("" + tagChose);
    private ArrayList<String> tagsList = new ArrayList<>();
    private ArrayList<String> plantList;
    private Image plantImage;
    private ImageView choseImageView;
    private ArrayList<ImageViewWithPath> listOfImageViews = new ArrayList<>();
    private ArrayList<TimesButton> times;
    private Slider timeSlider =  new Slider(1.0, 120.0, timeChose);
    private StringProperty chosenStringProperty = new SimpleStringProperty("" + timeChose);
    private FlowPane tagsContainer = new FlowPane();
    private ArrayList<TagsBtn> tagsButtons = new ArrayList<>();
    private int appWidth = 280;
    private int appHight = 420;

    private Button startBtn;

    public ChosePlant(){
        initialize();
    }

    /** * Initialize the view
     */
    public void initialize() {
        // Todo: Scroll Plane einbauen , flow pane zu scroll
        root = new ScrollPane();
        FlowPane container = new FlowPane();

        FlowPane plantContainer = getPlantContainer();
        FlowPane fokusTimeContainer = getFocusTimeContainer();
        tagsContainer = getTagsContainer(tagsList);
        FlowPane choseContainer = getChoseContainer();

        container.getChildren().add(plantContainer);
        container.getChildren().add(fokusTimeContainer);
        container.getChildren().add(tagsContainer);
        container.getChildren().add(choseContainer);
        container.setPrefWrapLength(appWidth - 20); // Sets the preferred break length of the FlowPane
        container.setMaxWidth(appWidth - 20);

        root.setContent(container);
        root.setFitToWidth(true); // Adjusts the width of the ScrollPane to match that of the FlowPane.
        root.setFitToHeight(true); // Adjusts the height of the ScrollPane to match that of the FlowPane.
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disables the horizontal scrollbar

    }

    private FlowPane getPlantContainer(){
        FlowPane plantContainer = new FlowPane();
        plantContainer.setMaxWidth(appWidth - 20);
        plantContainer.setPadding(new javafx.geometry.Insets(10,10,10,10));
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
        choseContainer.setMaxWidth(appWidth - 20);
        Label timeLabel = new Label("Test");
        timeLabel.textProperty().bind(chosenStringProperty);
        Label tagLabel = new Label(tagChose);
        tagLabel.textProperty().bind(chosenTagProperty);
        timeLabel.getStyleClass().add("label");
        tagLabel.getStyleClass().add("label");
        InputStream inputStream = getClass().getResourceAsStream(ApplicationState.getInstance().getSelectedPlant());
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
    private FlowPane getFocusTimeContainer (){
        FlowPane timesContainer = new FlowPane();
        timesContainer.setMaxWidth(appWidth - 20);
        FlowPane buttonsContainer = new FlowPane();
        buttonsContainer.setMaxWidth(appWidth - 20);

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

    private FlowPane getTagsContainer(ArrayList<String> tagsList){
        tagsContainer.getChildren().clear();
        tagsContainer.setMaxWidth(appWidth - 20);
        tagsContainer.setPadding(new javafx.geometry.Insets(10,10,10,10));
        System.out.println("Tags List Size: " + tagsList.size());
        if(tagsList.size() == 0 || tagsList == null){
            tagsContainer.getChildren().add(new TagsBtn("no tag"));
        }else{
            for(String tags : tagsList){
                TagsBtn tagsBtn = new TagsBtn(tags);
                tagsButtons.add(tagsBtn);
                tagsContainer.getChildren().add(tagsBtn);
            }
        }
        tagsContainer.getStyleClass().add("container");
        return tagsContainer;
    }

    public ArrayList<TagsBtn> getTagsButtons() {
        return tagsButtons;
    }

    public void updateTagList(ArrayList<String> tagsList){
        //Update List
        getTagsContainer(tagsList);
    }

    public ScrollPane getRoot() {
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
        chosenTagProperty.set("" + this.tagChose);
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

    public void setAppWidth(int appWidth) {
        this.appWidth = appWidth;
    }

    public void setAppHight(int appHight) {
        this.appHight = appHight;
    }
}

