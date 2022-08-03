package com.project.models.node;


import com.project.enums.MediaType;

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
