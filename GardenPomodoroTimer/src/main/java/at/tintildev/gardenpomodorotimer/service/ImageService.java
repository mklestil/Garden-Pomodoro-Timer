package at.tintildev.gardenpomodorotimer.service;

import at.tintildev.gardenpomodorotimer.repository.ImageRepository;
import javafx.scene.image.Image;

import java.util.List;

public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> loadImages() {
        return imageRepository.findAll();
    }
}

