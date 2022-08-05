package com.electro.phase1.models.node;


import com.electro.phase1.enums.MediaType;

public class Video extends Media {

    public Video(String address) {
        super(address);
        setMt(MediaType.Video);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void showMedia() {
        // TODO Auto-generated method stub
        
    }
    
}