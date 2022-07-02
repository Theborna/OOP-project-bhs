package com.project.models.node.user;

public class BusinessUser extends User {

    private String businessType;

    public BusinessUser(String username, String password) {
        super(username, password);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void Post(com.project.models.node.Post post) {
        // TODO Auto-generated method stub

    }

    public String getBusinessType() {
        return businessType;
    }
}
