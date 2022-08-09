package com.electro.controllers.views;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.electro.database.UserDB;
import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.StdColor;
import com.electro.phase1.util.StdOut;
import com.electro.util.FunctionalListview;

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

    private static Consumer<User> openPage;

    public void initialize(StringProperty textProperty) {
        List<Runnable> functions = new ArrayList<Runnable>();
        textProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (pnUser.isSelected()) {
                    if (arg2 == null)
                        return;
                    StdOut.println("searching for users...", StdColor.CYAN);
                    lstSearchUser.getItems().clear();
                    functions.clear();
                    List<User> users = new ArrayList<User>();
                    try {
                        users.addAll(UserDB.searchByUserName(arg2, User.getCurrentUser().getId()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    lstSearchUser.getItems().addAll(users.stream().map(User::getUsername).collect(Collectors.toList()));
                    functions.clear();
                    functions.addAll(
                            users.stream().map(user -> new Runnable() {
                                @Override
                                public void run() {
                                    openPage.accept(user);
                                }
                            }).collect(Collectors.toList()));
                } else if (pnChat.isSelected()) {
                    StdOut.println("searching for chats...", StdColor.CYAN);
                    lstSearchChat.getItems().clear();
                    lstSearchChat.getItems().addAll(arg1, arg2);// TODO: give search results on a thread
                }
            }
        });
        FunctionalListview.bind(functions, lstSearchUser);
    }

    public static void setOnProfileRequest(Consumer<User> openPage) {
        SearchController.openPage = openPage;
    }

}
