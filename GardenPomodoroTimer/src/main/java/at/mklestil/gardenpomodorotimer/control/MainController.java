package at.mklestil.gardenpomodorotimer.control;

import at.mklestil.gardenpomodorotimer.model.*;
import at.mklestil.gardenpomodorotimer.service.LanguageManager;
import at.mklestil.gardenpomodorotimer.view.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages view, logic, takes care of event handling, switch scenes.
 */
public class MainController {

    private final Stage stage;
    private AppModel model;
    private ImagesDAO imagesDAO;
    private SessionDAO sessionDAO;
    private TagDAO tagDAO;
    private int appWidth = 280;
    private int appHeight = 420;
    private Scene startScene;
    private Scene choseScene;
    private Scene settingsScene;
    private Scene chartScene;
    private StartWindowController startController;
    private ChosePlantController chosePlantController;
    private SettingsController settingsController;
    private ChartController chartController;

    public MainController(Stage stage, AppModel model) {
        this.model = model;
        this.stage = stage;

        // Set language before views are loaded
        LanguageManager.getInstance();

        //create Tables
        startDBs();
    }

    /**
     * Initialize the view and controllers.
     */
    public void initializeScenes() {
        //Start View
        StartWindow view = new StartWindow();
        startController = new StartWindowController(view, this);
        startScene = new Scene(view.getRoot(), appWidth, appHeight);
        startScene.getStylesheets().add(getClass().getResource("/styles/startWindowStyle.css").toExternalForm());

        //ChosePlant
        ChosePlant chosePlantView = new ChosePlant();
        chosePlantController = new ChosePlantController(chosePlantView, this);
        choseScene = new Scene(chosePlantView.getRoot(), appWidth, appHeight);
        choseScene.getStylesheets().add(getClass().getResource("/styles/chosePlant.css").toExternalForm());

        // Settings
        SettingsView settingsView = new SettingsView();
        settingsController = new SettingsController(settingsView, this);
        settingsScene = new Scene(settingsView.getRoot(), appWidth, appHeight);

        //Chart
        ChartView chartView = new ChartView();
        chartController = new ChartController(this, chartView);
        chartScene = new Scene(chartView.getRoot(), appWidth, appHeight);

    }

    public void startApp() {
        switchTo("start");
    }

    public void switchTo(String name) {
        //show scene
        if (name.equals("start")) {
            stage.setScene(startScene);
            startController.updateView();
        } else if (name.equals("chose")) {
            stage.setScene(choseScene);
        } else if (name.equals("settings")) {
            stage.setScene(settingsScene);
        } else if (name.equals("chart")) {
            chartController.updateView();
            stage.setScene(chartScene);
        } else {
            System.out.println("Error: Scene " + name + " not found");
            ErrorHandler.showError("Error: Scene " + name + " not found");
        }

    }

    public AppModel getModel() {
        return model;
    }

    private void startDBs() {
        imagesDAO = new ImagesDAO();
        sessionDAO = new SessionDAO();
        tagDAO = new TagDAO();

        // Create tables if not exists
        imagesDAO.createTable();
        sessionDAO.createTable();
        tagDAO.createTable();

    }


    public void saveSession(int duration, String plant, String tag) {
        String timestamp = "" + System.currentTimeMillis();
        sessionDAO.insert(new PomodoroSession(duration, plant, timestamp, tag));
    }

    public List<PomodoroSession> loadSessionsFromDB() {
        return sessionDAO.findAll();
    }


    public ArrayList<String> loadTagsFromDB() {
        //TODO
        return tagDAO.findAll();
    }

    public void changeLanugage() {
        LanguageManager.getInstance().addLanguageChangeListener(() -> {
            System.out.println("Language has been changed! UI needs to be updated now.");
            initializeScenes(); // reload UI Elements
        });
    }

    public void saveTag(String tag) {
        model.setTag(tag);
        tagDAO.insert(model);
        System.out.println("Tag saved: " + tag);
        // initializeScenes(); // reload UI Elements
    }

    public void saveEditTag(String tag, String newTag) {
        // Update Tags in db
        tagDAO.update(tag, newTag);
        model.setTag(newTag);
    }

    public void loadAllTags(){
        model.setTagsList(tagDAO.findAll());
    }
}



