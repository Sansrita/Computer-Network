import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPEchoClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        Socket socket = null;
        PrintWriter output = null;
        BufferedReader input = null;

        try {
            socket = new Socket(hostname, port); // create the socket
            System.out.println("Connected to the server at " + hostname + ":" + port);

            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput = keyboardInput.readLine()) != null) {
                System.out.println("Sending message to server: " + userInput);
                output.println(userInput);

                String serverResponse = input.readLine(); // read the server's response
                System.out.println("Received message from server: " + serverResponse);

                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Error: unknown host " + hostname);
        } catch (IOException e) {
            System.err.println("Error occurred while communicating with the server: " + e.getMessage());
        } finally {
            if (output != null) {
                output.close(); // close the output stream
            }
            if (input != null) {
                try {
                    input.close(); // close the input stream
                } catch (IOException e) {
                    System.err.println("Error occurred while closing the input stream: " + e.getMessage());
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error occurred while closing the socket: " + e.getMessage());
                }
            }
        }
    }
}