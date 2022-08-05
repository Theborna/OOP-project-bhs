package com.company.Socket;

import com.company.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private Set<Packet> packets = new HashSet<>();

    private static Server server = null;
    private final int port;


    private Server(int port) {
        this.port = port;
        server = this;
    }

    public static Server getServer(int port) {
        if (server == null) {
            server = new Server(port);
        }
        return server;
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Log.logger.info("The dicky server started");
        Socket sk = serverSocket.accept();

    }
}
