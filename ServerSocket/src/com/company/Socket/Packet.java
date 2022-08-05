package com.company.Socket;

import java.io.Serializable;

public class Packet implements Serializable {
    private long chatid;
    private long usid;

    public Packet(long chatid, long usid) {
        this.chatid = chatid;
        this.usid = usid;
    }

    public long getChatid() {
        return chatid;
    }

    public void setChatid(long chatid) {
        this.chatid = chatid;
    }

    public long getUsid() {
        return usid;
    }

    public void setUsid(long usid) {
        this.usid = usid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Packet packet = (Packet) o;
        return packet.getUsid() == usid;
    }

}
