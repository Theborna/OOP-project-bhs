package com.view.model;

import com.controllers.Controller;
import com.models.node.Message;
import com.util.StdColor;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

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

    public Message getMessage() {
        return message;
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

}
