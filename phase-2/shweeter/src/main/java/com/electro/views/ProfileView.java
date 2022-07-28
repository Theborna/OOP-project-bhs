package com.electro.views;

import java.util.ArrayList;

import com.electro.App;
import com.electro.controllers.components.postController;
import com.electro.controllers.components.profileController;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ProfileView extends VBox {

    private static ProfileView other;

    public ProfileView() {
    }

    public void withUser(User user) {
        System.out.println("loading the posts...");
        super.setSpacing(20);
        super.getChildren().clear();
        ArrayList<Post> posts = new ArrayList<Post>();
        posts.addAll(PostUserConnection.getPosts(user.getId()));
        ArrayList<Node> nodes = new ArrayList<Node>();
        final int size = posts.size();
        Thread thread = new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("components/profile.fxml"));
                Node node = loader.load();
                profileController controller = loader.getController();
                Platform.runLater(() -> controller.initialize(user));
                super.getChildren().add(node);
                for (int i = 0; i < size; i++) {
                    loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
                    node = loader.load();
                    postController _controller = loader.getController();
                    final int j = i;
                    Platform.runLater(() -> {
                        _controller.initialize(posts.get(j));
                        super.getChildren().add(nodes.get(j));
                    });
                    nodes.add(node);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.run();
    }

    public static ProfileView getOther() {
        if (other == null)
            other = new ProfileView();
        return other;
    }

}
