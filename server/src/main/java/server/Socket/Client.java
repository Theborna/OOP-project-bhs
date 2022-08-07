package server.Socket;

import server.DB.DataBase;
import server.Log;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Client implements Runnable {
    private RecPacket packet;
    private Socket client;
    private long usId;
    private final BufferedReader in;
    private final PrintWriter out;
    private static Set<Client> clients = new HashSet<>();

    public Client(Socket client) throws IOException, ClassNotFoundException {
        this.client = client;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());

    }

    public long getUsid() {
        return usId;
    }

    public void setUsid(long usId) {
        this.usId = usId;
    }

    public RecPacket getPacket() {
        return packet;
    }

    public void setPacket(RecPacket packet) {
        this.packet = packet;
    }

    public void sendRefreshSignal(long chatId) throws IOException {

        out.println(new sentPacket(chatId).toString());
        out.flush();
        // out.close();
        Log.logger.info("refreshed: " + usId + " ,chat:" + chatId);
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Client client1 = (Client) o;

        if (client1.equals(client) && client != null)
            return true;
        return false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                packet = new RecPacket(in.readLine());
                if (packet.getChatid() != 0) {
                    // System.out.println(packet);
                    // Log.logger.info(packet.toString());
                    ArrayList<Long> users = DataBase.getInstance().getUserIDByChatID(packet.getChatid());
                    // users.remove(packet.getUsid());
                    notifyUsers(users);
                } else {
                    usId = packet.getUsid();
                    if (clients.add(this))
                        Log.logger.info("Server: DickHead with US_ID = " + packet.getUsid()
                                + " connected to the messaging server");
                }
            }
        } catch (SQLException | IOException e) {
            Log.logger.warning(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Client getClientByUserID(long userID) {
        for (Client c : clients) {
            if (c.getUsid() == userID)
                return c;
        }
        return null;
    }

    private void notifyUsers(ArrayList<Long> users) throws IOException {
        for (long user : users) {
            Client c = getClientByUserID(user);
            if (c != null) {
                c.sendRefreshSignal(packet.getChatid());

            }
            // System.out.println(user);
        }
    }
}
