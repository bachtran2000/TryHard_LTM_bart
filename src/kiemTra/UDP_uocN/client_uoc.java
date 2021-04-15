package kiemTra.UDP_uocN;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class client_uoc {
    public static void main(String[] args) throws IOException {
        DatagramSocket cl = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        while (true) {
            System.out.print("Nhap vao 1 so: ");
            String n = new Scanner(System.in).nextLine();

            byte[] data_send = new byte[1024];
            data_send = n.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, ip, 9432);
            cl.send(packet_send);

            byte[] data_re = new byte[1024];
            DatagramPacket packet_re = new DatagramPacket(data_re, 0, data_re.length);
            cl.receive(packet_re);
            String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
            String result = str_re;
            data_re = new byte[1024];
            packet_re = new DatagramPacket(data_re, 0, data_re.length);
            cl.receive(packet_re);
            str_re = new String(packet_re.getData(), 0, packet_re.getLength());
            String soUoc = str_re;

            System.out.println("Cac uoc cua " + n + " la: " + result);
            System.out.println("Tong so uoc cua "+n+" la: "+soUoc);
        }
    }
}