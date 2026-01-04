package at.tintildev.gardenpomodorotimer.ui.view;

import at.tintildev.gardenpomodorotimer.domain.PomodoroSession;
import at.tintildev.gardenpomodorotimer.service.LanguageManager;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * ChartView, to view a Chart of Pomodoro Sessions.
 */
public class ChartView {
    private VBox root;
    private Button backBtn = new Button(LanguageManager.getInstance().getBundle().getString("homeButton"));
    private TableView tableView = new TableView();
    private TableColumn<PomodoroSession, Integer> idColumn = new TableColumn<>(LanguageManager.getInstance().getBundle().getString("idColumn"));
    private TableColumn<PomodoroSession, Integer> durationColumn = new TableColumn<>(LanguageManager.getInstance().getBundle().getString("durationColumn"));
    private TableColumn<PomodoroSession, String> plantColumn = new TableColumn<>(LanguageManager.getInstance().getBundle().getString("plantColumn"));
    private TableColumn<PomodoroSession, String> timestampColumn = new TableColumn<>(LanguageManager.getInstance().getBundle().getString("timestampColumn"));

    public ChartView(){
        root = new VBox();
        root.getChildren().addAll(tableView, backBtn);

        //updateTexts
        updateTexts();
        LanguageManager.getInstance().addLanguageChangeListener(this::updateTexts);
    }

    private void updateTexts() {
        backBtn.setText(LanguageManager.getInstance().getBundle().getString("homeButton"));
        updateColumnHeaders();
    }

    private void updateColumnHeaders() {
        idColumn.setText(LanguageManager.getInstance().getBundle().getString("idColumn"));
        durationColumn.setText(LanguageManager.getInstance().getBundle().getString("durationColumn"));
        plantColumn.setText(LanguageManager.getInstance().getBundle().getString("plantColumn"));
        timestampColumn.setText(LanguageManager.getInstance().getBundle().getString("timestampColumn"));
    }

    public void showData(ObservableList<PomodoroSession> sessionData) {
        System.out.println(sessionData);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        plantColumn.setCellValueFactory(new PropertyValueFactory<>("plantChoice"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        TableColumn<PomodoroSession, String> tag = new TableColumn<>("Tag");
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));

        // Spalten zur TableView hinzufügen
        tableView.getColumns().addAll(idColumn, durationColumn, plantColumn, timestampColumn, tag);
        tableView.setItems(sessionData);
        tableView.refresh();
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBackBtn() {
        return backBtn;
    }
}
