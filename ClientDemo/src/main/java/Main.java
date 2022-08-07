import MessagingServer.ServerConnection;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long usId = 15;
        System.out.println(usId);
        String text = sc.next();
        ServerConnection k = ServerConnection.getInstance(usId);
        while (!text.equals("exit")) {
            text = sc.next();
            k.notifyUseres(10, usId);
        }
        sc.close();
    }
}
