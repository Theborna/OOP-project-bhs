package server.Socket;

import server.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Server server = null;
    private final int port;
    private ServerSocket serverSocket;
    private static long client;

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
        serverSocket = new ServerSocket(port);
        Log.logger.info("The dicky server started");
        while (true) {
            Socket sk = serverSocket.accept();
            Thread thread = new Thread(new Client(sk), "Client-" + client++);
            thread.setDaemon(true);
            thread.start();
        }
    }
}
