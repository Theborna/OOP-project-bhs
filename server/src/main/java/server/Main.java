package server;

import server.Socket.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Start the fucking messaging server
        try {
            Log.init();
            Server.getServer(9090).startServer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            Log.logger.warning(e.getMessage());
            e.printStackTrace();
        }
    }
}
