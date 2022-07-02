package com.project.models.node.user;

import java.util.Set;

import com.project.models.connection.ChatUserConnection;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.Post;
import com.project.models.node.node;
import com.project.util.StdColor;

/**
 * abstract class defining users.
 * 
 * @abstract Posting and getting stats
 * @Children NormalUser, BusinessUser
 */
public abstract class User extends node {
    private static User currentUser;
    private String username, password;
    private boolean isPublic;
    private StdColor nameColor;
    private int followerCnt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        nameColor = StdColor.random("name");
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static User logToUser(String username, String password) {
        // get the current user from the database
        currentUser = new NormalUser(username, password);
        return currentUser;
    }

    public void sendMessage(Message message, Chat chat) {
    }

    public abstract void Post(Post post);

    public String getUsername() {
        return username;
    }

    public StdColor getNameColor() {
        return nameColor;
    }

    public int getFollowerCnt() {
        return followerCnt;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Set<com.project.models.node.Post> getPosts() {
        return PostUserConnection.getPost(this);
    }

    public Set<Chat> getChats() {
        return ChatUserConnection.getChats(this);
    }
}
