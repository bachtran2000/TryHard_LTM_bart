package D8.B1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class server_UDP_binhPhuong {
    public static void main(String[] args) throws IOException {
        DatagramSocket ser = new DatagramSocket(2349);

        byte[] data_re = new byte[1024];
        DatagramPacket pack_re = new DatagramPacket(data_re,0,data_re.length);
        ser.receive(pack_re);
        String str_re = new String(pack_re.getData(),0,pack_re.getLength());

        String str_send = Integer.parseInt(str_re)*Integer.parseInt(str_re)+"";

        byte[] data_send = new byte[1024];
        data_send = str_send.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet_send = new DatagramPacket(data_send,data_send.length,pack_re.getAddress(), pack_re.getPort());
        ser.send(packet_send);
    }
}
