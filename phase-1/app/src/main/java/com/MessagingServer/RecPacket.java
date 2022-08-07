package com.MessagingServer;

import java.io.Serializable;

public class RecPacket implements Serializable {
    private long chatId;
    private long usId;

    public RecPacket(long chatId, long usId) {
        this.chatId = chatId;
        this.usId = usId;
    }

    public long getChatid() {
        return chatId;
    }

    public void setChatid(long chatId) {
        this.chatId = chatId;
    }

    public long getUsid() {
        return usId;
    }

    public void setUsid(long usId) {
        this.usId = usId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecPacket packet = (RecPacket) o;
        return packet.getUsid() == usId;
    }

    @Override
    public String toString() {
        return chatId + " " + usId;
    }
}
