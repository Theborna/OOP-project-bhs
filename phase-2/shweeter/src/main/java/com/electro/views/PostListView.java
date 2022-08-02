package com.electro.views;

import java.util.ArrayList;
import java.util.Collection;

import com.electro.App;
import com.electro.controllers.components.chatItemController;
import com.electro.controllers.components.postController;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PostListView extends VBox {

    private static PostListView pnPostInstance;

    private PostListView() {
    }

    public void addAll(Collection<Post> posts) {
        System.out.println("loading the posts...");
        super.setSpacing(20);
        // ArrayList<Node> nodes = new ArrayList<Node>();
        final int size = posts.size();
        Thread thread = new Thread(() -> {
            try {
                for (Post post : posts) {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
                    postController controller = loader.getController();
                    controller.initialize(post.getText(), post.getSender().getUsername(), post.getLikes());
                    Node node = loader.load();
                    super.getChildren().add(node);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.run();
    }

    public static PostListView getPnPostInstance() {
        if (pnPostInstance == null) {
            pnPostInstance = new PostListView();
        }
        return pnPostInstance;
    }

}
