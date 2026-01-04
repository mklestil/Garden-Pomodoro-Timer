/**
 * The main module of the Garden Pomodoro Timer application.
 * <p>
 * It contains the timer control, database management
 * and the graphical user interface with JavaFX.
 * </p>
 */
module at.mklestil.pomodorotimer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens at.tintildev.gardenpomodorotimer.repository to javafx.base;
    opens at.tintildev.gardenpomodorotimer to javafx.fxml;
    exports at.tintildev.gardenpomodorotimer;
    opens at.tintildev.gardenpomodorotimer.domain to javafx.base;
    opens at.tintildev.gardenpomodorotimer.repository.sqlite to javafx.base;
    opens at.tintildev.gardenpomodorotimer.state to javafx.base;
    opens at.tintildev.gardenpomodorotimer.service to javafx.base;
    opens at.tintildev.gardenpomodorotimer.ui.view to javafx.base;
}