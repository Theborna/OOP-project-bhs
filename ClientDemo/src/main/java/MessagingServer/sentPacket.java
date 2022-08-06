package MessagingServer;

import java.io.Serializable;

public class sentPacket implements Serializable {
    private long chatID;

    public sentPacket(String in) {
        this.chatID = Long.parseLong(in);
    }

    public sentPacket(long chatID) {
        this.chatID = chatID;
    }

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }

    @Override
    public String toString() {
        return Long.toString(chatID);
    }
}
