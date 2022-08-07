package com.electro.MessagingServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.electro.phase1.util.Log;
import com.electro.views.MessageListView;
import com.electro.views.component.MessageNotification;

public class ServerConnection {
    private static final String SERVER_URL = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    private static ServerConnection sc = null;
    private final Socket socket;
    private boolean firstReq = true;

    private ServerConnection(long usId) throws IOException {
        socket = new Socket(SERVER_URL, SERVER_PORT);
        notifyUsers(0, usId);
    }

    public static ServerConnection getInstance(long usId) throws IOException {
        if (sc == null)
            sc = new ServerConnection(usId);
        return sc;
    }

    public void notifyUsers(long chatId, long usId) throws IOException {
        System.out.println("ServerConnection.notifyUsers()");
        RecPacket packet = new RecPacket(chatId, usId);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(packet);
        out.flush();
        System.out.println(chatId);
        // out.close();
        if (firstReq)
            serverListener();
        firstReq = false;
    }

    private void updater(long chatId) {
        System.out.println("update chat with id = " + chatId);
        Log.logger.info("update chat with id = " + chatId);
        MessageListView.update(chatId);
        new MessageNotification(chatId);
    }

    private void serverListener() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        String input = in.readLine();
                        System.out.println("ServerConnection.serverListener().new Runnable() {...}.run()");
                        // update
                        updater(new sentPacket(input).getChatID());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }

}
