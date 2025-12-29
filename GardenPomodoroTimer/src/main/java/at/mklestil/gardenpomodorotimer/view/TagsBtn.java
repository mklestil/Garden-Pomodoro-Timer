package at.mklestil.gardenpomodorotimer.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;

public class TagsBtn extends Button {
    private String tagName;

    public TagsBtn(String tagName) {
        this.tagName = tagName;
        this.setText(tagName);
        //Todo: Color, Background and Border according to tag
        this.setBorder(Border.stroke(Color.BLACK));
        this.setPadding(new javafx.geometry.Insets(5));

    }

    public String getTagName() {
        return tagName;
    }

}
