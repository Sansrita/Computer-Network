import java.net.*;

public class UDPEchoClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create a UDP socket
            socket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9999;

            String message = "Hello, Server!";

            // Convert message to bytes
            byte[] sendData = message.getBytes();

            // Create packet to send to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Send the packet to the server
            socket.send(sendPacket);

            // Receive response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}