package com.electro.views;

import com.electro.controllers.views.ExploreController;
import com.electro.phase1.models.node.user.User;

import javafx.scene.layout.AnchorPane;

public class ExploreView extends inPane {

    private ExploreController controller;

    public ExploreView() {
        super();
        controller = getController("explore-page");
        refresh();
    }

    public ExploreView refresh() {
        controller.initialize(User.getCurrentUser());
        return this;
    }

}
