package com.electro.phase1.models.node.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.Message;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.post.Post;
// import com.electro.phase1.util.Log;
import com.electro.phase1.util.StdColor;

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
    private Date birthDate;

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
        // Log.logger.info("sent message: " + message.toString() + " to chat: " +
        // chat.toString());

    }

    public void follow(User user) {
        // TODO : follow the user
    }

    public void unfollow(User user) {
        // TODO : unfollow the user
    }

    public boolean isFollowing(User user) {
        // TODO : check if the user is following
        return false;
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

    public User setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public User setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public static Long getID(String username) {
        // TODO: run a query to get the id
        return Long.valueOf(username.hashCode());
    }

    public Set<com.electro.phase1.models.node.post.Post> getPosts() {
        return PostUserConnection.getPosts(this.id);
    }

    public Set<Chat> getChats() {
        return ChatUserConnection.getChats(this.id);
    }

    public Set<Chat> getAccessibleChats() {// TODO: get appropriate channels
        return getChats();
    }

    public void like(com.electro.phase1.models.node.post.Post post) {// TODO: implement this method
    }

    public void dislike(com.electro.phase1.models.node.post.Post post) {// TODO: implement this method
    }

    public void sendToDB() {
        // TODO: send the user to the database, register
    }
}
