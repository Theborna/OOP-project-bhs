package com.electro.views.component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.controllers.components.profileController;
import com.electro.controllers.views.MainController;
import com.electro.phase1.models.node.user.User;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;
import jfxtras.styles.jmetro.Style;

public class ProfilePopOver extends PopOver {

    private static User selectedUser;
    private static BooleanProperty previewRequest;

    public ProfilePopOver(Node preview, User user) {// TODO: add user to constructor
        super();
        try {
            FXMLLoader loader = App.loadFXML("components/profile");
            Parent root = loader.load();
            // root.getStylesheets().add(arg0)
            profileController controller = loader.getController();
            controller.initialize(user);
            if (App.getStyle() == Style.DARK)
                root.getStylesheets().add(MainController.darkPath);
            else
                root.getStylesheets().add(MainController.lightPath);
            super.setContentNode(root);
            super.setAnimated(true);
            super.setFadeInDuration(Duration.seconds(0.7));
            super.setTitle(user.getUsername());
            controller.clickProperty().addListener((a, b, c) -> {// TODO: make timed
                Boolean bool = c.intValue() % 2 == 0;
                selectedUser = user;
                previewRequest.set(bool);
                if (bool)
                    this.hide();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        preview.setOnMouseClicked(evt -> {
            if (!this.isShowing()) {
                this.show((Node) evt.getSource());
            } else
                this.hide();
        });
        // preview.setOnMouseClicked(evt -> {
        // this.hide();
        // });
    }

    public static BooleanProperty getPreviewRequest() {
        if (previewRequest == null)
            previewRequest = new SimpleBooleanProperty();
        return previewRequest;
    }

    public static User getSelectedUser() {
        return selectedUser;
    }

}