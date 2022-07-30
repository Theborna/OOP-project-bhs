package com.project.models.node;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.database.MessageDB;
import com.project.models.node.user.User;

public class Message extends node implements TextBased { // TODO lots of modifications
    private StringBuilder message;
    private User sender;
    private Message replyTo;
    private User author;
    private Chat ch;
    private String encKey;
    // private int likes, dislikes;
    private static long id;

    public Message(String message, User sender, Chat ch) {
        this.message = new StringBuilder(message);
        this.sender = sender;
        this.author = sender;
        this.ch = ch;
//        setData(id++, LocalDateTime.now(),
//                LocalDateTime.now());
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
        this.author = forwardedFrom;
    }

    public Message getReplyTo() {
        return replyTo;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public User getSender() {
        return sender;
    }

    public User getForwardedFrom() {
        if (author == null)
            author = sender;
        return author;
    }

    public Chat getCh() {
        return ch;
    }

    public void setCh(Chat ch) {
        this.ch = ch;
    }

    public Message forwardFrom(User sender, Chat ch) {
        Message newMsg = new Message(this.message.toString(), sender, ch);
        newMsg.author = this.author;
        newMsg.creationDate = LocalDateTime.now();
//        newMsg.lastModifiedDate =
        return newMsg;
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        try {
            MessageDB.newMessage(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getText() {
        return message.toString();
    }

    @Override
    public StringBuilder getBuilder() {
        return message;
    }

}
