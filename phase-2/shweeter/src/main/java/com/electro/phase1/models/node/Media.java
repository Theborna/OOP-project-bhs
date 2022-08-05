package com.electro.phase1.models.node;

import com.electro.phase1.enums.MediaType;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

public abstract class Media extends node {
    protected String address;
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