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
            println(": " + message.getReplyTo().getBuilder(), StdColor.BLACK_BRIGHT);
        }
        print(message.getSender().getUsername(), message.getSender().getNameColor());
        if (!message.getAuthor().equals(message.getSender())) {
            print(" " + "forwarded from " + message.getAuthor().getUsername(), StdColor.BLACK_BRIGHT);
        }
        println("  @" + message.getLastModifiedDate(), StdColor.BLACK_BRIGHT);
        println(message.getBuilder());
        rule('_');
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    @Override
    public void reset() {
        return;
    }
}
