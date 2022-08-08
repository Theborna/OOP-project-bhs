package com.electro.controllers.components;

import java.sql.SQLException;

import com.electro.database.ChatDB;
import com.electro.database.MessageDB;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Message;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.format;
import com.electro.views.FileView;
import com.electro.views.component.ProfilePopOver;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class messageController {

    @FXML
    private Label date, lblForward, lblForwardedFrom;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label name, lblReplyName, lblReplyMsg;

    @FXML
    private ImageView pfp;

    @FXML
    private TextFlow msgText;

    @FXML
    private Text txtMsg;

    @FXML
    private HBox hBoxReply;

    private BooleanProperty repliedProperty, forwardProperty, editProperty;
    private Message message;

    public void initialize(Message message) { // will include the important stuff about the actual message
        this.message = message;
        name.setText(":/" + message.getSender().getUsername() + "/");
        // name.setTextFill(message.getSender().getNameColor());
        txtMsg.setText(message.getText());
        date.setText(format.SimpleDate(message.getLastModifiedDate()));
        lblForward.setText(message.getAuthor().getUsername());
        lblForwardedFrom.visibleProperty().bindBidirectional(lblForward.visibleProperty());
        lblForward.setVisible(message.isForwarded());
        System.out.println(message.isForwarded());
        if (message.getReplyTo() != null) {
            hBoxReply.setVisible(true);
            lblReplyName.textProperty().set(message.getReplyTo().getSender().getUsername());
            lblReplyMsg.textProperty().set(message.getReplyTo().getText());
        } else
            mainPane.setTop(null);
        new ProfilePopOver(pfp, message.getSender());
        setContext();
        Image img = ((ImageNode) message.getSender().getProfilePhoto()).getImage();
        double height = pfp.getFitHeight();
        pfp.setImage(img = ((ImageNode) message.getSender().getProfilePhoto())
                .getImage(height * img.getWidth() / img.getHeight(), height));
        pfp.setClip(new Circle(pfp.getFitWidth() / 2, pfp.getFitHeight() / 2, pfp.getFitHeight() / 2));
        setMedia();
    }

    private void setMedia() {
        if (message.getMd() == null)
            mainPane.setBottom(null);
        else
            mainPane.setBottom(new FileView(message.getMd()));
    }

    private void setContext() {
        // Creating a context menu
        ContextMenu contextMenu = new ContextMenu();
        // Creating the menu Items for the context menu
        MenuItem item1 = new MenuItem("reply");
        MenuItem item2 = new MenuItem("forward");
        MenuItem item3 = new MenuItem("copy");
        MenuItem item4 = new MenuItem("edit");
        MenuItem item5 = new MenuItem("delete");
        contextMenu.getItems().addAll(item1, item2, item3);
        if (message.getAuthor().equals(User.getCurrentUser())) {
            contextMenu.getItems().add(item4);
            contextMenu.getItems().add(item5);
        }
        msgText.setOnContextMenuRequested(evt -> {
            contextMenu.show(msgText, evt.getScreenX(), evt.getScreenY());
        });
        contextMenu.setAutoHide(true);
        item3.setOnAction(evt -> {
            ClipboardContent content = new ClipboardContent();
            final StringBuilder sb = new StringBuilder();
            sb.append(txtMsg.getText());
            content.putString(sb.toString());
            Clipboard.getSystemClipboard().setContent(content);
        });
        item1.setOnAction(evt -> {
            repliedProperty.set(!repliedProperty.get());
            if (repliedProperty.get())
                item1.setText("remove reply");
            else
                item1.setText("reply");
        });
        item2.setOnAction(evt -> {
            forwardProperty.set(true);
        });
        item4.setOnAction(evt -> {
            editProperty.set(true);
        });
        item5.setOnAction(evt -> {
            mainPane.setVisible(false);
            message.delete();
            System.out.println("messageController.setContext() delete");
        });
    }

    public BooleanProperty repliedProperty() {
        if (repliedProperty == null)
            repliedProperty = new SimpleBooleanProperty(false);
        return repliedProperty;
    }

    public BooleanProperty getForwardProperty() {
        if (forwardProperty == null)
            forwardProperty = new SimpleBooleanProperty(false);
        return forwardProperty;
    }

    public BooleanProperty getEditProperty() {
        if (editProperty == null)
            editProperty = new SimpleBooleanProperty(false);
        return editProperty;
    }
}
