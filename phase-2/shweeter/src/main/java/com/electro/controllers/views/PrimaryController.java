package com.electro.controllers.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.electro.App;
import com.electro.controllers.components.messageController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {
    @FXML
    private VBox chats, messages;

    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initChatItems();
        initMessages();
    }

    private void initChatItems() {
        System.out.println("loading the chats...");
        Node[] nodes = new Node[20];
        try {
            for (int i = 0; i < nodes.length; i++) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("components/chatItem.fxml"));
                nodes[i] = loader.load();
                chats.getChildren().add(nodes[i]);
                final int j = i;
                nodes[j].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color: #43474D");
                });
                nodes[j].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color: #282E33");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMessages() {
        System.out.println("loading the messages...");
        Node[] nodes = new Node[10];
        try {
            for (int i = 0; i < nodes.length; i++) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("components/message.fxml"));
                nodes[i] = loader.load();
                ((messageController) loader.getController()).initialize();
                messages.getChildren().add(nodes[i]);
                final int j = i;
                nodes[j].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color: #32353B");
                });
                nodes[j].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color: #36393F");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
