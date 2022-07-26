package com.project.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.PostScore;
import com.project.util.Suggestion;

public class ExploreController extends FeedController {
    private static Set<Post> posts = new LinkedHashSet<Post>();

    public static void setPosts(Set<Post> posts2){
        posts=new LinkedHashSet<Post>(posts2);
    }

    public void sortPosts(User user){
        ArrayList<PostScore> postScores=PostScore.postScores(new ArrayList<Post>(posts), user);
        Collections.sort(postScores);
        posts=new LinkedHashSet<Post>(PostScore.getPosts(postScores));
    }
}
