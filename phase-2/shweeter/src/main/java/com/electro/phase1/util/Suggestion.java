package com.electro.phase1.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.electro.phase1.models.connection.Like;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.connection.UserFollowingConnection;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

public class Suggestion {
    public static Set<Post> setScoreForPosts(User user) {
        Set<UserFollowingConnection> Followings = UserFollowingConnection.getFollowings(user);
        Set<Post> Posts = new LinkedHashSet<Post>();
        for (UserFollowingConnection following : Followings) {
            Set<Post> followingLiked = following.getObj2().getPosts();
            for (Post post : followingLiked) {
                if (post.getSender().isPublic()) {
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
        Set<Like> likes = Like.getUsers(post);
        Set<User> usersWhoLiked = new HashSet<>();
        for (Like like : likes) {
            usersWhoLiked.add(like.getObj2());
        }
        Set<UserFollowingConnection> Followings = UserFollowingConnection.getFollowings(user);
        User UserWhoPosted = post.getSender();
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
                if (followerConnection.getObj1().equals(followingConnection.getObj2())) {
                    a += (int) (20.0 * (((double) (followingConnection.getPromoIndex())) / 100.0));
                }
            }
        }
        return a;
    }

    public static void likedSuggestedPost(User user, Post post) {
        Set<Like> usersWhoLiked = Like.getUsers(post);
        Set<User> usersWhoLiked2 = new LinkedHashSet<User>();
        for (Like like : usersWhoLiked) {
            usersWhoLiked2.add(like.getObj2());
        }
        Set<UserFollowingConnection> followings = UserFollowingConnection.getFollowings(user);
        for (UserFollowingConnection connection : followings) {
            if (usersWhoLiked2.contains(connection.getObj2())) {
                connection.increasePromoIndex(40);
            }
        }
    }

    public static void dislikedSuggestedPost(User user, Post post) {
        Set<Like> usersWhoLiked = Like.getUsers(post);
        Set<User> usersWhoLiked2 = new LinkedHashSet<User>();
        for (Like like : usersWhoLiked) {
            usersWhoLiked2.add(like.getObj2());
        }
        Set<UserFollowingConnection> followings = UserFollowingConnection.getFollowings(user);
        for (UserFollowingConnection connection : followings) {
            if (usersWhoLiked2.contains(connection.getObj2())) {
                connection.decreasePromoIndex(40);
            }
        }
    }

    public static void likedSuggestedUser(User user, User user2) {
        Set<UserFollowingConnection> followers = UserFollowingConnection.getFollowers(user2);
        Set<UserFollowingConnection> followings = UserFollowingConnection.getFollowings(user);
        for (UserFollowingConnection connection : followings) {
            for (UserFollowingConnection connection2 : followers) {
                if (connection.getObj2().equals(connection2.getObj1())) {
                    connection.increasePromoIndex(10);
                }
            }
        }
    }

    public static void dislikedSuggestedUser(User user, User user2) {
        Set<UserFollowingConnection> followers = UserFollowingConnection.getFollowers(user2);
        Set<UserFollowingConnection> followings = UserFollowingConnection.getFollowings(user);
        for (UserFollowingConnection connection : followings) {
            for (UserFollowingConnection connection2 : followers) {
                if (connection.getObj2().equals(connection2.getObj1())) {
                    connection.decreasePromoIndex(10);
                }
            }
        }
    }

    public static Set<User> setScoreForUsers(User user) {
        Set<User> users = new LinkedHashSet<User>();
        Set<UserFollowingConnection> Followings = UserFollowingConnection.getFollowings(user);
        for (UserFollowingConnection connection : Followings) {
            Set<UserFollowingConnection> followingsFollowings = UserFollowingConnection
                    .getFollowings(connection.getObj2());
            for (UserFollowingConnection connectionConnection : followingsFollowings) {
                users.add(connectionConnection.getObj2());
            }
        }
        return users;
    }
}
