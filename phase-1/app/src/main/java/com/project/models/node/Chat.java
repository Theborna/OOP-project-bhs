
package com.project.models.node;

import java.time.LocalDateTime;
import java.util.List;

import com.project.controllers.ChatListController;
import com.project.models.connection.ChatUserConnection;
import com.project.models.connection.MessageConnection;
import com.project.view.general.ChatListView;

public class Chat extends node {
    private static Chat current;
    private static long chatId;
    private String name;
    private ChatType type;
    // TODO: sepehr changes
    // private int participantNum;
    // private ArrayList<User> participants;
    // private ArrayList<Message> messages;
    // private ArrayList<User> administrators;

    public Chat(String name, ChatType type) {
        this.name = name;
        this.type = type;
        setData(chatId++, LocalDateTime.now(),
                LocalDateTime.now());
    }

    public static void LogToChat(long id) {
        // TODO: find the chat from the database and set current chat
        current = new Chat("kos", ChatType.PRIVATE);
    }

    public static Chat getCurrent() {
        return current;
    }

    public String getName() {
        return name;
    }

    public ChatType getType() {
        return type;
    }

    @Override
    public LocalDateTime getLastModifiedDate() {
        return MessageConnection.getLastMessage(this.id).getLastModifiedDate();
    }

    public void addAll(List<Long> members) {
        for (Long member : members)
            ChatUserConnection.addUser(this.id, member);
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        ((ChatListController) ChatListView.getInstance().getController()).add(this);
    }
}
