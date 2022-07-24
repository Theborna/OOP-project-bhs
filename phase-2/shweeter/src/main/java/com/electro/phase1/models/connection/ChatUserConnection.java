package com.electro.phase1.models.connection;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.ChatType;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;

public class ChatUserConnection extends connection<User, Chat> {

    public ChatUserConnection(User obj1, Chat obj2) {
        super(obj1, obj2);
    }

    public static Set<Chat> getChats(Long userId) {
        Set<Chat> result = new LinkedHashSet<Chat>();
        // TODO: run a query and get all the chats the user is connected to
        result.add(new Chat("sepronites", ChatType.GROUP));
        result.add(new Chat("borna saving memes", ChatType.CHANNEL));
        result.add(new Chat("oop", ChatType.GROUP));
        result.add(new Chat("sepehr", ChatType.PRIVATE));
        for (int i = 0; i < 10; i++) {
            result.add(new Chat(String.valueOf(i), ChatType.PRIVATE));
        }
        return result;
    }

    public static Set<User> getUsers(Long chatId) {
        Set<User> result = new LinkedHashSet<User>();
        // TODO: run a query and get all the users in the chat
        result.add(new NormalUser("sex", "anal"));
        result.add(new NormalUser("sex", "vaginal"));
        for (int i = 0; i < 10; i++) {
            result.add(new NormalUser(Integer.toString(i), Integer.toString((2 * i))));
        }
        return result;
    }

    public static void addUser(Long chatId, Long memberId) {
        // TODO: add the user to the chat
    }

}
