package com.project.models.node.user;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

import java.util.Date;

import com.database.MessageDB;
import com.database.UserDB;
import com.project.LimitedList;
import com.project.enums.Security;
import com.project.models.connection.ChatUserConnection;
import com.project.models.connection.Like;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.Chat;
import com.project.models.node.Media;
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
    private long lastViewed;
    private double promoIndex = 0;
    private String salt;
    private String username, password, name, lastName, email;
    private boolean isPublic;
    private StdColor nameColor;
    private int followerCnt, followingCnt, postCnt;
    private int userType = 0;
    private Date birthDate;
    private Security secType;
    private String secAns;
    private LimitedList<Message> pastMsg;
    private Media profilePhoto;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // setId(username.hashCode());
        pastMsg = new LimitedList<Message>(10);
        nameColor = StdColor.values()[(int) (id % StdColor.values().length)];
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    public String getSecAns() {
        return secAns;
    }

    public Security getSecType() {
        return secType;
    }

    public void setSecType(Security secType) {
        this.secType = secType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setFollowerCnt(int followerCnt) {
        this.followerCnt = followerCnt;
    }

    public void setPostCnt(int postCnt) {
        this.postCnt = postCnt;
    }

    public static User logToUser(String username, String password) {
        // TODO: get the current user from the database
        try {
            if (UserDB.auth(username, password)) {
                currentUser = logToUser(username);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return currentUser;
        // try {
        // if (!UserDB.auth(currentUser))
        // return null;
        // currentUser = UserDB.getUserInfo(username);
        // return currentUser;
        // } catch (Throwable e) {
        // e.printStackTrace();
        // }
        // return null;
    }

    public static User logToUser(String username) {
        // TODO: get the current user from the database
        try {
            currentUser = UserDB.getUserInfo(username);
            // System.out.println(currentUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;
        // try {
        // if (!UserDB.auth(currentUser))
        // return null;
        // currentUser = UserDB.getUserInfo(username);
        // return currentUser;
        // } catch (Throwable e) {
        // e.printStackTrace();
        // }
    }

    public int getPostCnt() {
        return postCnt;
    }

    public int getFollowingCnt() {
        try {
            return UserDB.getFollowings(this.id,0).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void sendMessage(Message message, Chat chat) {// TODO: send a message lmao
        Log.logger.info("sent message: " + message.toString() + " to chat: " + chat.toString());
        // pastMsg.add(message);
        try {
            MessageDB.addToDB(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void follow(User user) {
        try {
            UserDB.follow(this, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unfollow(User user) {
        try {
            UserDB.unFollow(this, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isFollowing(User user) {
        try {
            return UserDB.isFollowed(this, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Fuck");
        return false;
    }

    public void setFollowingCnt(int followingCnt) {
        this.followingCnt = followingCnt;
    }

    public void setUS_ID(long US_ID) {
        setId(US_ID);
    }

    public long getUS_ID() {
        return getId();
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
        try {
            User us = UserDB.getUserInfo(username);
            return (us == null ? 0 : us.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        return getCreationDate();
    }

    public void setDt(LocalDateTime dt) {
        setCreationDate(dt);
    }

    public String getSalt() {
        return salt;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public abstract void Post(String post, Post inReplyTo);

    public String getUsername() {
        return username;
    }

    public StdColor getNameColor() {
        return nameColor;
    }

    public int getFollowerCnt() {
        try {
            return UserDB.getFollowers(this.id, 0).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public void view(Post post, int value) {
        new Like(post, this).setValue(value).sendToDB();
        Log.logger.info("viewed post " + post + " ,value: " + value);
    }

    public void view(Post post) {
        lastViewed = post.getId();
        long thisView = lastViewed;
        Thread wait = new Thread(() -> {
            try {
                Thread.sleep(1000);
                if (lastViewed == thisView)
                    view(post, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        wait.start();
    }

    public void like(com.project.models.node.post.Post post) {
        view(post, 1);
    }

    public void dislike(com.project.models.node.post.Post post) {
        view(post, -1);
    }

    @Override
    public String toString() {
        return "User [ username=" + username + ", follower count=" + followerCnt + ", visibility=" + isPublic + "]";
    }

    public LimitedList<Message> getPastMsg() {
        pastMsg.clear();
        try {
            pastMsg.addAll(MessageDB.getMessagesByUserID(this.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pastMsg;
    }

    public static void logout() {
        currentUser = null;
    }

    public User setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public User setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public void sendToDB() {
        // TODO: send the user to the database, register
        // System.out.println("heyuylg");
        try {
            UserDB.sendToDB(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Media getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Media profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
