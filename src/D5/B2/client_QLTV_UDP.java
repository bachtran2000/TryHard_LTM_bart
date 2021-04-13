package D5.B2;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class client_QLTV_UDP {

    public static void menu() {
        System.out.println("1. Hien all thong tin sach");
        System.out.println("2. Cho muon sach");
        System.out.println("3. Tra sach");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket cl = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        System.out.println("success");
        while (true) {
            menu();
            String c = new Scanner(System.in).nextLine();

            byte[] data_send = new byte[1024];
            data_send = c.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
            cl.send(packet_send);

            switch (c) {
                case "1":
                    byte[] data_re = new byte[1024];
                    DatagramPacket packet_re = new DatagramPacket(data_re, data_re.length);
                    cl.receive(packet_re);
                    String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    System.out.println(str_re);
                    break;
                case "2":
                    System.out.println("Nhap vao id sach can muon: ");
                    c = new Scanner(System.in).nextLine();
                    data_send = new byte[1024];
                    data_send = c.getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    cl.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, data_re.length);
                    cl.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    System.out.println(str_re);
                    break;
                case "3":
                    System.out.println("Nhap vao id sach can tra: ");
                    c = new Scanner(System.in).nextLine();
                    data_send = new byte[1024];
                    data_send = c.getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    cl.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, data_re.length);
                    cl.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    System.out.println(str_re);
                    break;
            }
        }
    }
}
