package com.electro.phase1.models.node.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.connection.MessageConnection;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.Message;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.post.Post;
// import com.electro.phase1.util.Log;
import com.electro.phase1.util.StdColor;
import com.electro.phase1.util.StdOut;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * abstract class defining users.
 * 
 * @abstract Posting and getting stats
 * @Children NormalUser, BusinessUser
 */
public abstract class User extends node {
    private static User currentUser;
    private String username, password, fullName, email, salt;
    private boolean isPublic;
    private StdColor nameColor;
    private int followerCnt, followingCnt;
    private Date birthDate;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        email = "asdad@adad";
        fullName = "jimmy fallen";
        salt = "asdasda";
        nameColor = StdColor.random("name");
        id = new Random().nextInt();
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
        MessageConnection.msg.add(message);
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

    public String getFullName() {
        return fullName;
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
        StdOut.println("liked " + post, StdColor.GREEN);
    }

    public void dislike(com.electro.phase1.models.node.post.Post post) {// TODO: implement this method
        StdOut.println("disliked " + post, StdColor.GREEN);
    }

    public void sendToDB() {
        // TODO: send the user to the database, register
    }

    public int getFollowingCnt() {
        return followingCnt;
    }

    public static String[] getSecurityQuestion(String username2) {// TODO: add this
        return new String[] { "what's the name of your dog?", "teddy", "boobs" };
    }

    public static User get(String selectedItem) {
        return currentUser;// TODO
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(StringProperty username2) {
    }

    public void setPublic(BooleanProperty visible) {
    }

    public void setName(StringProperty name) {
    }

    public String getSalt() {
        return salt;
    }

    public void setPassword(String encryptedString) {
    }
}
