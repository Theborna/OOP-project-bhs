package com.project.models.connection;


import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.database.viewDB;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
/*
*
* 0 is for a normal view
* 1 represents a like
* -1 represents a dislike
*
* we value a post by the sum of the values of its likes
* */
public class Like extends connection<Post, User> {

    private int value;
    public Like(Post obj1, User obj2) {
        super(obj1, obj2);
    }

    @Override
    public Like setValue(int value) {
        this.value = value;
        return this;
    }

    public int getValue() {
        return value;
    }

    public static Set<Like> getPosts(User user) {
        Set<Like> result = new LinkedHashSet<>();
        try {
            result.addAll(viewDB.getLikesByUserID(user.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Set<Like> getUsers(Post post) {
        Set<Like> result = new LinkedHashSet<>();
        try {
            result.addAll(viewDB.getLikesByPostID(post.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void sendToDB() {

    }
}
