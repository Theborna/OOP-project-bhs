package com.electro.controllers.views;

import java.sql.SQLException;

import com.electro.database.UserDB;
import com.electro.phase1.models.connection.UserFollowingConnection;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.StdOut;
import com.electro.util.FunctionalListview;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class ProfileInfoController {

    @FXML
    private Tab tabFollowers, tabFollowing;

    @FXML
    private AnchorPane pnInfo;

    public void initialize(User user) {
        try {
            StdOut.println("loading page info...");
            tabFollowers.setContent(new FunctionalListview(UserDB.getFollowers(user.getId(), 0)));
            tabFollowing.setContent(new FunctionalListview(UserDB.getFollowings(user.getId(), 0)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
