package com.project.models.node.user;

import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.Post;
import com.project.models.node.node;

/**
 * abstract class defining users.
 * 
 * @abstract Posting and getting stats
 * @Children NormalUser, BusinessUser
 */
public abstract class User extends node {
    private String username, password;
    private boolean isPublic;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendMessage(Message message, Chat chat) {
    }

    public abstract void Post(Post post);

    public String getUsername() {
        return username;
    }
}
