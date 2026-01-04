package at.tintildev.gardenpomodorotimer.ui.view;

import javafx.scene.image.ImageView;

/**
 * A class that holds an ImageView and its associated file path.
 */
public class ImageViewWithPath{
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
