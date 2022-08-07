package MessagingServer;

import java.io.*;
import java.net.Socket;

public class ServerConnection {
    private static final String SERVER_URL = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    private static ServerConnection sc = null;
    private final Socket socket;
    private boolean firstReq = true;

    private ServerConnection(long usId) throws IOException {
        socket = new Socket(SERVER_URL, SERVER_PORT);
        notifyUseres(0, usId);
    }

    public static ServerConnection getInstance(long usId) throws IOException {
        if (sc == null)
            sc = new ServerConnection(usId);
        return sc;
    }

    public void notifyUseres(long chatId, long usId) throws IOException {
        RecPacket packet = new RecPacket(chatId, usId);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(packet);
        out.flush();
        System.out.println(chatId);
//        out.close();
        if (firstReq)
            serverListener();
        firstReq = false;
    }

    private void updater(long chatId) {
        System.out.println("update chat with id = " + chatId);
    }

    private void serverListener() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        String input = in.readLine();
                        // update
                        updater(new sentPacket(input).getChatID());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }

}
