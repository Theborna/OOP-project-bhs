package com.electro.views;

import java.util.ArrayList;

import com.electro.App;
import com.electro.controllers.components.chatItemController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PostListView extends VBox {

    private static PostListView pnPostInstance;

    private PostListView() {
    }

    public void addAll() {
        System.out.println("loading the posts...");
        super.setSpacing(20);
        ArrayList<Node> nodes = new ArrayList<Node>();
        final int size = 20;
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < size; i++) {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
                    nodes.add(loader.load());
                    super.getChildren().add(nodes.get(i));
                    // ((chatItemController) loader.getController()).checkSize();
                    // super.widthProperty().addListener(new ChangeListener<Number>() {
                    // @Override
                    // public void changed(ObservableValue<? extends Number> arg0, Number arg1,
                    // Number arg2) {
                    // ((chatItemController) loader.getController()).checkSize();
                    // }
                    // });
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
