package at.tintildev.gardenpomodorotimer.ui.controller;

import at.tintildev.gardenpomodorotimer.domain.PomodoroSession;
import at.tintildev.gardenpomodorotimer.ui.view.ChartView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChartController {
    private MainController mainController;
    private ChartView view;
    private final ObservableList<PomodoroSession> sessionData = FXCollections.observableArrayList();

    public ChartController(MainController mainController, ChartView view){
        this.view = view;
        this.mainController = mainController;
        this.view.getBackBtn().setOnAction(event -> {
            mainController.switchTo("start");
        });

        setDataToTable();
    }

    private void setDataToTable() {
        sessionData.setAll(mainController.loadSessionsFromDB());
        view.showData(sessionData);
    }

    public void updateView(){
        sessionData.setAll(mainController.loadSessionsFromDB());
    }

}
