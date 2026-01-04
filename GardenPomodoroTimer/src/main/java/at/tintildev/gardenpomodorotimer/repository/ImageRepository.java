package at.tintildev.gardenpomodorotimer.repository;

import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    List<Image> findAll();
    Optional<Image> findById(long id);
    void save(ApplicationState model);
}
