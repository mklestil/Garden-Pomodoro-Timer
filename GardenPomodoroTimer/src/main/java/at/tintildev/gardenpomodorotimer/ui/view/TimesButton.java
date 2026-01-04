package at.tintildev.gardenpomodorotimer.ui.view;

import javafx.scene.control.Button;

/**
 * TimesButton inherits from the Button class and has been extended so that it can store/pass an int value.
 */
public class TimesButton extends Button {
    private int timeValue = 0;

    public TimesButton(String name, int value){
        this.setText(name);
        timeValue = value;
        this.getStyleClass().add("button");
    }

    public int getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(int timeValue) {
        this.timeValue = timeValue;
    }
}
