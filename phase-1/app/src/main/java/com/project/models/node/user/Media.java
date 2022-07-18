package com.project.models.node.user;

import java.sql.Date;

import com.project.models.node.node;

public abstract class Media extends node{
    private String address;
    
    public Media(Date creationDate,String adress) {
        super(creationDate);
        this.address = address;
        //TODO Auto-generated constructor stub
    }

    public abstract void showMedia();
}
