package com.electro.phase1.models.connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.Suggestion;
import com.electro.phase1.util.UserScore;

public class UserFollowingConnection extends connection<User, User> {

    private int promoIndex;

    public UserFollowingConnection(User obj1, User obj2, int promoIndex) {
        super(obj1, obj2);
        this.promoIndex = promoIndex;
    }

    public UserFollowingConnection(User obj1, User obj2) {
        super(obj1, obj2);
        promoIndex = 100;
    }

    public static Set<UserFollowingConnection> getFollowings(User user) {
        Set<UserFollowingConnection> result = new LinkedHashSet<UserFollowingConnection>();
        // TODO run a query on the database and get Followings;
        result.add(new UserFollowingConnection(user, new NormalUser("sex", "anal")));
        result.add(new UserFollowingConnection(user, new NormalUser("sex", "vaginal")));
        for (int i = 0; i < 10; i++) {
            result.add(new UserFollowingConnection(user,
                    new NormalUser(Integer.toBinaryString(i), Integer.toString(2 * i))));
        }
        return result;
    }

    public static Set<UserFollowingConnection> getFollowers(User user) {
        Set<UserFollowingConnection> result = new LinkedHashSet<UserFollowingConnection>();
        // TODO run a query on the database and get Followers;
        result.add(new UserFollowingConnection(new NormalUser("sex", "anal"), user));
        result.add(new UserFollowingConnection(new NormalUser("sex", "vaginal"), user));
        for (int i = 0; i < 10; i++) {
            result.add(new UserFollowingConnection(new NormalUser(Integer.toBinaryString(i), Integer.toString(2 * i)),
                    user));
        }
        return result;
    }

    public static boolean Follows(User user1, User user2) {
        Set<UserFollowingConnection> connections = getFollowers(user2);
        for (UserFollowingConnection connection : connections) {
            if (connection.getObj1() == user1) {
                return true;
            }
        }
        return false;
    }

    public static UserFollowingConnection getConnectionByUsers(User user, User Following) {
        Set<UserFollowingConnection> connections = getFollowers(Following);
        for (UserFollowingConnection connection : connections) {
            if (connection.getObj1() == user) {
                return connection;
            }
        }
        return null;
    }

    public int getPromoIndex() {
        return promoIndex;
    }

    public void decreasePromoIndex(int decreaseNum) {
        if (!(promoIndex - decreaseNum < -100)) {
            promoIndex -= decreaseNum;
        }
        this.sendToDB();
    }

    public void increasePromoIndex(int increaseNum) {
        if (!(increaseNum + promoIndex > 100)) {
            promoIndex += increaseNum;
        }
        this.sendToDB();
    }

    public static Set<User> getExploreUsers(User user) { // TODO: find appropriate users to show
        ArrayList<UserScore> userScores = UserScore.userScores(new ArrayList<User>(Suggestion.setScoreForUsers(user)),
                user);
        Collections.sort(userScores);
        return new LinkedHashSet<User>(UserScore.getUsers(userScores));
        // return getFeed(user.getId());
    }

    @Override
    public void sendToDB() {
        
    }

}
