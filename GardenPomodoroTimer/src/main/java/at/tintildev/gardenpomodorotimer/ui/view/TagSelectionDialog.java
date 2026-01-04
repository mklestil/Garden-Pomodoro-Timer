package at.tintildev.gardenpomodorotimer.ui.view;

import at.tintildev.gardenpomodorotimer.service.LanguageManager;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

public class TagSelectionDialog extends ChoiceDialog<String> {
    /**
     * Shows a dialog to select a tag from the provided list.
     *
     * @param tags List of tags to choose from.
     * @return An Optional containing the selected tag, or empty if no selection was made.
     */
    public TagSelectionDialog(List<String> tags) {
        //Todo: LanguageManager
        super(tags.get(0), tags);
        this.setTitle(LanguageManager.getInstance().getBundle().getString("selectTagTitle"));
        this.setHeaderText(LanguageManager.getInstance().getBundle().getString("sleectTagHeader"));
        this.setContentText(LanguageManager.getInstance().getBundle().getString("selectTagContent"));
    }

    public Optional<String> show(List<String> tags) {
        return this.showAndWait();

    }
}
