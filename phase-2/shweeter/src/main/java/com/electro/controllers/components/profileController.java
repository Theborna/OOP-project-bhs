package com.electro.controllers.components;

import com.electro.phase1.models.node.user.BusinessUser;
import com.electro.phase1.models.node.user.User;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class profileController {

    @FXML
    private Button btnFollow, btnMsg;

    @FXML
    private ImageView ivProfile;

    @FXML
    private Label lblFollowers, lblFollowing, lblInfo, lblName, lblUsername;

    @FXML
    private Text txtBio;

    @FXML
    private MenuItem btnInfo;

    private IntegerProperty clicks;

    private BooleanProperty infoRequest;

    private User user;

    public void initialize(User user) {
        this.user = user;
        lblName.setText(user.getName());
        lblUsername.setText(user.getUsername());
        lblInfo.setText(
                ((user.isPublic()) ? "Public ," : "Private ,")
                        + ((user instanceof BusinessUser) ? "Business " : "Normal ") + "user");
        lblFollowers.setText(String.valueOf(user.getFollowerCnt()));
        lblFollowing.setText(String.valueOf(user.getFollowingCnt()));
        if (user == User.getCurrentUser()) {
            btnFollow.setDisable(true);
            btnMsg.setDisable(true);
        }
        clicks = new SimpleIntegerProperty(0);
        if (User.getCurrentUser().isFollowing(user))
            btnFollow.setText("un-follow");
        else
            btnFollow.setText("follow");
        if (infoRequest == null)
            infoRequest = new SimpleBooleanProperty(false);
    }

    @FXML
    private void profRequest() {
        clicks.setValue(clicks.getValue() + 1);
    }

    @FXML
    void Block(ActionEvent event) {
        // User.getCurrentUser().unfollow(user); TODO
    }

    @FXML
    void Follow(ActionEvent event) {
        if (User.getCurrentUser().isFollowing(user)) {
            btnFollow.setText("follow");
            User.getCurrentUser().unfollow(user);
        } else {
            btnFollow.setText("un-follow");
            User.getCurrentUser().follow(user);
        }
    }

    @FXML
    void info() {
        infoRequest.set(true);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                infoRequest.set(false);
            }
        }).start();
    }

    public BooleanProperty getInfoRequest() {
        if (infoRequest == null)
            infoRequest = new SimpleBooleanProperty(false);
        return infoRequest;
    }

    public IntegerProperty clickProperty() {
        if (clicks == null)
            clicks = new SimpleIntegerProperty(0);
        return clicks;
    }

}
