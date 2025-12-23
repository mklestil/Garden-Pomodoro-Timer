package at.mklestil.gardenpomodorotimer.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;

public class TagsLabel extends Label {
    private String tagName;

    public TagsLabel(String tagName) {
        this.tagName = tagName;
        this.setText(tagName);
        //Todo: Color, Background and Border according to tag
        this.setBorder(Border.stroke(Color.BLACK));
        this.setPadding(new javafx.geometry.Insets(5));
        this.setMinSize(60, 30);
    }

    public String getTagName() {
        return tagName;
    }

}
