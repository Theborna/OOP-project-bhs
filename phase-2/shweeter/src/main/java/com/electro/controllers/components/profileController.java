package com.electro.controllers.components;

import com.electro.phase1.models.node.user.BusinessUser;
import com.electro.phase1.models.node.user.User;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class profileController {

    @FXML
    private Label lblFollowers;

    @FXML
    private Label lblFollowing;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUsername;

    @FXML
    private Text txtBio;

    private IntegerProperty clicks;

    private User user;

    public void initialize(User user) {
        this.user = user;
        lblName.setText(user.getFullName());
        lblUsername.setText(user.getUsername());
        lblInfo.setText(
                ((user.isPublic()) ? "Public ," : "Private ,")
                        + ((user instanceof BusinessUser) ? "Business " : "Normal ") + "user");
        lblFollowers.setText(String.valueOf(user.getFollowerCnt()));
        lblFollowing.setText(String.valueOf(user.getFollowingCnt()));
        clicks = new SimpleIntegerProperty(0);
    }

    @FXML
    private void profRequest() {
        clicks.add(1);
    }

    public IntegerProperty clickProperty() {
        if (clicks == null)
            clicks = new SimpleIntegerProperty(0);
        return clicks;
    }

}
