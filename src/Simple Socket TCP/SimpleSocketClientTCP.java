import java.io.*;
import java.net.*;

public class SimpleSocketClientTCP {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int port = 12345;

        Socket socket = new Socket(serverAddress, port);
        System.out.println("Connected to server");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Hello, server!");
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();
        socket.close();
    }
}