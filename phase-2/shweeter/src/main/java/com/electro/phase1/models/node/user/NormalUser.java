package com.electro.phase1.models.node.user;

import com.electro.phase1.models.node.post.NormalPost;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.util.Log;

public class NormalUser extends User {

    public NormalUser(String username, String password) {
        super(username, password);
        setUserType(0);
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