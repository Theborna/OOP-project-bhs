package com.project.models.node.post;

import java.time.LocalDateTime;

import com.project.models.node.user.User;

public class NormalPost extends Post{

    public NormalPost(String text,User sender) {
        super(text, sender);
    }

    
}