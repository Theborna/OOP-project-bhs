package com.electro.views.component;

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.controllers.components.postStatController;
import com.electro.controllers.views.MainController;
import com.electro.phase1.models.node.post.PromotedPost;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import jfxtras.styles.jmetro.Style;

public class PostStatsPopOver extends PopOver {

    public PostStatsPopOver(Button parent, PromotedPost post) {
        try {
            FXMLLoader loader = App.loadFXML("components/post-stats");
            Parent root = loader.load();
            postStatController controller = loader.getController();
            controller.initialize(post);
            // super.setStyle("-fx-background-color: transparent");
            // if (App.getStyle() == Style.DARK)
            // root.getStylesheets().add(MainController.darkPath);
            // else
            // root.getStylesheets().add(MainController.lightPath);
            super.setContentNode(root);
            super.setAnimated(true);
            super.setFadeInDuration(Duration.seconds(0.7));
            super.setTitle("post statistics");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parent.setOnAction(evt -> {
            if (!this.isShowing()) {
                this.show((Node) evt.getSource());
            } else
                this.hide();
        });
    }
}
