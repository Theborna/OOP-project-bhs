package com.project.models.node.user;

import java.time.LocalDateTime;

import com.project.util.Log;

public class NormalUser extends User {

    public NormalUser(String username, String password) {
        super(username, password);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void Post(com.project.models.node.post.Post post) {
        // TODO: add post to DB
        Log.logger.info("added post: " + post.toString());
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        
    }

}
