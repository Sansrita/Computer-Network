import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SRs {
    static ServerSocket Serversocket;
    static DataInputStream dis;
    static DataOutputStream dos;

    public static void main(String[] args) throws SocketException {
        try {
            int a[] = {30, 40, 50, 60, 70, 80, 90, 100};
            Serversocket = new ServerSocket(8011);
            System.out.println("waiting for connection");
            Socket client = Serversocket.accept();
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());

            for (int i = 0; i < 8; i++) {
                int n = dis.read();
                System.out.println("Received frame is: " + n);
                if (n!= a[i]) {
                    dos.write(n);
                    dos.flush();
                    System.out.println("Request to retransmit from packet no " + (i + 1) + " again!!");
                } else {
                    dos.write(n);
                    dos.flush();
                    System.out.println("Frame " + n + " accepted");
                }
            }
            System.out.println("quiting");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}