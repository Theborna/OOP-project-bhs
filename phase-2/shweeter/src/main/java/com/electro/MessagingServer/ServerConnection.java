package com.electro.MessagingServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.electro.views.MessageListView;

public class ServerConnection {
    private static final String SERVER_URL = "84.17.35.119";
    private static final int SERVER_PORT = 9090;

    private static ServerConnection sc = null;
    private final Socket socket;
    private boolean firstReq = true;

    private ServerConnection(long usid) throws IOException {
        socket = new Socket(SERVER_URL, SERVER_PORT);
        notifyUsers(0, usid);
    }

    public static ServerConnection getInstance(long usid) throws IOException {
        if (sc == null)
            sc = new ServerConnection(usid);
        return sc;
    }

    public void notifyUsers(long chatid, long usid) throws IOException {
        RecPacket packet = new RecPacket(chatid, usid);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(packet);
        out.flush();
        System.out.println(chatid);
        // out.close();
        if (firstReq)
            serverListener();
        firstReq = false;
    }

    private void updater(long chatid) {
        System.out.println("update chat with id = " + chatid);
        MessageListView.update(chatid);
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
