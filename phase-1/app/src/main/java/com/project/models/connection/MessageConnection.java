package com.project.models.connection;

import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

public class MessageConnection extends connection<Message, Chat> {

    public static Set<Message> getMessages(Chat chat) {
        // TODO: run query and shit
        Set<Message> messages = new LinkedHashSet<>();
        User other = new NormalUser("sep", "14124");
        Message ziba = new Message(
                "khaste am va az badbakhti daram mimiram dige nemitoonam edame bedam in mozakhrafat ro riazi ham moonde",
                User.getCurrentUser());
        messages.add(ziba);
        messages.add(new Message("+++++", other).setReplyTo(ziba));
        messages.add(new Message("sammeeee", other).setReplyTo(ziba));
        for (int i = 0; i < 29; i++)
            messages.add(new Message(String.valueOf(i), (i % 2 == 0) ? User.getCurrentUser() : other));
        return messages;
    }

    public static Message getLastMessage(Chat chat) {
        // TODO: run query and shit
        return new Message(
                "khaste am va az badbakhti daram mimiram dige nemitoonam edame bedam in mozakhrafat ro riazi ham moonde",
                User.getCurrentUser());
    }
}
