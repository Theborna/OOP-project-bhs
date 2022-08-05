package com.electro.views;

import com.electro.controllers.views.ProfileInfoController;
import com.electro.phase1.models.node.user.User;

public class profileInfoView extends inPane {

    private ProfileInfoController controller;

    public profileInfoView() {
        super();
    }

    public profileInfoView withUser(User user) {
        controller = getController("prof-info");
        controller.initialize(user);
        return this;
    }

}
