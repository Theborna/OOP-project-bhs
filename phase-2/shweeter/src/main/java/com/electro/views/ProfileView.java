package com.electro.views;

import java.util.ArrayList;

import com.electro.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ProfileView extends VBox {

    private static ProfileView other;

    private ProfileView() {
    }

    public void setUser() {
        System.out.println("loading the posts...");
        super.setSpacing(20);
        super.getChildren().clear();
        ArrayList<Node> nodes = new ArrayList<Node>();
        final int size = 20;
        Thread thread = new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("components/profile.fxml"));
                super.getChildren().add(loader.load());
                for (int i = 0; i < size; i++) {
                    loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
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

    public static ProfileView getOther() {
        if (other == null)
            other = new ProfileView();
        return other;
    }

}
