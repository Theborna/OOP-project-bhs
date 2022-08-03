package com.project.models.node;


import com.project.enums.MediaType;
import com.project.models.node.user.User;

public class Image extends Media {

    public Image(String address) {
        super(address);
        setMt(MediaType.Image);
    }

    @Override
    public void showMedia() {
        // TODO Auto-generated method stub

    }

}
