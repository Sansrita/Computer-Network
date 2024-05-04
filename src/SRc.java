import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SRc {
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8011);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            int v[] = new int[8];
            int n = 0;
            for (int i = 0; i < 8; i++) {
                v[i] = i + 1;
                out.write(v[i]);
                out.flush();
                System.out.println("Frame " + v[i] + " sent");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                if (in.read()!= v[i]) {
                    System.out.println("Request to retransmit from packet no " + (i + 1) + " again!!");
                    n = i;
                    out.write(n);
                    out.flush();
                }
                System.out.println();
                v[n] = in.read();
                System.out.println("Received frame is: " + v[n]);
            }
            System.out.println("quiting");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}