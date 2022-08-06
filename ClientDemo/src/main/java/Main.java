import MessagingServer.ServerConnection;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long usid = 15;
        System.out.println(usid);
        String text = sc.next();
        ServerConnection k = ServerConnection.getInstance(usid);
        while (!text.equals("exit")) {
            text = sc.next();
            k.notifyUseres(10, usid);
        }
        sc.close();
    }
}
