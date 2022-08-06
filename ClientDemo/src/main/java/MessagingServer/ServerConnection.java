package MessagingServer;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection {
    private static final String SERVER_URL = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

    private static ServerConnection sc = null;
    private final Socket socket;

    private ServerConnection() throws IOException {
        socket = new Socket(SERVER_URL, SERVER_PORT);
    }

    public static ServerConnection getInstance() throws IOException {
        if(sc != null)
            sc = new ServerConnection();
        return sc;
    }

    public

}
