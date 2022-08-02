package com.electro.phase1.models.node;

import java.time.LocalDateTime;

import com.electro.phase1.models.node.user.User;

public class Message extends node implements TextBased { // TODO lots of modifications
    private StringBuilder message;
    private User sender;
    private User author;
    private Message replyTo;
    // private int likes, dislikes;
    private static long id;

    public Message(String message, User sender) {
        this.message = new StringBuilder(message);
        this.sender = sender;
        this.author = sender;
        setData(id++, LocalDateTime.now(), LocalDateTime.now());
        replyTo = null;
    }

    public Message setReplyTo(Message replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public Message getReplyTo() {
        return replyTo;
    }

    public StringBuilder getBuilder() {
        return message;
    }

    public User getSender() {
        return sender;
    }

    public User getAuthor() {
        return author;
    }

    public Message forwardFrom(User sender) {
        Message newMsg = new Message(this.message.toString(), sender);
        newMsg.author = this.author;
        newMsg.creationDate = this.creationDate;
        newMsg.lastModifiedDate = LocalDateTime.now();
        return newMsg;
    }

    @Override
    public String getText() {
        return message.toString();
    }

    public boolean isForwarded() {
        return true;
    }
}
