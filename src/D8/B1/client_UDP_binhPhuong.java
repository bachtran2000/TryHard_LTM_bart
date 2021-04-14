package D8.B1;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class client_UDP_binhPhuong {
    public static void main(String[] args) throws IOException {
        DatagramSocket cl = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        System.out.println("Nhap vao 1 so nguyen bat ki: ");
        byte[] data_send = new byte[1024];
        data_send = (new Scanner(System.in).nextLine()).getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet_send = new DatagramPacket(data_send,data_send.length,ip,2349);
        cl.send(packet_send);

        byte[] data_re = new byte[1024];
        DatagramPacket pack_re = new DatagramPacket(data_re,0,data_re.length);
        cl.receive(pack_re);
        String str_re = new String(pack_re.getData(),0,pack_re.getLength());
        System.out.println("Binh phuong: "+str_re);
    }
}
