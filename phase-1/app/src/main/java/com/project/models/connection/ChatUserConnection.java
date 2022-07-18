package com.project.models.connection;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.node.Chat;
import com.project.models.node.ChatType;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

public class ChatUserConnection extends connection<User, Chat> {

    public ChatUserConnection(Date creationDate, User obj1, Chat obj2) {
        super(creationDate, obj1, obj2);
    }

    public static Set<Chat> getChats(User user) {
        Set<Chat> result = new LinkedHashSet<Chat>();
        TODO: run a query and get all the chats the user is connected to
        result.add(new Chat("sepronites", ChatType.GROUP));
        result.add(new Chat("borna saving memes", ChatType.CHANNEL));
        result.add(new Chat("oop", ChatType.GROUP));
        result.add(new Chat("sepehr", ChatType.PRIVATE));
        for (int i = 0; i < 50; i++) {
            result.add(new Chat(String.valueOf(i), ChatType.PRIVATE));
        }
        return result;
    }

    public static Set<User> getUsers(Chat chat) {
        Set<User> result = new LinkedHashSet<User>();
        TODO: run a query and get all the users in the chat
        result.add(new NormalUser("sex","anal"));
        result.add(new NormalUser("sex","vaginal"));
        for (int i = 0; i < 10; i++) {
            result.add(new NormalUser(Integer.toString(i),Integer.toString((2*i))));
        }
        return result;
    }

}
