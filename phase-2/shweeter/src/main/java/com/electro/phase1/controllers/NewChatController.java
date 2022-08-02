package com.electro.phase1.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.controlsfx.control.textfield.CustomTextField;

import com.electro.App;
import com.electro.phase1.AppRegex;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.ChatType;
import com.electro.phase1.models.node.user.User;
import com.electro.views.component.ErrorNotification;

import javafx.scene.image.ImageView;

public class NewChatController {

    private static NewChatController instance;

    private String name;
    private ChatType type;
    private Map<String, Long> users;
    private Long newUserID;

    public NewChatController() {
        name = null;
        type = null;
        users = new HashMap<String, Long>();
    }

    public boolean setChatName(CustomTextField field) {
        String name = field.getText();
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
                // System.out.println(id);
                users.put(name, newUserID);
                return true;
            default:// should not happen
                return false;
        }
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
        if (type == ChatType.PRIVATE && users.values().size() > 0)
            return 2;
        if (users.values().contains(newUserID))
            return 3;
        // System.out.println(newUserID);
        return 4;
    }

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

    public void setType(ChatType type) {
        this.type = type;
    }

    public static NewChatController getInstance() {
        if (instance == null)
            instance = new NewChatController();
        return instance;
    }

    public boolean finalizeChat() {
        if (users.isEmpty())
            return false;
        Chat chat = new Chat(name, type);
        chat.addAll(users.values());
        chat.sendToDB();
        reset();
        return true;
    }

    public static void reset() {
        instance = null;
    }

    public boolean removeMember(String text) {
        if (users.containsKey(text)) {
            users.remove(text);
            return true;
        }
        new ErrorNotification("user " + text + " is not in the members list");
        return false;
    }

    public void clearMembers() {
        users.clear();
    }
}
