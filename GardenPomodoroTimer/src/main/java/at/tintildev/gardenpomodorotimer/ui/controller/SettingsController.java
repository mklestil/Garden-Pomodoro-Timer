package at.tintildev.gardenpomodorotimer.ui.controller;

import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import at.tintildev.gardenpomodorotimer.service.LanguageManager;
import at.tintildev.gardenpomodorotimer.ui.view.SettingsView;

public class SettingsController {
    private SettingsView view;
    private MainController mainController;
    public SettingsController(SettingsView view, MainController mainController) {
        this.view = view;
        this.mainController = mainController;

        changeLanguage();
        changeColor();
        changeScene();

    }

    private void changeColor() {
        view.getColorPicker().setOnAction(event -> {
            ApplicationState.getInstance().setBackgroundColor(view.getColorPicker().getValue().toString());
        });
    }

    private void changeScene() {
        view.getBackBtn().setOnAction(event -> {
            mainController.switchTo("start");
        });
    }

    private void changeLanguage() {
        view.getLanguageBox().setOnAction(event -> {
            System.out.println("change Language: " + view.getLanguageBox().getValue());
            LanguageManager.getInstance().setLocale(view.getLanguageBox().getValue());
        });
    }

}
