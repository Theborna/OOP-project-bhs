package com.project.models.node.user;

import com.project.models.node.post.NormalPost;
import com.project.models.node.post.Post;
import com.project.util.Log;

public class NormalUser extends User {

    public NormalUser(String username, String password) {
        super(username, password);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void Post(String post, Post inReplyTo) {
        // TODO: add post to DB
        Post newPost = new NormalPost(post,this);
        newPost.setRepliedPost(inReplyTo);
        Log.logger.info("added post: " + newPost.toString());
        newPost.sendToDB();
    }

}
