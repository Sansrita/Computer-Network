// Server.java
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // Create a server socket bound to port 12345
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started");

            // Listen for incoming connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Create input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read data from the client and echo it back
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client: " + message);
                out.println("Server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
