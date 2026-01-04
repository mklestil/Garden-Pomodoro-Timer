package at.tintildev.gardenpomodorotimer.ui.view;

import javafx.scene.control.Alert;

/**
 * Central Error Handling View
 */
public class ErrorHandler {
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Ein Fehler ist aufgetreten!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
