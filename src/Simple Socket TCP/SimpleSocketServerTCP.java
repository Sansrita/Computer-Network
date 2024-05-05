import java.io.*;
import java.net.*;

public class SimpleSocketServerTCP {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                out.println("Server: " + line);
            }

            in.close();
            out.close();
            socket.close();
        }
    }
}