package com.electro.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.electro.App;
import com.electro.controllers.components.messageController;
import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.connection.MessageConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.Message;
import com.electro.phase1.models.node.node;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MessageListView extends VBox {
    private static MessageListView instance;
    private List<Message> messages;
    private List<Node> nodes;
    private List<BooleanProperty> replied, forwarded;
    private Message repliedTo, forwarding;
    private StringProperty repliedName, repliedMsg;
    private BooleanProperty forwardingProperty;
    private Thread updater;

    private MessageListView() {
    }

    public void addAll() {
        System.out.println("loading the messages...");
        messages = new ArrayList<>();
        messages.addAll(MessageConnection.getMessages(Chat.getCurrent().getId()));
        init();
        clearChildren();
        load();
    }

    private void init() {
        if (repliedMsg == null)
            repliedMsg = new SimpleStringProperty();
        if (repliedName == null)
            repliedName = new SimpleStringProperty();
        if (nodes == null)
            nodes = new ArrayList<>();
        if (replied == null)
            replied = new ArrayList<>();
        if (forwarded == null)
            forwarded = new ArrayList<>();
        if (forwardingProperty == null)
            forwardingProperty = new SimpleBooleanProperty(false);
    }

    private void load() {
        nodes.clear();
        replied.clear();
        forwarded.clear();
        forwardingProperty.set(false);
        Thread thread = new Thread(() -> {
            try {
                addMessages(messages);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        updater = new Thread(() -> { // TODO: make this with sockets
            while (true) {
                try {

                    Thread.sleep(200);// the apparent ping
                    ArrayList<Message> newMsg = new ArrayList<Message>();
                    newMsg.addAll(MessageConnection.getMessages(Chat.getCurrent().getId()));
                    newMsg.removeAll(messages);
                    addMessages(newMsg);
                    messages.addAll(newMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        updater.setDaemon(true);
        updater.start();
    }

    private void addMessages(List<Message> messages) throws IOException, InterruptedException {
        for (int i = 0; i < messages.size(); i++) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("components/message.fxml"));
            Node node = loader.load();
            messageController controller = loader.getController();
            nodes.add(node);
            BooleanProperty replyProp,
                    forwardProp;
            replied.add(replyProp = new SimpleBooleanProperty(false));
            forwarded.add(forwardProp = new SimpleBooleanProperty(false));
            final int j = i;
            Platform.runLater(() -> {
                controller.initialize(messages.get(j));
                super.getChildren().add(node);
            });
            replyProp.bindBidirectional(controller.repliedProperty());
            replyProp.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                    // clearReply();
                    // replied.get(j).set(arg2);
                    for (int k = 0; k < replied.size(); k++)
                        if (k != j)
                            replied.get(k).set(false);
                    // System.out.println(replied);
                    if (arg2) {
                        repliedTo = messages.get(j);
                        repliedName.set(repliedTo.getSender().getUsername());
                        repliedMsg.set(repliedTo.getText());
                    } else {
                        repliedTo = null;
                        repliedName.set("");
                        repliedMsg.set("");
                    }
                    System.out.println(repliedTo);
                    System.out.println(repliedMsg.get());
                }
            });
            forwardProp.bindBidirectional(controller.getForwardProperty());
            forwardProp.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                    for (int k = 0; k < messages.size(); k++)
                        if (k != j)
                            forwarded.get(k).set(false);
                    if (arg2) {
                        forwarding = messages.get(j);
                        forwardingProperty.set(true);
                    } else
                        forwarding = null;
                    System.out.println(forwarding);
                }
            });
            Thread.sleep(60);
        }
    }

    private void clearChildren() {
        for (Node node : super.getChildren())
            if (node instanceof BorderPane)
                Platform.runLater(() -> super.getChildren().remove(node));
    }

    public BooleanProperty getForwardingProperty() {
        if (forwardingProperty == null)
            forwardingProperty = new SimpleBooleanProperty(false);
        return forwardingProperty;
    }

    public void clearReply() {
        repliedTo = null;
        repliedName.set("");
        repliedMsg.set("");
    }

    public Message getForwarding() {
        return forwarding;
    }

    public void hasReply() {

    }

    public StringProperty getRepliedMsg() {
        return repliedMsg;
    }

    public StringProperty getRepliedName() {
        return repliedName;
    }

    public Message getRepliedTo() {
        if (repliedTo == null)
            return null;
        return repliedTo;
    }

    public static MessageListView getInstance() {
        if (instance == null)
            instance = new MessageListView();
        return instance;
    }

    public void update() {
    }
}
