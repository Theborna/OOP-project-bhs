package com.project.models.connection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.project.enums.ChatPermission;
import com.project.enums.ChatType;
import com.project.models.node.Chat;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

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

    // TODO: i made this into a Map!!!
    public static Map<User, ChatPermission> getUsers(Long chatId) { // TODO: i made this into a Map!!!
        Map<User, ChatPermission> result = new LinkedHashMap<User, ChatPermission>();
        // TODO: run a query and get all the users in the chat
        result.put(new NormalUser("sex", "anal"), ChatPermission.NORMAL);
        result.put(new NormalUser("sex2", "vaginal"), ChatPermission.NORMAL);
        for (int i = 0; i < 10; i++) {
            result.put(new NormalUser(Integer.toString(i), Integer.toString((2 * i))), ChatPermission.NORMAL);
        }
        return result;
    }

    public static void addUser(Long chatId, Long memberId, ChatPermission chatPermission) {
        // TODO: add the user to the chat
    }

}
