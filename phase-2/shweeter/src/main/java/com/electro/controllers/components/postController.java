package com.electro.controllers.components;

import com.electro.phase1.models.node.post.Post;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class postController {

    @FXML
    private Button btnComment, btnDislike, btnLike, btnShare;

    @FXML
    private Text content;

    @FXML
    private ImageView ivProfilePic;

    @FXML
    private Label lblLikes, lblName;

    public void initialize(String text, String name, int likes) {
        content.setText(text);
        lblName.setText(name);
        lblLikes.setText(String.valueOf(likes));
    }

}
