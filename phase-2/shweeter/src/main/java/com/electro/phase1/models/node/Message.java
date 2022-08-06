package com.electro.phase1.models.node;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.electro.MessagingServer.ServerConnection;
import com.electro.database.MessageDB;
import com.electro.phase1.models.node.user.User;

public class Message extends node implements TextBased { // TODO lots of modifications
    private StringBuilder message;
    private User sender;
    private Message replyTo;
    private User author;
    private Chat ch;
    private String encKey;
    private Media md;
    // private int likes, dislikes;
    private static long id;

    public Message(String message, User sender, Chat ch) {
        this.message = new StringBuilder(message);
        this.sender = sender;
        this.author = sender;
        this.ch = ch;
        // setData(id++, LocalDateTime.now(),
        // LocalDateTime.now());
        replyTo = null;
    }

    public Media getMd() {
        return md;
    }

    public void setMd(Media md) {
        this.md = md;
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
        // newMsg.lastModifiedDate =
        return newMsg;
    }

    @Override
    public void sendToDB() {
        try {
            MessageDB.newMessage(this);
            System.out.println(".()");
            ServerConnection.getInstance(User.getCurrentUser().getId()).notifyUsers(this.getCh().getId(),
                    User.getCurrentUser().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    public boolean isForwarded() {
        return !(author.equals(sender));
    }

    public void setText(String string) {
        message = new StringBuilder(string);
    }

    public void delete() {
        try {
            MessageDB.deleteMessage(this.getId());
            ServerConnection.getInstance(User.getCurrentUser().getId()).notifyUsers(ch.getId(),
                    User.getCurrentUser().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
