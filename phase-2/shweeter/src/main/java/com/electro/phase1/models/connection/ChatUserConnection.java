package com.electro.phase1.models.connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.electro.database.ChatDB;
import com.electro.database.UserDB;
import com.electro.phase1.enums.ChatPermission;
import com.electro.phase1.enums.ChatType;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;

public class ChatUserConnection extends connection<User, Chat> {

    public ChatUserConnection(User obj1, Chat obj2) {
        super(obj1, obj2);
    }

    public static Set<Chat> getChats(Long userId) {
        Set<Chat> result = new LinkedHashSet<Chat>();
        // TODO: run a query and get all the chats the user is connected to
        // result.add(new Chat("sepronites", ChatType.GROUP));
        // result.add(new Chat("borna saving memes", ChatType.CHANNEL));
        // result.add(new Chat("oop", ChatType.GROUP));
        // result.add(new Chat("sepehr", ChatType.PRIVATE));
        // for (int i = 0; i < 10; i++) {
        // result.add(new Chat(String.valueOf(i), ChatType.PRIVATE));
        // }
        try {
            result.addAll(ChatDB.getChats(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: i made this into a Map!!!
    public static Map<User, ChatPermission> getUsers(Long chatId) { // TODO: i made this into a Map!!!
        Map<User, ChatPermission> result = new LinkedHashMap<User, ChatPermission>();
        // TODO: run a query and get all the users in the chat
        // result.put(new NormalUser("sex", "anal"), ChatPermission.NORMAL);
        // result.put(new NormalUser("sex2", "vaginal"), ChatPermission.NORMAL);
        // for (int i = 0; i < 10; i++) {
        //     result.put(new NormalUser(Integer.toString(i), Integer.toString((2 * i))), ChatPermission.NORMAL);
        // }
        // UserDB.get
        return result;
    }
}
