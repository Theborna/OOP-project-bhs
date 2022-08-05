package com.electro.views;

import java.util.function.Consumer;

import com.electro.controllers.views.ProfilePageController;
import com.electro.phase1.models.node.user.User;

public class ProfileView extends inPane {

    private ProfilePageController controller;
    private boolean loaded;
    private User user;

    public ProfileView() {
        super();
    }

    public ProfileView withUser(User user) {
        if (!loaded) {
            controller = getController("profile-page");
            loaded = true;
        }
        this.user = user;
        controller.initialize(user);
        return this;
    }

    public void setOnInfoRequest(Consumer<User> func) {
        controller.getInfoRequestProperty().addListener((a, old, niu) -> {
            System.out.println("ProfileView.setOnInfoRequest()");
            if (niu)
                func.accept(user);
        });
    }
}
