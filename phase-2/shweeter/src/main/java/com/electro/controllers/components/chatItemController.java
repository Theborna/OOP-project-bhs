package com.electro.controllers.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class chatItemController {

    @FXML
    private Circle glow;

    @FXML
    private ImageView ivImage;

    @FXML
    private Label lblLastMessage;

    @FXML
    private Label lblLastSender;

    @FXML
    private Label lblName;

    @FXML
    void glowStart(MouseEvent event) {

    }

}