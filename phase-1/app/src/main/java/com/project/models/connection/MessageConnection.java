package com.project.models.connection;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.database.MessageDB;
import com.project.models.node.Chat;
import com.project.models.node.Message;

public class MessageConnection extends connection<Message, Chat> {

    public MessageConnection(Message obj1, Chat obj2) {
        super(obj1, obj2);
    }

    public static Set<Message> getMessages(Long chatId) {
        Set<Message> messages = null;
        try {
            messages = new LinkedHashSet<>(MessageDB.getMessagesByChatID(chatId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static Message getLastMessage(Long chatId) {
        Set<Message> msgs = getMessages(chatId);
        return (msgs.size() == 0) ? null : (Message) msgs.toArray()[msgs.size() - 1];
    }
}
