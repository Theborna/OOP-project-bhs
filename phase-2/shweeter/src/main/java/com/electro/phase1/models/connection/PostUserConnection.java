package com.electro.phase1.models.connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.electro.database.PostDB;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.post.PromotedPost;
import com.electro.phase1.models.node.user.BusinessUser;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.PostScore;
import com.electro.phase1.util.Suggestion;

public class PostUserConnection extends connection<User, Post> {

    public PostUserConnection(User obj1, Post obj2) {
        super(obj1, obj2);
    }

    public static Set<Post> getPosts(Long userId) {
        Set<Post> result = new LinkedHashSet<>();
//        // TODO run a query on the database and get posts;
//        result.add(new Post("kos mikham man borna am vali"));
//        result.add(new Post(
//                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
//        result.add(new Post("vay daram mimiram"));
//        for (int i = 0; i < 10; i++) {
//            result.add(new Post(String.valueOf(i)));
//        }
        try {
            result.addAll(PostDB.getPostsByUS_ID(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Set<Post> getFeed(Long userId) {
        Set<Post> result = new LinkedHashSet<>();
        // TODO run a query on the database and get posts
        try {
            result.addAll(PostDB.getFeed(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static User getUser(Post postId) {
//        return (new NormalUser("",""));
//
//    }

    public static Set<Post> getExplore(User user) { // TODO: find appropriate posts to show
        ArrayList<PostScore> postScores = PostScore.postScores(new ArrayList<Post>(Suggestion.setScoreForPosts(user)),
                user);
        Collections.sort(postScores);
        return new LinkedHashSet<Post>(PostScore.getPosts(postScores));
        // return getFeed(user.getId());
    }
}