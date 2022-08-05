package com.electro.phase1.models.node;

import com.electro.database.ChatDB;
import com.electro.phase1.enums.ChatPermission;
import com.electro.phase1.enums.ChatType;
import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.connection.MessageConnection;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.Log;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class Chat extends node {
    private static Chat current;
    private static long chatId;
    private static StringProperty currentName;
    private String linkID;
    private String name;
    private ChatType type;
    private boolean canSend;
    private int memberCount;
    private boolean visible;
    // For groups and channels
    private User owner;

    // TODO: sepehr changes
    // private int participantNum;
    // private ArrayList<User> participants;
    // private ArrayList<Message> messages;
    // private ArrayList<User> administrators;

    public Chat(String name, ChatType type) {
        this.name = name;
        this.type = type;
        // setData(chatId++, new Date(1), new Date(2));
    }

    public static void LogToChat(long id) {
        // find the chat from the database and set current chat
        // current = new Chat("kos", ChatType.PRIVATE);
        try {
            current = ChatDB.getChatByID(id);
            currentName.set(current.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void LogToChat(Chat chat) {
        current = chat;
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

    public boolean isCanSend() {
        return canSend;
    }

    public void setCanSend(boolean canSend) {
        this.canSend = canSend;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public User getOwner() {
        return owner;
    }

    public String getLinkID() {
        return linkID;
    }

    public Chat setLinkID(String linkID) {
        this.linkID = linkID;
        return this;
    }

    @Override
    public LocalDateTime getLastModifiedDate() {
        Message last = MessageConnection.getLastMessage(this.id);
        return (last == null) ? creationDate : last.getCreationDate();
    }

    public Chat addAll(Map<Long, ChatPermission> memberWithPermit) {
        for (Long member : memberWithPermit.keySet())
            ChatUserConnection.addUser(this.id, member, memberWithPermit.get(member));
        return this;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static Long getByLinkID(String linkID) {
        // TODO get the id of the chat with the specified linkID
        return null;
    }

    public Set<Long> getAdmins() {
        return null;
    }

    public ChatPermission getPermission(long id) throws SQLException {
        // System.out.println(this.id + " user: " + id);
        return ChatDB.getChatPermission(id, this.id);
    }

    public void delete() {
        // TODO: delete the chat
        Log.logger.info("deleted chat " + id);
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        try {
            ChatDB.sendToDB(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StringProperty getCurrentName() {
        if (currentName == null)
            currentName = new SimpleStringProperty();
        return currentName;
    }
}
