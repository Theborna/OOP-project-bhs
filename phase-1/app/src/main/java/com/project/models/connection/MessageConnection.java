package com.project.models.connection;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.database.MessageDB;
import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

public class MessageConnection extends connection<Message, Chat> {

    public MessageConnection(Message obj1, Chat obj2) {
        super(obj1, obj2);
    }

    public static Set<Message> getMessages(Long chatId) {
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
        // messages.addAll(MessageDB.)
        return messages;
    }

    public static Message getLastMessage(Long chatId) {
        // TODO: run query and shit
        Message msg = new Message(
                "khaste am va az badbakhti daram mimiram dige nemitoonam edame bedam in mozakhrafat ro riazi ham moonde",
                User.getCurrentUser());
        msg.setLastModifiedDate(LocalDateTime.now());
        // System.out.println(msg.getLastModifiedDate());
        return msg;
    }
}
