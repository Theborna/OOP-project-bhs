package com.electro.views;

import com.electro.controllers.views.ChatController;

import javafx.beans.property.BooleanProperty;

public class ChatView extends inPane {

    private ChatController controller;

    public ChatView() {
        super();
        controller = getController("chat-view");
    }

    public void setOnRequest(Runnable run) {
        controller.getRequestSettingsProperty().addListener((o, old, niu) -> {
            if (niu)
                run.run();
        });
    }
}
