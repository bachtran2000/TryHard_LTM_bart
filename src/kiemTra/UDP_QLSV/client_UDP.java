package kiemTra.UDP_QLSV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class client_UDP {
    public static void menu() {
        System.out.println("1. Show all");
        System.out.println("2. Danh sach hoc bong");
        System.out.println("3. Nhap diem");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket cl = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();

            byte[] data_send = new byte[1024];
            data_send = (c + "").getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
            cl.send(packet_send);

            switch (c) {
                case 1:
                    System.out.println("Danh sach hoc sinh");
                    byte[] data_re = new byte[1024];
                    DatagramPacket packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    cl.receive(packet_re);
                    String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String result = str_re;
                    System.out.println(result);
                    break;
                case 2:
                    System.out.println("Danh sach dat hoc bong");
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    cl.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    result = str_re;
                    System.out.println(result);
                    break;
                case 3:
                    System.out.println("Nhap ma sinh vien:");
                    String str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = str_send.getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
                    cl.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    cl.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());


                    if (str_re.equalsIgnoreCase("Nhap diem")) {
                        System.out.println(str_re);
                        str_send = new Scanner(System.in).nextLine();

                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
                        cl.send(packet_send);

                    } else {
                        System.out.println("Them thong tin sinh vien");
                        System.out.print("Ten: ");
                        str_send = new Scanner(System.in).nextLine();
                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
                        cl.send(packet_send);
                        System.out.print("Dia chi: ");
                        str_send = new Scanner(System.in).nextLine();
                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
                        cl.send(packet_send);
                        System.out.print("Gioi tinh: ");
                        str_send = new Scanner(System.in).nextLine();
                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
                        cl.send(packet_send);
                        System.out.print("Diem tong ket: ");
                        str_send = new Scanner(System.in).nextLine();
                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, ip, 1234);
                        cl.send(packet_send);
                    }
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    cl.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                    System.out.println(str_re);
                    break;
            }
        }

    }
}
