package at.tintildev.gardenpomodorotimer.ui.view;

import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import at.tintildev.gardenpomodorotimer.service.LanguageManager;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingsView {

    private VBox root = new VBox();
    private ComboBox<String> languageBox;
    private Button backBtn = new Button(LanguageManager.getInstance().getBundle().getString("homeButton"));

    private ColorPicker colorPicker = new ColorPicker(Color.web(ApplicationState.getInstance().getBackgroundColor()));

    public SettingsView(){
        FlowPane flowPane = new FlowPane();
        languageBox = new ComboBox<>();
        languageBox.getItems().addAll("en", "de");
        languageBox.setValue(LanguageManager.getInstance().getBundle().getString("language"));

        Label infoLabel = new Label("Chose Language:");
        flowPane.getChildren().addAll(infoLabel, languageBox);
        root.getChildren().addAll(flowPane, colorPicker, backBtn);

        //updateTexts
        updateTexts();
        LanguageManager.getInstance().addLanguageChangeListener(this::updateTexts);

    }

    private void updateTexts() {
        backBtn.setText(LanguageManager.getInstance().getBundle().getString("homeButton"));
    }

    public VBox getRoot() {
        return root;
    }

    public ComboBox<String> getLanguageBox() {
        return languageBox;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }
}


