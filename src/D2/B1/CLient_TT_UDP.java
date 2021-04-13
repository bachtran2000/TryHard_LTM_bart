package D2.B1;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CLient_TT_UDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        byte[] data_send = new byte[1024];
        System.out.println("Nhap vao so nguyen a: ");
        String a = new Scanner(System.in).nextLine();
        data_send = a.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length,ip,2349);
        client.send(packet_send);

        data_send = new byte[1024];
        System.out.println("Nhap vao so nguyen a: ");
        String b = new Scanner(System.in).nextLine();
        data_send = a.getBytes(StandardCharsets.UTF_8);
        packet_send = new DatagramPacket(data_send, data_send.length,ip,2349);
        client.send(packet_send);

        byte[] data_re = new byte[1024];
        DatagramPacket packet_re = new DatagramPacket(data_re,data_re.length);
        client.receive(packet_re);
        String str_re = new String(packet_re.getData(),0, packet_re.getLength());
        System.out.println(a+"*"+b+" = "+str_re);
    }
}
