package com.electro.views;

import java.io.IOException;

import com.electro.App;
import com.electro.controllers.views.CreateChatController;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CreateChatView extends inPane {

    private CreateChatController controller;

    public CreateChatView() {
        super();
        controller = getController("new-chat");
    }

    public BooleanProperty finishedProperty() {
        return controller.getFinishedProperty();
    }
}
