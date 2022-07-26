package com.project.models.node.user;


import java.time.LocalDateTime;
import java.util.Set;

import com.models.connection.ChatUserConnection;
import com.models.connection.PostUserConnection;
import com.models.node.Chat;
import com.models.node.Message;
import com.models.node.Post;
import com.models.node.node;

import com.util.StdColor;

/**
 * abstract class defining users.
 * 
 * @abstract Posting and getting stats
 * @Children NormalUser, BusinessUser
 */
public abstract class User extends node {
    private long USID;
    private static User currentUser;
    private double promoindex = 0;
    private String salt;
    private String username, password, name, lastName, email;
    private boolean isPublic;
    private StdColor nameColor;
    private int followerCnt, followingCnt, postCnt;
    private LocalDateTime dt;
    private int userType = 0;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        nameColor = StdColor.random("name");
    }

    public void setFollowerCnt(int followerCnt) {
        this.followerCnt = followerCnt;
    }

    public void setPostCnt(int postCnt) {
        this.postCnt = postCnt;
    }

    public int getPostCnt() {
        return postCnt;
    }

    public int getFollowingCnt() {
        return followingCnt;
    }

    public void setFollowingCnt(int followingCnt) {
        this.followingCnt = followingCnt;
    }

    public void setUSID(long USID) {
        this.USID = USID;
    }

    public long getUSID() {
        return USID;
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
        return promoindex;
    }

    public void setPromoindex(double promoindex) {
        this.promoindex = promoindex;
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
//
//    public Set<Post> getPosts() {
//        return PostUserConnection.getPost(this);
//    }

    public Set<Chat> getChats() {
        return ChatUserConnection.getChats(this);
    }

}
