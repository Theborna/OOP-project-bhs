package com.project.util;

import java.util.ArrayList;

import com.project.models.node.user.User;

public class UserScore implements Comparable<UserScore>{
    private User user;
    private int score;

    public UserScore(User user, int score) {
        this.user = user;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    // public Static ArrayList<Post> getPosts(ArrayList<PostScore> postScores) {
    // ArrayList<Post> posts = new ArrayList<Post>();
    // for (PostScore postScore : postScores) {
    // posts.add(postScore.getPost());
    // }
    // return posts;
    // }

    public static ArrayList<User> getUsers(ArrayList<UserScore> userScores) {
        ArrayList<User> users = new ArrayList<User>();
        for (UserScore userScore : userScores) {
            users.add(userScore.getUser());
        }
        return users;
    }

    public static ArrayList<UserScore> userScores(ArrayList<User> users, User user) {
        ArrayList<UserScore> userScores = new ArrayList<UserScore>();
        for (User user2 : users) {
            userScores.add(new UserScore(user2, Suggestion.UserScore(user, user2)));
        }
        return userScores;
    }

    @Override
    public int compareTo(UserScore arg0) {
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
