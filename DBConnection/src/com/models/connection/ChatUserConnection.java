package com.models.connection;

import com.models.node.Chat;
import com.models.node.ChatType;
import com.models.node.user.User;

import java.util.LinkedHashSet;
import java.util.Set;

public class ChatUserConnection extends connection<User, Chat> {

    public static Set<Chat> getChats(User user) {
        Set<Chat> result = new LinkedHashSet<Chat>();
        // TODO: run a query and get all the chats the user is connected to
        result.add(new Chat("sepronites", ChatType.GROUP));
        result.add(new Chat("borna saving memes", ChatType.CHANNEL));
        result.add(new Chat("oop", ChatType.GROUP));
        result.add(new Chat("sepehr", ChatType.PRIVATE));
        for (int i = 0; i < 50; i++) {
            result.add(new Chat(String.valueOf(i), ChatType.PRIVATE));
        }
        return result;
    }
}
