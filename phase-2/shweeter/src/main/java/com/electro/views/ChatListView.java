package com.electro.views;

import java.util.ArrayList;

import com.electro.App;
import com.electro.controllers.components.chatItemController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ChatListView extends VBox {

    private static ChatListView pnChatInstance;
    private boolean toggleMany;

    private ChatListView() {
    }

    public void addAll() {
        System.out.println("loading the chats...");
        ArrayList<Node> nodes = new ArrayList<Node>();
        Thread thread = new Thread(() -> {
            final int size = 20;
            try {
                for (int i = 0; i < size; i++) {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("components/chatItem.fxml"));
                    nodes.add(loader.load());
                    super.getChildren().add(nodes.get(i));
                    super.widthProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> arg0, Number arg1,
                                Number arg2) {
                            ((chatItemController) loader.getController()).checkSize();
                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.run();
    }

    public static ChatListView getPnChatInstance() {
        if (pnChatInstance == null) {
            pnChatInstance = new ChatListView();
            pnChatInstance.toggleMany = false;
        }
        return pnChatInstance;
    }

}
