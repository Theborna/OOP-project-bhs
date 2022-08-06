package com.company.Socket;

import com.company.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

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

    public void startServer() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        Log.logger.info("The dicky server started");
        while (true) {
            Socket sk = serverSocket.accept();
            Client cl = new Client(sk);
            new Thread(cl).start();
        }
    }
}

