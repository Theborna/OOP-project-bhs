package com.electro.controllers.components;

import com.electro.views.component.ProfilePopOver;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class messageController {

    @FXML
    private Label date;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label name;

    @FXML
    private ImageView pfp;

    @FXML
    private TextFlow msgText;

    public void initialize() { // will include the important stuff about the actual message
        name.setText(":/borna/");
        // mainPane.setPrefHeight(1000);
        new ProfilePopOver(pfp);
        setContext();
    }

    private void setContext() {
        // Creating a context menu
        ContextMenu contextMenu = new ContextMenu();
        // Creating the menu Items for the context menu
        MenuItem item1 = new MenuItem("reply");
        MenuItem item2 = new MenuItem("forward");
        MenuItem item3 = new MenuItem("copy");
        contextMenu.getItems().addAll(item1, item2, item3);
        msgText.setOnContextMenuRequested(evt -> {
            contextMenu.show(msgText, evt.getScreenX(), evt.getScreenY());
        });
        contextMenu.setAutoHide(true);
        item3.setOnAction(evt -> {
            ClipboardContent content = new ClipboardContent();
            final StringBuilder sb = new StringBuilder();
            msgText.getChildren().forEach(i -> {
                if (i instanceof Text)
                    sb.append(i);
            });
            content.putString(sb.toString());
            Clipboard.getSystemClipboard().setContent(content);
        });
    }
}
