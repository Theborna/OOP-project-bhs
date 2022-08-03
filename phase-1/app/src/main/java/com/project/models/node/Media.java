package com.project.models.node;

import com.project.enums.MediaType;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;

public abstract class Media extends node {
    private String address;
    private MediaType mt;

    public Media(String address) {
        this.address = address;
    }

    public MediaType getMt() {
        return mt;
    }

    public void setMt(MediaType mt) {
        this.mt = mt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract void showMedia();

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        
    }

    
}
