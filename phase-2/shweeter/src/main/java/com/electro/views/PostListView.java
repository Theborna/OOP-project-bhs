package com.electro.views;

import java.util.Collection;

import com.electro.App;
import com.electro.controllers.components.postController;
import com.electro.phase1.models.node.post.Post;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PostListView extends VBox {

    public PostListView() {
    }

    public PostListView withPosts(Collection<Post> posts) {
        System.out.println("loading the posts...");
        super.setSpacing(20);
        // ArrayList<Node> nodes = new ArrayList<Node>();
        Thread thread = new Thread(() -> {
            try {
                for (Post post : posts) {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
                    Node node = loader.load();
                    postController controller = loader.getController();
                    Platform.runLater(() -> {
                        controller.initialize(post);
                        super.getChildren().add(node);
                    });
                    Thread.sleep(120);// chill time
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        return this;
    }

}
