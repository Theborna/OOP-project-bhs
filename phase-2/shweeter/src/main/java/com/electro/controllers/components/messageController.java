package com.electro.controllers.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class messageController{

    @FXML
    private Label date;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label name;

    @FXML
    private ImageView pfp;

    @FXML
    private TextArea text;

    public void initialize() { // will include the important stuff about the actual message
        name.setText(":/borna/");
        // mainPane.setPrefHeight(1000);
    }
}
