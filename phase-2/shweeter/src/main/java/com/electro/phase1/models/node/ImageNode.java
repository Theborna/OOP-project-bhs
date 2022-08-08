package com.electro.phase1.models.node;

import com.electro.App;
import com.electro.phase1.enums.MediaType;
import com.electro.phase1.models.node.user.User;

import javafx.scene.image.Image;

public class ImageNode extends Media {
    public static final ImageNode DEFAULT_PFP = new ImageNode(
            App.class.getResource("icons/icons8_github_512px_1.png").toExternalForm());

    public ImageNode(String address) {
        super(address);
        setMt(MediaType.Image);
    }

    @Override
    public void showMedia() {
        // TODO Auto-generated method stub

    }

    public Image getImage(double width, double height) {
        return new Image(address, width, height, false, true);
    }

    public Image getImage() {
        return new Image(address);
    }
}
