package com.electro.controllers.views;

import java.util.ArrayList;

import com.electro.App;
import com.electro.controllers.components.postController;
import com.electro.controllers.components.profileController;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ProfilePageController {

    @FXML
    private Button btnScrollPage, btnFollow, btnMessage;

    @FXML
    private AnchorPane pnProfile;

    @FXML
    private ScrollPane scrollProf;

    private User user;

    private ProfileVbox Profile;

    @FXML
    void Scroll(ActionEvent event) {
        boolean top = true;
        Animation animation = new Timeline(
                new KeyFrame(
                        Duration.seconds((top ? scrollProf.getVvalue() : 1 - scrollProf.getVvalue())
                                * scrollProf.getHeight() * 0.003),
                        new KeyValue(scrollProf.vvalueProperty(), top ? 0 : 1, Interpolator.EASE_OUT)));
        animation.play();
    }

    public void initialize(User user) {
        this.user = user;
        if (user == User.getCurrentUser()) {
            btnMessage.setDisable(true);
            btnFollow.setDisable(true);
        }
        btnScrollPage.setText(user.getName());
        Profile = new ProfileVbox().withUser(user);
        scrollProf.setContent(Profile);
        if (User.getCurrentUser().isFollowing(user))
            btnFollow.setText("un-follow");
        else
            btnFollow.setText("follow");
    }

    public BooleanProperty getInfoRequestProperty() {
        return Profile.getProfileRequest();
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

    public static class ProfileVbox extends VBox {

        private static BooleanProperty profileRequest;

        public ProfileVbox() {
        }

        public ProfileVbox withUser(User user) {
            System.out.println("loading the posts...");
            if (profileRequest == null)
                profileRequest = new SimpleBooleanProperty(false);
            super.setSpacing(20);
            super.getChildren().clear();
            ArrayList<Post> posts = new ArrayList<Post>();
            posts.addAll(PostUserConnection.getPosts(user.getId()));
            ArrayList<Node> nodes = new ArrayList<Node>();
            final int size = posts.size();
            Thread thread = new Thread(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("components/profile.fxml"));
                    final Node node = loader.load();
                    profileController controller = loader.getController();
                    profileRequest.bind(controller.getInfoRequest());
                    Platform.runLater(() -> {
                        controller.initialize(user);
                        super.getChildren().add(node);
                    });
                    for (int i = 0; i < size; i++) {
                        loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
                        final Node node_ = loader.load();
                        postController _controller = loader.getController();
                        final int j = i;
                        Platform.runLater(() -> {
                            _controller.initialize(posts.get(j));
                            super.getChildren().add(nodes.get(j));
                        });
                        nodes.add(node_);
                        Thread.sleep(60);// resting
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            return this;
        }

        public BooleanProperty getProfileRequest() {
            return profileRequest;
        }

    }

}
