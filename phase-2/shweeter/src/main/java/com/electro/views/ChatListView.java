package com.electro.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.electro.App;
import com.electro.controllers.components.chatItemController;
import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.user.User;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class ChatListView extends VBox {

    private static ChatListView pnChatInstance, pnForwardInstance;
    private boolean toggleMany;
    private List<BooleanProperty> selected;
    private List<Chat> chats;
    private ArrayList<Node> nodes;
    private static Thread updater;

    private ChatListView() {
    }

    public void withChat(Collection<Chat> chat) {
        System.out.println("loading the chats...");
        super.getChildren().clear();
        chats = new ArrayList<>();
        nodes = new ArrayList<Node>();
        if (selected == null)
            selected = new ArrayList<>();
        selected.clear();
        chats.addAll(chat);
        Thread thread = new Thread(() -> {
            addChats(chats);
        });
        thread.start();
        updater = new Thread(() -> {
            try {
                Thread.sleep(1000);
                ArrayList<Chat> newChat = new ArrayList<Chat>();
                newChat.addAll(ChatUserConnection.getChats(User.getCurrentUser().getId()));
                newChat.removeAll(chats);
                addChats(newChat);
                chats.addAll(newChat);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        updater.setDaemon(true);
        updater.start();
    }

    private void addChats(List<Chat> chats) {
        try {
            for (int i = 0; i < chats.size(); i++) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("components/chatItem.fxml"));
                Node node = loader.load();
                nodes.add(node);
                BooleanProperty property = new SimpleBooleanProperty(false);
                selected.add(property);
                chatItemController controller = loader.getController();
                final int j = i;
                Platform.runLater(() -> {
                    super.getChildren().add(node);
                    controller.initialize(chats.get(j));
                });
                super.widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> arg0, Number arg1,
                            Number arg2) {
                        ((chatItemController) loader.getController()).checkSize();
                    }
                });
                ToggleButton btn = lookupButton(node);
                property.bindBidirectional(btn.selectedProperty());

                if (!toggleMany)
                    btn.selectedProperty().addListener(new ChangeListener<Boolean>() {

                        @Override
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                            if (arg2) {
                                for (Node _node : nodes)
                                    if (_node != node)
                                        lookupButton(_node).setSelected(false);
                                Chat.LogToChat(controller.getChat().getId());
                                MessageListView.getInstance().addAll();
                            }
                        }

                    });
                Thread.sleep(60); // sleep
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ToggleButton lookupButton(Node node) {
        if (node instanceof ToggleButton) {
            return (ToggleButton) node;
        }
        if (node instanceof Parent) {
            ObservableList<Node> children = ((Parent) node).getChildrenUnmodifiable();
            for (Node child : children) {
                ToggleButton scrollBar = lookupButton(child);
                if (scrollBar != null) {
                    return scrollBar;
                }
            }
        }
        return null;
    }

    public static ChatListView getPnChatInstance() {
        if (pnChatInstance == null) {
            pnChatInstance = new ChatListView();
            pnChatInstance.toggleMany = false;
        }
        return pnChatInstance;
    }

    public static ChatListView getPnForwardInstance() {
        if (pnForwardInstance == null) {
            pnForwardInstance = new ChatListView();
            pnForwardInstance.toggleMany = true;
        }
        return pnForwardInstance;
    }

    public void forward(boolean forward) {
        if (forward) {
            for (int i = 0; i < chats.size(); i++) {
                if (selected.get(i).get()) {
                    User.getCurrentUser().sendMessage(
                            MessageListView.getInstance().getForwarding().forwardFrom(User.getCurrentUser(),
                                    chats.get(i)));
                    System.out.println(chats.get(i) + " " + MessageListView.getInstance().getForwarding());
                }
            }
        }
        for (

        BooleanProperty property : selected)
            property.set(false);
    }

}
