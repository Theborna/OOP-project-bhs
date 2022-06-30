package com.electro.controllers.components;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class chatItemController implements Initializable {

    private ScaleTransition grow;

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
    private void glowStart(MouseEvent event) {
        glow.setLayoutX((event).getX() - glow.getRadius());
        glow.setLayoutY((event).getY() - glow.getRadius());
        glow.setRadius(1);
        grow.play();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        grow = new ScaleTransition(Duration.millis(1000), glow);
        grow.setByX(40f);
        grow.setByY(40f);
        grow.setCycleCount(2);
        grow.setAutoReverse(true);
    }

}