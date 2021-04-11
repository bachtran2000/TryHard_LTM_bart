package D4.b1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ServerUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(1506);
        byte[] receiveData = new byte[1024];

        DatagramPacket packetReceive = new DatagramPacket(receiveData, receiveData.length);
        server.receive(packetReceive);
        String recei = new String(packetReceive.getData(), 0, packetReceive.getLength());

        String resuilt = recei.toUpperCase(Locale.ROOT);
        System.out.println(resuilt);

        byte[] sendData = new byte[1024];
        sendData = resuilt.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetsend = new DatagramPacket(sendData, sendData.length, packetReceive.getAddress(), packetReceive.getPort());
        server.send(packetsend);


    }
}
