package com.electro.views;

import com.electro.controllers.views.SettingsController;
import com.electro.phase1.models.node.user.User;

public class SettingsView extends inPane {

    private SettingsController controller;

    public SettingsView() {
        super();
        controller = getController("settings");
    }

    public SettingsView withUser(User user) {
        controller.withUser(user).init();
        return this;
    }
}
