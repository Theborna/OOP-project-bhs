package com.project.models.connection;


import java.util.LinkedHashSet;
import java.util.Set;

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
        // TODO run a query on the database and get posts;
//        result.add(new Post("kos mikham"));
//        result.add(new Post(
//                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
//        result.add(new Post("vay daram mimiram"));
//        for (int i = 0; i < 10; i++) {
//            result.add(new Post(String.valueOf(i)));
//        }
        return result;
    }

    public static Set<Like> getUsers(Post post) {
        Set<Like> result = new LinkedHashSet<>();
        // TODO run a query on the database and get Users;
//        result.add(new NormalUser("sex", "anal"));
//        result.add(new NormalUser("sex", "vaginal"));
//        for (int i = 0; i < 10; i++) {
//            result.add(new NormalUser(Integer.toString(i), Integer.toString((2 * i))));
//        }
        return result;
    }

    @Override
    public void sendToDB() {

    }
}
