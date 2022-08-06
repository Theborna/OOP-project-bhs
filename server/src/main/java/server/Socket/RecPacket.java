package server.Socket;

import java.io.Serializable;

public class RecPacket implements Serializable {
    private long chatid;
    private long usid;

    public RecPacket(long chatid, long usid) {
        this.chatid = chatid;
        this.usid = usid;
    }

    public RecPacket(String in) {
        String sp[] = in.trim().split("\\s");
        this.chatid = Long.parseLong(sp[0]);
        this.usid = Long.parseLong(sp[1]);
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
        RecPacket packet = (RecPacket) o;
        return packet.getUsid() == usid;
    }

    @Override
    public String toString() {
        return chatid + " " + usid;
    }
}
