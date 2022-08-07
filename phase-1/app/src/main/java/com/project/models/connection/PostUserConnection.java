package com.project.models.connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.database.PostDB;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.PostScore;
import com.project.util.Suggestion;

public class PostUserConnection extends connection<User, Post> {

    public PostUserConnection(User obj1, Post obj2) {
        super(obj1, obj2);
    }

    public static Set<Post> getPosts(Long userId) {
        Set<Post> result = new LinkedHashSet<>();
        try {
            result.addAll(PostDB.getPostsByUS_ID(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Set<Post> getFeed(Long userId) {
        Set<Post> result = new LinkedHashSet<>();
        try {
            result.addAll(PostDB.getFeed(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // public static User getUser(Post postId) {
    // return (new NormalUser("",""));
    //
    // }

    public static Set<Post> getExplore(User user) {
        ArrayList<PostScore> postScores = PostScore.postScores(new ArrayList<Post>(Suggestion.setScoreForPosts(user)),
                user);
        Collections.sort(postScores);
        return new LinkedHashSet<Post>(PostScore.getPosts(postScores));
    }
}
