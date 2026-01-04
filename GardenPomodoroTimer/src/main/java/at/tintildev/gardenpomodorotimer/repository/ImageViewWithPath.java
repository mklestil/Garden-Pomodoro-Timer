package at.tintildev.gardenpomodorotimer.repository;

import javafx.scene.image.ImageView;

public class ImageViewWithPath {
    private ImageView imageView;
    private String path;

    public ImageViewWithPath(ImageView imageView, String path) {
        this.imageView = imageView;
        this.path = path;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public String getPath() {
        return path;
    }
}
