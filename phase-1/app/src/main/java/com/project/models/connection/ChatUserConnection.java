package com.project.models.connection;

import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.node.Chat;
import com.project.models.node.ChatType;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

public class ChatUserConnection extends connection<User, Chat> {

    public static Set<Chat> getChats(Long userId) {
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

    public static Set<User> getUsers(Long chatId) {
        Set<User> result = new LinkedHashSet<User>();
        // TODO: run a query and get all the users
        result.add(new NormalUser("124", "password"));
        result.add(new NormalUser("123", "password"));
        result.add(new NormalUser("11245", "password"));
        result.add(new NormalUser("1241243", "password"));
        return result;
    }

}
