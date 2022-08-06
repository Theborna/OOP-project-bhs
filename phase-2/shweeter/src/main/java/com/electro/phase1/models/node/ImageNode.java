package com.electro.phase1.models.node;

import com.electro.App;
import com.electro.phase1.enums.MediaType;
import com.electro.phase1.models.node.user.User;

public class ImageNode extends Media {

    public ImageNode(String address) {
        super(address);
        setMt(MediaType.Image);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void showMedia() {
        // TODO Auto-generated method stub

    }

    public javafx.scene.image.Image getImage() {
        return App.getImage("images/icons8_info_96px.png");// TODO
    }

}
