package com.project.models.connection;

import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.user.User;

public class MessageConnection extends connection<Message, Chat> {

    public static void getMessages(Chat chat) {
        // TODO: run query and shit
    }

    public static Message getLastMessage(Chat chat) {
        // TODO: run query and shit
        return new Message(
                "khaste am va az badbakhti daram mimiram dige nemitoonam edame bedam in mozakhrafat ro riazi ham moonde",
                User.getCurrentUser());
    }
}
