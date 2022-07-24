package com.project.controllers;

import com.project.AppRegex;
import com.project.models.node.ChatType;

public class NewChatController {

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

}
