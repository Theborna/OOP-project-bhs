package com.electro.controllers.components;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.electro.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class chatItemController implements Initializable {

    @FXML
    private Circle glow;

    @FXML
    private ImageView ivImage;

    @FXML
    private Label lblLastMessage;

    @FXML
    private Label lblLastSender;

    @FXML
    private Label lblName, lblDate;

    @FXML
    private AnchorPane mainPane;

    private String date, name, message, sender;
    private double width;

    @FXML
    void glowStart(MouseEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        date = lblDate.getText();
        sender = lblLastSender.getText();
        message = lblLastMessage.getText();
        name = lblName.getText();
        checkSize();
    }

    public void checkSize() {
        if (mainPane == null)
            return;
        width = mainPane.getWidth();
        if (width == 0)
            width = 300;
        if (width < 150) {
            lblDate.setText("");
            lblName.setText("");
            lblLastMessage.setText("");
            lblLastSender.setText("");
        } else {
            lblName.setText(name);
            lblLastMessage.setText(message);
            lblLastSender.setText(sender);
        }
        if (width < 250)
            lblDate.setText("...");
        else
            lblDate.setText(date);
    }

}