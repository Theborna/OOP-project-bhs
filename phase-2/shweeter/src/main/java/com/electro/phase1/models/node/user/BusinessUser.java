package com.electro.phase1.models.node.user;

import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.post.PromotedPost;
import com.electro.phase1.util.Log;

public class BusinessUser extends User {
    
    private String businessType;

    public BusinessUser(String username, String password) {
        super(username, password);
        setUserType(1);
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
