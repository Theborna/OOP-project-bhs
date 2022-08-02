package com.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.project.AppRegex;
import com.project.enums.ChatPermission;
import com.project.enums.ChatType;
import com.project.models.node.Chat;
import com.project.models.node.user.User;

public class NewChatController {

    private ChatType type;
    private String name, linkID;
    private Map<String, ChatPermission> permissions, initialPermissions;
    private Map<Long, String> members, initialMembers;

    public NewChatController() {
        permissions = new HashMap<>();
        members = new HashMap<>();
        initialMembers = new HashMap<>();
        initialMembers.put(User.getCurrentUser().getId(), User.getCurrentUser().getUsername());
        members.putAll(initialMembers);
        initialPermissions = new HashMap<>();
        initialPermissions.put(User.getCurrentUser().getUsername(), ChatPermission.OWNER);
        setDefaultPermissions();
    }

    public NewChatController setInitialMembers(Map<Long, String> initialMembers) {
        this.initialMembers = initialMembers;
        members.clear();
        members.putAll(initialMembers);
        return this;
    }

    public NewChatController setInitialPermissions(Map<String, ChatPermission> initialPermissions) {
        this.initialPermissions = initialPermissions;
        return this;
    }

    public String getChatName(String input) {
        if (AppRegex.CHAT_NAME.getMatcher(input) != null)
            return name = input.trim();
        return null;
    }

    public String getName() {
        return name;
    }

    public ChatType getType() {
        return type;
    }

    public String getUsername(String input) {
        Matcher m;
        if ((m = AppRegex.ADD_USER.getMatcher(input)) == null)
            if ((m = AppRegex.REMOVE_USER.getMatcher(input)) == null)
                return null;
        return m.group("username");
    }

    public ChatType getChatType(String input) {
        input = input.trim().toLowerCase();
        if (input.equals("private"))
            return type = ChatType.PRIVATE;
        else if (input.equals("channel"))
            return type = ChatType.CHANNEL;
        else if (input.equals("group"))
            return type = ChatType.GROUP;
        return null;
    }

    public int isContinue(String input) {
        input = input.toLowerCase().trim();
        switch (input) {
            case "yes":
            case "y":
                return 1;
            case "no":
            case "n":
                return 2;
            default:
                return 3;
        }
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

    public Map<Long, String> getMembers() {
        return members;
    }

    public void setMembers(List<Long> members2, List<String> names) {
        if (members == null)
            members = new HashMap<>();
        for (int i = 0; i < members2.size(); i++)
            members.put(members2.get(i), names.get(i));
    }

    public int changePermission(String nextLine) {
        if (permissions == null)
            permissions = new HashMap<>();
        nextLine = nextLine.trim().toLowerCase();
        if (nextLine.startsWith("-done"))
            return 0;
        Matcher m;
        if ((m = AppRegex.CHANGE_PERMISSIONS.getMatcher(nextLine)) == null)
            return 1;
        if (!members.values().contains(m.group("username")))
            return 2;
        ChatPermission permission = getPermission(m.group("permission"));
        if (permission == null)
            return 3;
        if (m.group("username").equals(User.getCurrentUser().getUsername()))
            return 5;
        permissions.put(m.group("username"), permission);
        return 4;
    }

    public ChatPermission getPermission(String input) {
        input = input.trim().toLowerCase();
        for (ChatPermission permission : ChatPermission.values())
            if (permission.name().toLowerCase().trim().equals(input))
                return permission;
        return null;
    }

    public Map<String, ChatPermission> getPermissions() {
        return permissions;
    }

    public int getId(String nextLine) {
        nextLine = nextLine.trim();
        if (nextLine.toLowerCase().equals("-none")) {
            linkID = null;
            return 3;
        } else if (nextLine.toLowerCase().equals("skip")) {
            return 5;
        } else if (AppRegex.CHAT_ID.matches(nextLine)) {
            if (Chat.getByLinkID(nextLine) != null)
                return 0;
            linkID = nextLine;
            return 2;
        }
        return 1;
    }

    public Chat makeChat() {
        Chat chat = new Chat(name, type);
        Map<Long, ChatPermission> memberWithPermit = new HashMap<Long, ChatPermission>();
        for (Long member : members.keySet())
            memberWithPermit.put(member, permissions.get(members.get(member)));
        return chat.setLinkID(linkID).addAll(memberWithPermit);
    }

    public int parseMember(String input) {
        Matcher m;
        input = input.trim().toLowerCase();
        if ((m = AppRegex.ADD_USER.getMatcher(input)) != null) {
            if (members.size() > 1 && type == ChatType.PRIVATE)
                return 6;
            members.put(User.getID(m.group("username")), m.group("username"));
            return 1;
        } else if ((m = AppRegex.REMOVE_USER.getMatcher(input)) != null) {
            if (members.remove(User.getID(m.group("username")), m.group("username")))
                return 2;
            return 3;
        } else if (input.equals("-done")) {
            setDefaultPermissions();
            return 4;
        } else if (input.equals("-reset")) {
            members.clear();
            members.putAll(initialMembers);
            return 5;
        }
        return 0;
    }

    public NewChatController setType(ChatType type) {
        this.type = type;
        return this;
    }

    public NewChatController setName(String name) {
        this.name = name;
        return this;
    }

    public String getLinkID() {
        return linkID;
    }

    public NewChatController setLinkID(String linkID) {
        this.linkID = linkID;
        return this;
    }

}
