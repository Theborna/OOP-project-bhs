package com.project.models.user;

import com.project.models.Chat;
import com.project.models.Message;
import com.project.models.Post;
import com.project.models.data;

/**
 * abstract class defining users.
 * 
 * @abstract Posting and getting stats
 * @Children NormalUser, BusinessUser
 */
public abstract class User extends data {
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
