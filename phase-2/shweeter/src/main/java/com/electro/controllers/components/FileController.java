package com.electro.controllers.components;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Media;
import com.electro.phase1.models.node.Video;
import com.electro.phase1.models.node.node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class FileController {

    @FXML
    private ImageView ivImage;

    @FXML
    private Button btnShow;

    public void initialize(Media media) {
        Node node = null;
        if (media instanceof ImageNode) {
            ivImage.setImage(((ImageNode) media).getImage());
            node = new ImageView(((ImageNode) media).getImage());
        } else if (media instanceof Video) {
            ivImage.setImage(App.getImage("images/icons8_video_96px.png"));
        }
        // TODO: the rest
        PopOver popOver = new PopOver(node);
        btnShow.setOnAction(evt -> {
            if (popOver.isShowing())
                popOver.hide();
            else
                popOver.show(btnShow);
        });
    }

}
