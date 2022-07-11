package com.electro.views;

import java.util.ArrayList;

import com.electro.App;
import com.electro.controllers.components.messageController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MessageListView extends VBox {
    private static MessageListView instance;

    private MessageListView() {
    }

    public void addAll() {
        System.out.println("loading the messages...");
        ArrayList<Node> nodes = new ArrayList<Node>();
        final int size = 20;
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10);
                System.out.println("h");
                for (int i = 0; i < size; i++) {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("components/message.fxml"));
                    nodes.add(loader.load());
                    ((messageController) loader.getController()).initialize();
                    super.getChildren().add(nodes.get(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.run();
    }

    public static MessageListView getInstance() {
        if (instance == null)
            instance = new MessageListView();
        return instance;
    }
}
