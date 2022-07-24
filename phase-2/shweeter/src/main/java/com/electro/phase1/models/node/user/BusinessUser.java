package com.electro.phase1.models.node.user;

import java.time.LocalDateTime;

public class BusinessUser extends User {
    
    private String businessType;

    public BusinessUser(String username, String password) {
        super(username, password);
        //TODO Auto-generated constructor stub
    }


    @Override
    public void Post(com.electro.phase1.models.node.post.Post post) {
        // TODO Auto-generated method stub

    }

    public String getBusinessType() {
        return businessType;
    }
}
