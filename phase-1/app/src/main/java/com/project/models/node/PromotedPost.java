package com.project.models.node;

import java.sql.Date;

import com.project.models.node.user.User;

public class PromotedPost extends Post {

    public PromotedPost(Date creationDate, String text, User Sender) {
        super(creationDate, text, Sender);
        //TODO Auto-generated constructor stub
    }

}
