package com.project.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.project.controllers.ExploreController;
import com.project.models.connection.Like;
import com.project.models.connection.PostUserConnection;
import com.project.models.connection.UserFollowingConnection;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;

public class Suggestion {
    public static Set<Post> setScoreForPosts(User user) {
        Set<UserFollowingConnection> Followings = UserFollowingConnection.getFollowings(user);
        Set<Post> Posts = new LinkedHashSet<Post>();
        for (UserFollowingConnection following : Followings) {
            Set<Post> followingLiked = following.getObj2().getPosts();
            for (Post post : followingLiked) {
                if (PostUserConnection.getUser(post.getId()).isPublic()) {
                    Posts.add(post);
                }
            }
            Set<UserFollowingConnection> followingsFollowings = UserFollowingConnection
                    .getFollowings(following.getObj2());
            for (UserFollowingConnection followingsFollowing : followingsFollowings) {
                if (followingsFollowing.getObj2().isPublic()) {
                    Posts.addAll(followingsFollowing.getObj2().getPosts());
                }
            }
            Posts.addAll(following.getObj2().getPosts());
        }
        // ExploreController.setPosts(Posts);
        return Posts;
    }

    public static int PostsScore(User user, Post post) {
        int a = 0;
        Set<User> usersWhoLiked = new HashSet<>();// = Like.getUsers(post);//TODO:fix
        Set<UserFollowingConnection> Followings = UserFollowingConnection.getFollowings(user);
        User UserWhoPosted = PostUserConnection.getUser(post.getId());
        for (UserFollowingConnection user2 : Followings) {
            if (usersWhoLiked.contains(user2.getObj2())) {
                a += (int) (20.0 * (((double) (user2.getPromoIndex())) / 100.0));
            }
            if (UserFollowingConnection.Follows(user2.getObj2(), UserWhoPosted)) {
                a += (int) (5.0 * (((double) (user2.getPromoIndex())) / 100.0));
            }
        }
        return a;
    }

    public static int UserScore(User user, User user2) {
        int a = 0;
        Set<UserFollowingConnection> followings = UserFollowingConnection.getFollowings(user);
        Set<UserFollowingConnection> followers = UserFollowingConnection.getFollowers(user2);
        for (UserFollowingConnection followingConnection : followings) {
            for (UserFollowingConnection followerConnection : followers) {
                if (followerConnection.getObj1() == followingConnection.getObj2()) {
                    a += (int) (20.0 * (((double) (followingConnection.getPromoIndex())) / 100.0));
                }
            }
        }
        return a;
    }
}
