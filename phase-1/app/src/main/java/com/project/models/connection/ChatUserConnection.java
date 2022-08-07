package com.project.models.connection;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.database.ChatDB;
import com.project.enums.ChatPermission;
import com.project.models.node.Chat;
import com.project.models.node.user.User;

public class ChatUserConnection extends connection<User, Chat> {

    public ChatUserConnection(User obj1, Chat obj2) {
        super(obj1, obj2);
    }

    public static Set<Chat> getChats(Long userId) {
        Set<Chat> result = new LinkedHashSet<Chat>();
        try {
            result.addAll(ChatDB.getChats(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static Map<User, ChatPermission> getUsers(Long chatId) {
        Map<User, ChatPermission> result = new LinkedHashMap<>();
        try {
            result = ChatDB.getMembersOfChat(chatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
