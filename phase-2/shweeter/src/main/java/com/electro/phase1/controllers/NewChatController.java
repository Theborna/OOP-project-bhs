package com.electro.phase1.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.controlsfx.control.textfield.CustomTextField;

import com.electro.App;
import com.electro.database.ChatDB;
import com.electro.phase1.AppRegex;
import com.electro.phase1.enums.ChatPermission;
import com.electro.phase1.enums.ChatType;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.user.User;
import com.electro.views.component.ErrorNotification;
import com.electro.views.component.FieldEmptyError;

import javafx.scene.image.ImageView;

public class NewChatController {

    private ChatType type;
    private String name, linkID;
    private Map<String, ChatPermission> permissions, initialPermissions;
    private Map<Long, String> members, initialMembers;
    private Long newUserID;

    public NewChatController() {
        permissions = new LinkedHashMap<>();
        members = new LinkedHashMap<>();
        initialMembers = new LinkedHashMap<>();
        initialMembers.put(User.getCurrentUser().getId(), User.getCurrentUser().getUsername());
        members.putAll(initialMembers);
        initialPermissions = new LinkedHashMap<>();
        initialPermissions.put(User.getCurrentUser().getUsername(), ChatPermission.OWNER);
        setDefaultPermissions();
    }

    public NewChatController setInitialPermissions(Map<String, ChatPermission> initialPermissions) {
        this.initialPermissions = initialPermissions;
        permissions.clear();
        permissions.putAll(initialPermissions);
        return this;
    }

    public NewChatController setInitialMembers(Map<Long, String> initialMembers) {
        this.initialMembers = initialMembers;
        members.clear();
        members.putAll(initialMembers);
        return this;
    }

    public void setDefaultPermissions() {
        if (type == ChatType.PRIVATE) {
            for (String member : members.values()) // make both an owner
                permissions.put(member, ChatPermission.OWNER);
        } else if (type == ChatType.GROUP) {
            for (String member : members.values())
                permissions.put(member, ChatPermission.NORMAL);
            permissions.put(User.getCurrentUser().getUsername(), ChatPermission.OWNER);
        } else if (type == ChatType.CHANNEL) {
            for (String member : members.values())
                permissions.put(member, ChatPermission.OBSERVER);
            permissions.put(User.getCurrentUser().getUsername(), ChatPermission.OWNER);
        }
        permissions.putAll(initialPermissions);
    }

    public boolean setChatLinkId(CustomTextField field) {
        String name = field.getText();
        new FieldEmptyError(field);
        if (field.getText() == null)
            return false;
        if (!AppRegex.CHAT_ID.matches(field.getText())) {
            ImageView nope = new ImageView(App.getImage("images/icons8_fail_96px.png"));
            nope.setFitWidth(30);
            nope.setFitHeight(30);
            field.setRight(nope);
            field.setOnKeyTyped(evt -> {
                field.setRight(null);
            });
            return false;
        }
        this.linkID = name;
        return true;
    }

    public boolean setChatName(CustomTextField field) {
        String name = field.getText();
        new FieldEmptyError(field);
        if (field.getText() == null)
            return false;
        if (getChatName(name) == null) {
            ImageView nope = new ImageView(App.getImage("images/icons8_fail_96px.png"));
            nope.setFitWidth(30);
            nope.setFitHeight(30);
            field.setRight(nope);
            field.setOnKeyTyped(evt -> {
                field.setRight(null);
            });
            return false;
        }
        this.name = name;
        return true;
    }

    public String getChatName(String input) {
        if (AppRegex.CHAT_NAME.getMatcher(input) != null)
            return input.trim();
        return null;
    }

    public String getUsername(String input) {
        if (AppRegex.USERNAME.getMatcher(input) != null)
            return input;
        return null;
    }

    public boolean addMember(String name) {
        newUserID = null;
        int key = validMember(name);
        switch (key) {
            case 0:
                new ErrorNotification("invalid username format");
                return false;
            case 1:
                new ErrorNotification("no such user Exists");
                return false;
            case 2:
                new ErrorNotification("chat is already full");
                return false;
            case 3:
                new ErrorNotification("user already in chat");
                return false;
            case 4:
                members.put(newUserID, name);
                ChatPermission def = ChatPermission.NORMAL;
                if (type == ChatType.PRIVATE)
                    def = ChatPermission.OWNER;
                else if (type == ChatType.CHANNEL)
                    def = ChatPermission.OBSERVER;
                permissions.put(name, def);
                return true;
            default:// should not happen
                return false;
        }
    }

    public boolean removeMember(String name) {
        Long id;
        if (members.remove(id = User.getID(name), name)) {
            if (id == User.getCurrentUser().getId()) {
                new ErrorNotification("cannot remove self");
                return false;
            }
            permissions.remove(name);
            return true;
        }
        new ErrorNotification("user " + name + " is not in the members list");
        return false;
    }

    /**
     * 
     * @param name
     * @return 0 if invalid format, 1 if non existent , 2 if full , 3 if already in
     *         chat, 4 if valid
     */
    public int validMember(String name) {
        if (getUsername(name) == null)
            return 0;
        if ((newUserID = User.getID(name)) == null)
            return 1;
        if (type == ChatType.PRIVATE && members.values().size() > 1)
            return 2;
        if (members.keySet().contains(newUserID))
            return 3;
        // System.out.println(newUserID);
        return 4;
    }

    // public int getId(String nextLine) {
    // nextLine = nextLine.trim();
    // if (nextLine.toLowerCase().equals("-none")) {
    // linkID = null;
    // return 3;
    // } else if (nextLine.toLowerCase().equals("skip")) {
    // return 5;
    // } else if (AppRegex.CHAT_ID.matches(nextLine)) {
    // if (Chat.getByLinkID(nextLine) != null)
    // return 0;
    // linkID = nextLine;
    // return 2;
    // }
    // return 1;
    // }

    public ChatType getChatType(String input) {
        input = input.trim().toLowerCase();
        if (input.equals("private"))
            return ChatType.PRIVATE;
        else if (input.equals("channel"))
            return ChatType.CHANNEL;
        else if (input.equals("group"))
            return ChatType.GROUP;
        return null;
    }

    public boolean makeChat() {
        if (members.size() < 2)
            return false;
        Chat chat = new Chat(name, type);
        Map<Long, ChatPermission> memberWithPermit = new HashMap<Long, ChatPermission>();
        for (Long member : members.keySet())
            memberWithPermit.put(member, permissions.get(members.get(member)));
        if (type == ChatType.PRIVATE)
            linkID = null;// TODO: check this
        chat.setLinkID(linkID).sendToDB();
        // Adding members
        try {
            Chat chatTemp = ChatDB.getChatByLinkID(chat.getLinkID());
            for (long id : memberWithPermit.keySet()) {
                ChatDB.addMemeber(id, chatTemp.getId(), memberWithPermit.get(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;// TODO
    }

    public void clearMembers() {
        members.clear();
        permissions.clear();
        members.putAll(initialMembers);
        permissions.putAll(initialPermissions);
    }

    public boolean setPermission(String username, ChatPermission permission) {
        if (username == User.getCurrentUser().getUsername())
            return false;
        permissions.put(username, permission);
        return true;
    }

    public String getName() {
        return name;
    }

    public Map<Long, String> getInitialMembers() {
        return initialMembers;
    }

    public Map<String, ChatPermission> getInitialPermissions() {
        return initialPermissions;
    }

    public Map<String, ChatPermission> getPermissions() {
        return permissions;
    }

    public String getLinkID() {
        return linkID;
    }

    public NewChatController setType(ChatType type) {
        this.type = type;
        return this;
    }

    public NewChatController setName(String name) {
        this.name = name;
        return this;
    }

    public NewChatController setLinkID(String linkID) {
        this.linkID = linkID;
        return this;
    }

}
