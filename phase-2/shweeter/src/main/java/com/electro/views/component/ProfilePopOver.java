package com.electro.views.component;

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.controllers.components.profileController;
import com.electro.controllers.views.MainController;
import com.electro.phase1.models.node.user.User;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;
import jfxtras.styles.jmetro.Style;

public class ProfilePopOver extends PopOver {

    private IntegerProperty clicks;

    public ProfilePopOver(Node preview, User user) {// TODO: add user to constructor
        super();
        clicks = new SimpleIntegerProperty(0);
        try {
            FXMLLoader loader = App.loadFXML("components/profile");
            Parent root = loader.load();
            // root.getStylesheets().add(arg0)
            profileController controller = loader.getController();
            controller.initialize(user);
            clicks.bind(controller.clickProperty());
            if (App.getStyle() == Style.DARK)
                root.getStylesheets().add(MainController.darkPath);
            else
                root.getStylesheets().add(MainController.lightPath);
            super.setContentNode(root);
            super.setAnimated(true);
            super.setFadeInDuration(Duration.seconds(0.7));
            super.setTitle(user.getUsername());
            clicks.addListener((a, b, c) -> {
                System.out.println("hiiii");
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

    // TODO : make showing this timed
    // public class openThread extends Thread {

    // @Override
    // public void run() {
    // try {
    // Thread.sleep(1000);
    // } catch (InterruptedException e) {
    // return;
    // }

    // }

    // }
}