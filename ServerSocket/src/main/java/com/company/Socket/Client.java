package com.company.Socket;

import com.company.DB.DataBase;
import com.company.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Client implements Runnable {
    private RecPacket packet;
    private Socket client;

    private static Set<Client> clients = new HashSet<>();


    public Client(Socket client) throws IOException, ClassNotFoundException {
        this.client = client;
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        packet = (RecPacket) in.readObject();
        in.close();
        if (clients.add(this))
            Log.logger.info("Server: DickHead with USID = " + packet.getUsid() + " connected to the messaging server");

    }

    public RecPacket getPacket() {
        return packet;
    }

    public void setPacket(RecPacket packet) {
        this.packet = packet;
    }

    public void sendRefreshSignal(long chatId) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        out.writeObject(new sentPacket(chatId));
        out.flush();
        out.close();
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client1 = (Client) o;

        if (client1.equals(client) && client != null)
            return true;
        return false;
    }


    @Override
    public void run() {
        try {
            ArrayList<Long> users = DataBase.getInstance().getUserIDByChatID(packet.getChatid());
            notifyUsers(users);
        } catch (SQLException | IOException e) {
            Log.logger.warning(e.getMessage());
        }
    }

    public static Client getClientByUserID(long userID) {
        for (Client c : clients) {
            if (c.getPacket().getUsid() == userID)
                return c;
        }
        return null;
    }

    private void notifyUsers(ArrayList<Long> users) throws IOException {
        for (long user : users) {
            Client c = getClientByUserID(user);
            if(c != null)
                c.sendRefreshSignal(packet.getChatid());
        }
    }
}
