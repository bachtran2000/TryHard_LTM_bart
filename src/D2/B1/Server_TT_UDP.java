package D2.B1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server_TT_UDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(2349);

        byte[] data_re = new byte[1024];
        DatagramPacket packet_re = new DatagramPacket(data_re, data_re.length);
        server.receive(packet_re);
        String str_re1 = new String(packet_re.getData(), 0, packet_re.getLength());

        data_re = new byte[1024];
        packet_re = new DatagramPacket(data_re, data_re.length);
        server.receive(packet_re);
        String str_re2 = new String(packet_re.getData(), 0, packet_re.getLength());

        String c = Integer.parseInt(str_re1) * Integer.parseInt(str_re2) + "";

        byte[] data_send = new byte[1024];
        data_send = c.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
        server.send(packet_send);
    }
}
