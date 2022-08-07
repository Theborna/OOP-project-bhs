package com.electro.phase1.models.connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.electro.database.UserDB;
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
        this(obj1, obj2, 100);
    }

    public static Set<UserFollowingConnection> getFollowings(User user) {
        Set<UserFollowingConnection> result = new LinkedHashSet<UserFollowingConnection>();
        try {
            Map<User, Integer> followings = UserDB.getFollowings(user.getId(), 0);
            result.addAll(
                    followings.keySet().stream().map(i -> (new UserFollowingConnection(user, i, followings.get(i))))
                            .collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Set<UserFollowingConnection> getFollowers(User user) {
        Set<UserFollowingConnection> result = new LinkedHashSet<UserFollowingConnection>();
        try {
            Map<User, Integer> followers = UserDB.getFollowers(user.getId(), 0);
            result.addAll(
                    followers.keySet().stream().map(i -> (new UserFollowingConnection(i, user, followers.get(i))))
                            .collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
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

    public static Set<User> getExploreUsers(User user) {
        ArrayList<UserScore> userScores = UserScore.userScores(new ArrayList<User>(Suggestion.setScoreForUsers(user)),
                user);
        System.out.println(userScores);
        Collections.sort(userScores);
        System.out.println(userScores);
        return new LinkedHashSet<User>(UserScore.getUsers(userScores));
    }

    @Override
    public void sendToDB() {
        try {
            UserDB.updatePromoIndex(promoIndex, getObj2().getId(), getObj1().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
