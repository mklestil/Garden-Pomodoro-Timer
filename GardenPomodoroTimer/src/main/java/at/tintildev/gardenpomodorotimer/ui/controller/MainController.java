package at.tintildev.gardenpomodorotimer.ui.controller;

import at.tintildev.gardenpomodorotimer.domain.PomodoroSession;
import at.tintildev.gardenpomodorotimer.repository.*;
import at.tintildev.gardenpomodorotimer.repository.sqlite.ImageSQLiteRepository;
import at.tintildev.gardenpomodorotimer.repository.sqlite.SessionSQLiteRepository;
import at.tintildev.gardenpomodorotimer.repository.sqlite.TagSQLiteRepository;
import at.tintildev.gardenpomodorotimer.service.LanguageManager;
import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import at.tintildev.gardenpomodorotimer.repository.TagRepository;
import at.tintildev.gardenpomodorotimer.ui.view.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages view, logic, takes care of event handling, switch scenes.
 */
public class MainController {

    private final Stage stage;
    private ApplicationState model;
    private ImageRepository imageRepository;
    private SessionRepository sessionRepository;
    private TagRepository tagDAO;
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

    public MainController(Stage stage, ApplicationState model) {
        this.model = model;
        this.stage = stage;
        appWidth = model.getAppWidth();
        appHeight = model.getAppHight();

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
        startController.initialize();
        startScene = new Scene(view.getRoot(), appWidth, appHeight);
        startScene.getStylesheets().add(getClass().getResource("/styles/startWindowStyle.css").toExternalForm());

        //ChosePlant
        ChosePlant chosePlantView = new ChosePlant();
        chosePlantController = new ChosePlantController(chosePlantView, this);
        chosePlantView.getRoot().setMaxSize(appWidth, appHeight);
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

    public ApplicationState getModel() {
        return model;
    }

    private void startDBs() {
        imageRepository = new ImageSQLiteRepository();
        sessionRepository = new SessionSQLiteRepository();
        tagDAO = new TagSQLiteRepository();

    }


    public void saveSession(int duration, String plant, String tag) {
        String timestamp = "" + System.currentTimeMillis();
        sessionRepository.insert(new PomodoroSession(duration, plant, timestamp, tag));
    }

    public List<PomodoroSession> loadSessionsFromDB() {
        return sessionRepository.findAll();
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



