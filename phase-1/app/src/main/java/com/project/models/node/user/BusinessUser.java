package com.project.models.node.user;

import com.project.models.node.post.Post;
import com.project.models.node.post.PromotedPost;
import com.project.util.Log;

public class BusinessUser extends User {
    
    private String businessType;

    public BusinessUser(String username, String password) {
        super(username, password);
        //TODO Auto-generated constructor stub
    }


    @Override
    public void Post(String post, Post inReplyTo) {
        // TODO Auto-generated method stub
        Post newPost = new PromotedPost(post,this);
        newPost.setRepliedPost(inReplyTo);
        Log.logger.info("added post: " + newPost.toString());
        newPost.sendToDB();
    }

    public String getBusinessType() {
        return businessType;
    }
}
