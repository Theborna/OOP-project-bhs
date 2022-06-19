package com.project.models.node;

import java.sql.Date;

public class Chat extends node {
    private static long chatId;
    private String name;
    private ChatType type;

    public Chat(String name, ChatType type) {
        this.name = name;
        this.type = type;
        setData(chatId++, new Date(1), new Date(2));
    }

    public String getName() {
        return name;
    }

    public ChatType getType() {
        return type;
    }
}
