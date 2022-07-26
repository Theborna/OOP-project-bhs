package com.project.models.node.user;

import java.time.LocalDateTime;
import java.util.Set;

import java.util.Date;

import com.project.LimitedList;
import com.project.models.connection.ChatUserConnection;
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
    private double promoIndex = 0;
    private String salt;
    private String username, password, name, lastName, email;
    private boolean isPublic;
    private StdColor nameColor;
    private int followerCnt, followingCnt, postCnt;
    private LocalDateTime dt;
    private int userType = 0;
    private Date birthDate;
    private LimitedList<Message> pastMsg;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setId(username.hashCode());
        pastMsg = new LimitedList<Message>(10);
        nameColor = StdColor.random("name");
    }

    public void setFollowerCnt(int followerCnt) {
        this.followerCnt = followerCnt;
    }

    public void setPostCnt(int postCnt) {
        this.postCnt = postCnt;
    }

    public static User logToUser(String username, String password) {
        // TODO: get the current user from the database
        currentUser = new NormalUser(username, password);
        return currentUser;
        // return null;
    }

    public int getPostCnt() {
        return postCnt;
    }

    public int getFollowingCnt() {
        return followingCnt;
    }

    public void sendMessage(Message message, Chat chat) {// TODO: send a message lmao
        Log.logger.info("sent message: " + message.toString() + " to chat: " + chat.toString());
        pastMsg.add(message);
    }

    public void setFollowingCnt(int followingCnt) {
        this.followingCnt = followingCnt;
    }

    public void setUSID(long US_ID) {
        setId(US_ID);
    }

    public long getUSID() {
        return getId();
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public double getPromoindex() {
        return promoIndex;
    }

    public void setPromoindex(double promoindex) {
        this.promoIndex = promoindex;
    }

    public static Long getID(String username) {
        // TODO: run a query to get the id
        return Long.valueOf(username.hashCode());
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public int getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public String getSalt() {
        return salt;
    }

    public static User getCurrentUser() {
        return currentUser;
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
    //
    // public Set<Post> getPosts() {
    // return PostUserConnection.getPost(this);
    // }

    public Set<Chat> getChats() {
        return ChatUserConnection.getChats(this.id);
    }

    @Override
    public String toString() {
        return "User [ username=" + username + ", follower count=" + followerCnt + ", visibility=" + isPublic + "]";
    }

    public LimitedList<Message> getPastMsg() {
        return pastMsg;
    }

}
