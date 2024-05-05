import java.net.*;

public class UDPEchoServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create a UDP socket bound to port 9999
            socket = new DatagramSocket(9999);

            System.out.println("Server started, waiting for client...");

            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData;

                // Receive data from the client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + message);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Prepare data to send back to the client
                sendData = message.getBytes();

                // Create packet to send to the client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Send the response back to the client
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
