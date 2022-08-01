package com.electro.controllers.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.electro.phase1.util.StdColor;
import com.electro.phase1.util.StdOut;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class SearchController {

    @FXML
    private ListView<String> lstSearchChat;

    @FXML
    private ListView<String> lstSearchUser;

    @FXML
    private Tab pnChat, pnPost, pnUser;
    @FXML
    private AnchorPane pnSearch;

    public void initialize(StringProperty textProperty) {
        textProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (pnUser.isSelected()) {
                    StdOut.println("searching for users...", StdColor.CYAN);
                    lstSearchUser.getItems().clear();
                    lstSearchUser.getItems().addAll(arg1, arg2);// TODO: give search results on a thread
                } else if (pnChat.isSelected()) {
                    StdOut.println("searching for chats...", StdColor.CYAN);
                    lstSearchChat.getItems().clear();
                    lstSearchChat.getItems().addAll(arg1, arg2);// TODO: give search results on a thread
                }
            }
        });
    }

}
