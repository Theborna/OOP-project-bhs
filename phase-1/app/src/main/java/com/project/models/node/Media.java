package com.project.models.node;

import java.time.LocalDateTime;

public abstract class Media extends node{
    private String address;
    
    public Media(String address) {
        this.address = address;
        //TODO Auto-generated constructor stub
    }

    public abstract void showMedia();
}
