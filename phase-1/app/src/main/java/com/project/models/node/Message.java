package com.project.models.node;

import com.project.models.node.user.User;

public class Message extends node { // TODO lots of modifications
    private StringBuilder message;
    private User sender;

    public Message(String message, User sender) {
        this.message = new StringBuilder(message);
        this.sender = sender;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }
}
