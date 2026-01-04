package at.tintildev.gardenpomodorotimer.timer;

import javafx.animation.Timeline;

public interface TimerService {

    public abstract void startTimer();
    public abstract void pauseTimer();
    public abstract void resetTimer();
    public abstract void updateTimer();
}
