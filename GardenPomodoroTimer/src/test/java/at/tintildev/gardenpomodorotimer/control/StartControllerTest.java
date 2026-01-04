package at.tintildev.gardenpomodorotimer.control;

import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import at.tintildev.gardenpomodorotimer.ui.controller.MainController;
import at.tintildev.gardenpomodorotimer.ui.view.StartWindow;
import at.tintildev.gardenpomodorotimer.ui.controller.StartWindowController;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class StartControllerTest {

    private StartWindowController controller;
    private StartWindow view;  // use Mock or Dummy-View
    private MainController sceneManger;
    private ApplicationState model;

    @BeforeAll
    static void initJfxRuntime() {
        // JavaFX initialization required for tests
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        view = new StartWindow();
        model = ApplicationState.getInstance();
        sceneManger = new MainController(new Stage(), model);
        controller = new StartWindowController(view, sceneManger);
    }

    @Test
    void testPlusTime() {
        int initialTime = 25 * 60; // 25 Minuten
        controller.plusTime();
        assertEquals(initialTime + 60, controller.getRemainingTime());
    }

    @Test
    void testMinusTime() {
        int initialTime = 25 * 60; // 25 Minuten
        controller.minusTime();
        assertEquals(initialTime - 60, controller.getRemainingTime());
    }

    @Test
    void testFormatTime() {

        assertEquals("25:00", controller.formatTime(1500));
        assertEquals("01:00", controller.formatTime(60));
        assertEquals("00:59", controller.formatTime(59));
    }

    @Test
    void testResetTimer() {
        controller.resetTimer();
        assertEquals(25 * 60, controller.getRemainingTime());  // Soll auf ursprüngliche Zeit zurückgesetzt werden
        assertEquals(0, view.getProgress().getProgress());
    }
}