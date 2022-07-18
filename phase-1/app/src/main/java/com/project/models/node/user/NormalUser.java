package com.project.models.node.user;

import java.sql.Date;

public class NormalUser extends User {



    public NormalUser(Date creationDate, String username, String password) {
        super(creationDate, username, password);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void Post(com.project.models.node.Post post) {
        // TODO Auto-generated method stub

    }

}
