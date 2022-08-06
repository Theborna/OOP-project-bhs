package com.company.Socket;

import java.io.Serializable;

public class sentPacket implements Serializable {
    private long chatID;

    public sentPacket(long chatID) {
        this.chatID = chatID;
    }

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }
}
