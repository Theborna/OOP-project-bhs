package com.electro.views.component;

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.controllers.views.MainController;
import com.electro.phase1.models.node.post.PromotedPost;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;
import jfxtras.styles.jmetro.Style;

public class PostStatsPopOver extends PopOver {

    public PostStatsPopOver(Node parent, PromotedPost post) {
        super();
        // clicks = new SimpleIntegerProperty(0);
        try {
            FXMLLoader loader = App.loadFXML("components/profile");
            Parent root = loader.load();
            // root.getStylesheets().add(arg0)
            // clicks.bind(controller.clickProperty());
            if (App.getStyle() == Style.DARK)
                root.getStylesheets().add(MainController.darkPath);
            else
                root.getStylesheets().add(MainController.lightPath);
            super.setContentNode(root);
            super.setAnimated(true);
            super.setFadeInDuration(Duration.seconds(0.7));
            // clicks.addListener((a, b, c) -> {
            // System.out.println("hiiii");
            // });
        } catch (

        IOException e) {
            e.printStackTrace();
        }
        parent.setOnMouseClicked(evt -> {
            if (!this.isShowing()) {
                this.show((Node) evt.getSource());
            } else
                this.hide();
        });
    }
}
