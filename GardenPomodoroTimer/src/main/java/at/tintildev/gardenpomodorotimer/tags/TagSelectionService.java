package at.tintildev.gardenpomodorotimer.tags;

import at.tintildev.gardenpomodorotimer.ui.controller.MainController;
import at.tintildev.gardenpomodorotimer.state.ApplicationState;

import java.util.List;

public class TagSelectionService {

    private final MainController mainController;
    private final ApplicationState model;

    /**
     * Constructor for TagSelectionService.
     *
     * @param mainController The main controller to interact with.
     * @param model          The application model containing the current state.
     */

    public TagSelectionService(MainController mainController, ApplicationState model) {
        this.mainController = mainController;
        this.model = model;
    }

    /**
     * Method to get the available tags from the database.
     */
    public List<String> getAvailableTags() {
        List<String> tags = mainController.loadTagsFromDB();

        if (tags.isEmpty()) {
            tags.add(model.getTag());
        }

        return tags;
    }
}
