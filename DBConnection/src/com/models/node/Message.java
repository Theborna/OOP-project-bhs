package com.models.node;

import com.models.node.user.User;

import java.sql.Date;

public class Message extends node { // TODO lots of modifications
    private StringBuilder message;
    private User sender;
    private Message replyTo;
    // private int likes, dislikes;
    private static long id;

    public Message(String message, User sender) {
        this.message = new StringBuilder(message);
        this.sender = sender;
        setData(id++, new Date(1), new Date(2));
        replyTo = null;
    }

    public Message setReplyTo(Message replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public Message getReplyTo() {
        return replyTo;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }
}