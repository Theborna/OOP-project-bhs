package com.project.models.node;

import java.sql.Date;

import com.project.models.node.user.User;

public class NormalPost extends Post{

    public NormalPost(Date creationDate, String text,User sender) {
        super(creationDate, text, sender);
    }

    
}