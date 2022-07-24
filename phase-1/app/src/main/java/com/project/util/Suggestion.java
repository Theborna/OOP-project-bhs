package com.project.util;

import java.util.ArrayList;
import java.util.Set;

import com.project.models.connection.Like;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;

public class Suggestion {
    public void setScoreForPosts(User user){

    }

    public void setScoreForPost(User user,Post post){
        Set<User> usersWhoLiked=Like.getUsers(post);
        Set<User> Followings=User.
        for () {
            
        }
    }
}
