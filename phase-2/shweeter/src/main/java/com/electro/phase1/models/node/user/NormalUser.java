package com.electro.phase1.models.node.user;

import java.time.LocalDateTime;

// import com.electro.phase1.util.Log;

public class NormalUser extends User {

    public NormalUser(String username, String password) {
        super(username, password);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void Post(com.electro.phase1.models.node.post.Post post) {
        // TODO: add post to DB
        // Log.logger.info("added post: " + post.toString());
    }

}
