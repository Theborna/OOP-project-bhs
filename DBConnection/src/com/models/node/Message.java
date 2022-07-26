package com.models.node;

import com.models.node.user.User;

import java.sql.Date;

public class Message extends node { // TODO lots of modifications
    private StringBuilder message;
    private User sender;
    private Message replyTo;
    private User forwardedFrom;
    private Chat ch;
    private String encKey;
    // private int likes, dislikes;
    private static long id;

    public Message(String message, User sender) {
        this.message = new StringBuilder(message);
        this.sender = sender;
        setData(id++, new Date(1), new Date(2));
        replyTo = null;
    }

    public void setEncKey(String encKey) {
        this.encKey = encKey;
    }

    public String getEncKey() {
        return encKey;
    }

    public Message setReplyTo(Message replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMessage(StringBuilder message) {
        this.message = message;
    }

    public void setForwardedFrom(User forwardedFrom) {
        this.forwardedFrom = forwardedFrom;
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

    public User getForwardedFrom() {
        return forwardedFrom;
    }

    public Chat getCh() {
        return ch;
    }

    public void setCh(Chat ch) {
        this.ch = ch;
    }


}
