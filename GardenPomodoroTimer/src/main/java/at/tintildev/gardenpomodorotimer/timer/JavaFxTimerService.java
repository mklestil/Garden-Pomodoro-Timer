package at.tintildev.gardenpomodorotimer.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class JavaFxTimerService implements TimerService {

    private Timeline timeline;
    private boolean switchCheck = true;

    @Override
    public void startTimer() {
        if (timeline == null || timeline.getStatus() == Timeline.Status.STOPPED) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

        }else {
            //Pause
            timeline.play();

        }
        switchCheck = false;
    }

    @Override
    public void pauseTimer() {
        // Implementation here
    }

    @Override
    public void resetTimer() {
        // Implementation here
    }

    @Override
    public void updateTimer() {

    }
}
