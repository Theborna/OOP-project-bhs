package com.project.view.model;

import com.project.controllers.Controller;
import com.project.models.node.Message;
import com.project.util.StdColor;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

public class MessageView implements View {

    private Message message;

    public MessageView(Message message) {
        this.message = message;
    }

    @Override
    public void show() throws changeViewException {
        rule('_');
        if (message.getReplyTo() != null) {
            print("╔════@" + message.getReplyTo().getSender().getUsername(), StdColor.BLACK_BRIGHT);
            println(": " + message.getReplyTo().getMessage(), StdColor.BLACK_BRIGHT);
        }
        print(message.getSender().getUsername(), message.getSender().getNameColor());
        println("  " + message.getCreationDate(), StdColor.BLACK_BRIGHT);
        println(message.getMessage());
        rule('_');
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

}
