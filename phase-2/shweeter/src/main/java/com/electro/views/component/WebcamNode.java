package com.electro.views.component;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.phase1.models.node.ImageNode;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDevice;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import java.awt.image.BufferedImage;

public class WebcamNode {

    protected WebcamNode(WebcamDevice device) {

    }

    public static Image getImage() throws IOException {
        ImageView iv = new ImageView();
        Webcam node = Webcam.getDefault();
        PopOver popOver = new PopOver();
        node.open();
        Image result = null;
        iv.setImage(result = convertToFxImage(node.getImage()));
        popOver.setContentNode(iv);
        node.close();
        popOver.show(App.getScene().getWindow());
        return result;
    }

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
}
