package com.project.models.connection;

import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.node.Post;
import com.project.models.node.user.User;

public class PostUserConnection extends connection<User, Post> {

    public static Set<Post> getPost(User user) {
        Set<Post> result = new LinkedHashSet<>();
        // run a query on the database and get posts;
        result.add(new Post("kos mikham man borna am vali"));
        result.add(new Post(
                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
        result.add(new Post("vay daram mimiram"));
        for (int i = 0; i < 10; i++) {
            result.add(new Post(String.valueOf(i)));
        }
        return result;
    }

    public static Set<Post> getFeed(User user) {
        Set<Post> result = new LinkedHashSet<>();
        // run a query on the database and get posts;
        result.add(new Post("kos mikham"));
        result.add(new Post(
                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
        result.add(new Post("vay daram mimiram"));
        for (int i = 0; i < 10; i++) {
            result.add(new Post(String.valueOf(i)));
        }
        return result;
    }
}
