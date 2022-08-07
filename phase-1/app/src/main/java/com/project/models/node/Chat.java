package com.project.models.node;

import com.database.ChatDB;
import com.project.enums.ChatPermission;
import com.project.enums.ChatType;
import com.project.models.connection.MessageConnection;
import com.project.models.node.user.User;
import com.project.util.Log;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Chat extends node {
    private static Chat current;
    private String linkID;
    private String name;
    private ChatType type;
    private boolean canSend;
    private int memberCount;
    private boolean visible;
    // For groups and channels
    private User owner;

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

    // public Chat addAll(Map<Long, ChatPermission> memberWithPermit) {
    // for (Long member : memberWithPermit.keySet())
    // ChatUserConnection.addUser(this.id, member, memberWithPermit.get(member));
    // return this;
    // }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static Long getByLinkID(String linkID) {
        try {
            return ChatDB.getChatByLinkID(linkID).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public Set<Long> getAdmins() {
    //     return null;
    // }

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
        try {
            ChatDB.sendToDB(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
