package com.project.models.node.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import com.project.models.connection.ChatUserConnection;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.node;
import com.project.models.node.post.Post;
import com.project.util.Log;
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
        // TODO: get the current user from the database
        currentUser = new NormalUser(username, password);
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public void sendMessage(Message message, Chat chat) {// TODO: send a message lmao
        Log.logger.info("sent message: " + message.toString() + " to chat: " + chat.toString());
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

    public Set<com.project.models.node.post.Post> getPosts() {
        return PostUserConnection.getPosts(this.id);
    }

    public Set<Chat> getChats() {
        return ChatUserConnection.getChats(this.id);
    }

    public Set<Chat> getAccessibleChats() {// TODO: get appropriate channels
        return getChats();
    }

    public void like(com.project.models.node.post.Post post) {// TODO: implement this method
    }

    public void dislike(com.project.models.node.post.Post post) {// TODO: implement this method
    }

}
