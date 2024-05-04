import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerTCP {
    public static void main(String[] args) {
        int port = 5000; // define the port number
        ServerSocket serverSocket = null; // initialize the server socket

        try {
            serverSocket = new ServerSocket(port); // create the server socket
            System.out.println("Server listening for client requests coming in for port " + port);

            while (true) { // infinite loop to listen for client requests
                Socket socket = serverSocket.accept(); // accept a client request
                System.out.println("Client accepted");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println("Received message from client: " + line);
                    output.println("Echo: " + line);
                }

                socket.close(); // close the socket connection
                System.out.println("Closing connection");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while starting the server: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.err.println("Error occurred while closing the server socket: " + e.getMessage());
                }
            }
        }
    }

}
