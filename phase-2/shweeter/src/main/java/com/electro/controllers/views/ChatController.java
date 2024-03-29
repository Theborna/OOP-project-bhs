package com.electro.controllers.views;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.electro.App;
import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Message;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.StdColor;
import com.electro.phase1.util.StdOut;
import com.electro.util.StretchTextArea;
import com.electro.views.ChatListView;
import com.electro.views.FileView;
import com.electro.views.MessageListView;

import de.jensd.shichimifx.utils.SplitPaneDividerSlider;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ChatController implements Initializable {

    @FXML
    private BorderPane bpReply;

    @FXML
    private Button btnChatSettings, btnChatTop, btnMsgFile, btnMsgSend, btnMsgTop;

    @FXML
    private HBox hBoxReply;

    @FXML
    private Label lblRepliedMsg, lblRepliedName;

    @FXML
    private ScrollPane scrollChat, scrollMsg;

    @FXML
    private SplitPane splChat;

    @FXML
    private ToggleButton tglChat, tglMsg;

    @FXML
    private TextArea txtAMessage;

    private ImageNode img;

    private BooleanProperty requestSettingsProperty;

    private void initChatItems() {
        ChatListView.getPnChatInstance().withChat(ChatUserConnection.getChats(User.getCurrentUser().getId()));
        scrollChat.setContent(ChatListView.getPnChatInstance());
    }

    private void initMessages() {
        img = null;
        MessageListView.getInstance().addAll();
        scrollMsg.setContent(MessageListView.getInstance());
        lblRepliedName.textProperty().bind(MessageListView.getInstance().getRepliedName());
        lblRepliedMsg.textProperty().bind(MessageListView.getInstance().getRepliedMsg());
        btnMsgTop.textProperty().bind(Chat.getCurrentName());
        lblRepliedName.textProperty().addListener((a, old, niu) -> {
            if (niu == null || niu.length() == 0)
                bpReply.setTop(null);
            else
                bpReply.setTop(hBoxReply);
        });
        txtAMessage.disableProperty().bind(Chat.getCanSendProperty().not());
        txtAMessage.disableProperty().addListener((a, old, niu) -> {
            if (niu)
                txtAMessage.setPromptText("cannot send message to this chat");
            else
                txtAMessage.setPromptText("your message...");
        });
        MessageListView.getInstance().getEditProperty().addListener((a, old, niu) -> {
            if (niu)
                txtAMessage.setText(MessageListView.getInstance().getEditing().getText());
        });
    }

    @FXML
    private void Scroll(ActionEvent evt) {
        if (evt.getSource() == btnChatTop) {
            scrollTo(scrollChat, true);
        } else if (evt.getSource() == btnMsgTop) {
            scrollTo(scrollMsg, false);
        } else if (evt.getSource() == btnChatSettings) {
            requestSettingsProperty.set(true);
            new Thread(() -> {// to reset stuff after some time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                requestSettingsProperty.set(false);
            }).start();
        }
    }

    private void scrollTo(ScrollPane myScrollPane, boolean top) {
        Animation animation = new Timeline(
                new KeyFrame(
                        Duration.seconds((top ? myScrollPane.getVvalue() : 1 - myScrollPane.getVvalue())
                                * myScrollPane.getHeight() * 0.003),
                        new KeyValue(myScrollPane.vvalueProperty(), top ? 0 : 1, Interpolator.EASE_OUT)));
        animation.play();
    }

    @FXML
    private void cancelReply() {
        MessageListView.getInstance().clearReply();
        img = null;
        bpReply.setBottom(null);
    }

    @FXML
    private void msgButton(ActionEvent event) {
        Object o = event.getSource();
        if (!(o instanceof Button))
            return;
        Button btn = (Button) o;
        if (btn == btnMsgSend) {
            StringBuilder sb = new StringBuilder();
            sb.append(txtAMessage.getText());
            post(sb, MessageListView.getInstance().getRepliedTo(), MessageListView.getInstance().getEditing(), img);
            txtAMessage.setText("");
            img = null;
            bpReply.setBottom(null);
        } else if (btn == btnMsgFile) {
            img = new ImageNode(App.getPicChooser().showOpenDialog(App.getScene().getWindow()).getPath());
            bpReply.setBottom(new FileView(img));
        }
    }

    public void post(StringBuilder sb, Message inReply, Message edit, ImageNode img) {
        if (sb == null)
            return;
        Message message = null;
        if (edit == null)
            message = new Message(sb.toString(), User.getCurrentUser(), Chat.getCurrent());
        else {
            message = edit;
            message.setText(sb.toString());
        }
        // TODO: set message data
        message.setReplyTo(inReply);
        message.setMd(img);
        User.getCurrentUser().sendMessage(message);
        // println("message posted successfully", StdColor.GREEN);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initChatItems();
        initMessages();
        coupleButtonAndSlider(tglChat, splChat, 0, SplitPaneDividerSlider.Direction.RIGHT);
        coupleButtonAndSlider(tglMsg, splChat, 0, SplitPaneDividerSlider.Direction.LEFT);
        StretchTextArea.bind(txtAMessage);
        requestSettingsProperty = new SimpleBooleanProperty(false);
    }

    private void coupleButtonAndSlider(ToggleButton button, SplitPane pane, int division,
            SplitPaneDividerSlider.Direction direction) {
        if (button == null)
            button = new ToggleButton();
        SplitPaneDividerSlider slider = new SplitPaneDividerSlider(pane, division, direction);
        button.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            slider.setAimContentVisible(t1);
        });

    }

    public BooleanProperty getRequestSettingsProperty() {
        return requestSettingsProperty;
    }
}
