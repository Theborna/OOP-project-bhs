package com.electro.views;

import java.io.IOException;

import com.electro.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class inPane extends AnchorPane {
    public <T> T getController(String url) {
        FXMLLoader loader;
        try {
            loader = App.loadFXML(url);
            Node pane = loader.load();
            super.getChildren().add(pane);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
