package com.project.models.node;

import java.sql.Date;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.controllers.ChatListController;
import com.project.enums.ChatPermission;
import com.project.enums.ChatType;
import com.project.models.connection.ChatUserConnection;
import com.project.models.connection.MessageConnection;
import com.project.models.node.user.User;
import com.project.util.Log;
import com.project.view.general.ChatListView;

public class Chat extends node {
    private static Chat current;
    private static long chatId;
    private String linkID;
    private String name;
    private ChatType type;
    private User sender;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
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
        return MessageConnection.getLastMessage(this.id).getLastModifiedDate();
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

    public ChatPermission getPermission(long id) {
        // TODO: get the permission of the user
        return ChatPermission.OWNER;
    }

    public void delete() {
        // TODO: delete the chat
        Log.logger.info("deleted chat " + id);
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        
    }
}
