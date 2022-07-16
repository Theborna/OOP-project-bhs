package com.models.node;


import com.models.node.user.User;

public class PromotedPost extends Post {
    public PromotedPost(String text, User sender) {
        super(text, sender);
    }
}
