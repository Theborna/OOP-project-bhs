package MessagingServer;

import java.io.Serializable;

public class recPacket implements Serializable {
    private long chatID;

    public recPacket(long chatID) {
        this.chatID = chatID;
    }

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }
}
