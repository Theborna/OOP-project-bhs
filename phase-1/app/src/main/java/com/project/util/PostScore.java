package com.project.util;

import java.util.ArrayList;

import com.project.models.node.post.Post;
import com.project.models.node.user.User;

public class PostScore implements Comparable<PostScore> {
    private Post post;
    private int score;

    public PostScore(Post post, int score) {
        this.post = post;
        this.score = score;
    }

    public Post getPost() {
        return post;
    }

    // public Static ArrayList<Post> getPosts(ArrayList<PostScore> postScores) {
    // ArrayList<Post> posts = new ArrayList<Post>();
    // for (PostScore postScore : postScores) {
    // posts.add(postScore.getPost());
    // }
    // return posts;
    // }

    public static ArrayList<Post> getPosts(ArrayList<PostScore> postScores) {
        ArrayList<Post> posts = new ArrayList<Post>();
        for (PostScore postScore : postScores) {
            posts.add(postScore.getPost());
        }
        return posts;
    }

    public static ArrayList<PostScore> postScores(ArrayList<Post> posts, User user) {
        ArrayList<PostScore> postScores = new ArrayList<PostScore>();
        for (Post post : posts) {
            postScores.add(new PostScore(post, Suggestion.PostsScore(user, post)));
        }
        return postScores;
    }

    @Override
    public int compareTo(PostScore arg0) {
        // if (this.score > arg0.getScore()) {
        // return 1;
        // } else if (this.score < arg0.getScore()) {
        // return -1;
        // } else {
        // return 0;
        // }
        return this.score - arg0.getScore();
    }

    private int getScore() {
        return score;
    }
}
