package D2.Bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);
        byte[] dataRecie = new byte[1024];

        DatagramPacket packRecie = new DatagramPacket(dataRecie,dataRecie.length);
        server.receive(packRecie);
        String str_recie = new String(packRecie.getData(),0,packRecie.getLength());
        System.out.println(str_recie);

        byte[] sendData = new byte[1024];
        String send = str_recie.toUpperCase(Locale.ROOT);
        sendData = send.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(sendData,sendData.length,packRecie.getAddress() , packRecie.getPort());
        server.send(packetSend);

    }
}
