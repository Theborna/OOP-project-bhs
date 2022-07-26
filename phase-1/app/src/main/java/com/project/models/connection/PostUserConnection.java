package com.project.models.connection;

import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.node.post.Post;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

public class PostUserConnection extends connection<User, Post> {

    public PostUserConnection(User obj1, Post obj2) {
        super(obj1, obj2);
    }

    public static Set<Post> getPosts(Long userId) {
        Set<Post> result = new LinkedHashSet<>();
        // TODO run a query on the database and get posts;
        result.add(new Post("kos mikham man borna am vali"));
        result.add(new Post(
                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
        result.add(new Post("vay daram mimiram"));
        for (int i = 0; i < 10; i++) {
            result.add(new Post(String.valueOf(i)));
        }
        return result;
    }

    public static Set<Post> getFeed(Long userId) {
        Set<Post> result = new LinkedHashSet<>();
        // TODO run a query on the database and get posts;
        result.add(new Post("kos mikham"));
        result.add(new Post(
                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
        result.add(new Post("vay daram mimiram"));
        for (int i = 0; i < 10; i++) {
            result.add(new Post(String.valueOf(i)));
        }
        return result;
    }

    public static User getUser(Long postId) { // in chi hast??? //khodet fekr mikoni chie borna jan? post ro midi mige male che userie
        // Set<User> result = new LinkedHashSet<>();
        // TODO run a query on the database and get Users;
        return(new NormalUser("sex", "anal"));
        // result.add(new NormalUser("sex", "vaginal"));
        // for (int i = 0; i < 10; i++) {
        //     result.add(new NormalUser(Integer.toString(i), Integer.toString((2 * i))));
        // }
        // return result;
    }

    public static Set<Post> getExplore(User user) { // TODO: find appropriate posts to show
        return getFeed(user.getId());
    }
}
