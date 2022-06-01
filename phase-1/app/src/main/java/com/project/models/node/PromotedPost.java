package com.project.models.node;

import com.project.models.node.user.User;

public class PromotedPost extends Post {
    public PromotedPost(String text,User sender) {
        super(text, sender);
    }
}
