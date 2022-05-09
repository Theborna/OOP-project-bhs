package com.project.models.user;

import com.project.models.Message;
import com.project.models.Post;
import com.project.models.data;
import com.project.models.chat.Chat;

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
        // TODO: make constructor
    }

    public void sendMessage(Message message, Chat chat) {
    }

    public abstract void Post(Post post);

}
