package com.electro.views;

import java.io.IOException;
import java.util.ArrayList;

import com.electro.App;
import com.electro.controllers.components.postController;
import com.electro.controllers.components.profileController;
import com.electro.controllers.views.ProfilePageController;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ProfileView extends inPane {

    private static ProfileView other;
    private ProfilePageController controller;
    private boolean loaded;

    public ProfileView() {
        super();
    }

    public ProfileView withUser(User user) {
        if (!loaded) {
            controller = getController("profile-page");
            loaded = true;
        }
        controller.initialize(user);
        return this;
    }

    public static ProfileView getOther() {
        if (other == null)
            other = new ProfileView();
        return other;
    }


}
