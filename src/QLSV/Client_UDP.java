package QLSV;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client_UDP {
    public static void menu() {
        System.out.println("1. Show all");
        System.out.println("2. Tim sinh vien");
        System.out.println("3. Add sinh vien");
        System.out.println("4. Update thong tin sinh vien");
        System.out.println("5. Delete sinh vien");
        System.out.println("6. Exit");
        System.out.printf("Chon: ");
    }

    public static void menu2() {
        System.out.println("1. Update All");
        System.out.println("2. Update ten");
        System.out.println("3. Update ngay sinh");
        System.out.println("4. Update MSV");
        System.out.println("5. Update Diem");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();

            byte[] data_send = new byte[1024];
            data_send = (c + "").getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
            client.send(packet_send);

            switch (c) {
                case 1:
                    byte[] data_re = new byte[1024];
                    DatagramPacket packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    client.receive(packet_re);
                    String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String result = str_re;
                    System.out.println(result);
                    break;
                case 2:
                    System.out.printf("Nhap ma sinh vien can tim: ");
                    String str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    client.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    result = str_re;
                    System.out.println(result);
                    break;
                case 3:
                    System.out.println("Nhap thong tin sinh sinh");

                    System.out.printf("Ma sinh vien: ");
                    str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    System.out.printf("Ho ten: ");
                    str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    System.out.printf("Ngay sinh: ");
                    str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    System.out.printf("Diem TB: ");
                    str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    client.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    result = str_re;
                    System.out.println(result);
                    break;
                case 4:
                    System.out.printf("Ma sinh vien can update: ");
                    str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    client.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    result = str_re;

                    if (result.equals("1")) {
                        menu2();
                        int chon = new Scanner(System.in).nextInt();

                        data_send = new byte[1024];
                        data_send = (chon + "").getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                        client.send(packet_send);

                        switch (chon) {
                            case 1:
                                System.out.printf("Ma sinh vien: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);

                                System.out.printf("Ho ten: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);

                                System.out.printf("Ngay sinh: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);

                                System.out.printf("Diem TB: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);

                                data_re = new byte[1024];
                                packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                client.receive(packet_re);
                                str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                result = str_re;
                                System.out.println(result);
                                break;
                            case 2:
                                System.out.printf("Ho ten: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);
                                break;
                            case 3:
                                System.out.printf("Ngay sinh: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);
                                break;
                            case 4:
                                System.out.printf("Ma sinh vien: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);
                                break;
                            case 5:
                                System.out.printf("Diem TB: ");
                                str_send = new Scanner(System.in).nextLine();

                                data_send = new byte[1024];
                                data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                                packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                                client.send(packet_send);
                                break;
                        }

                    } else {
                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        client.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                        result = str_re;
                        System.out.println(result);
                    }
                    break;
                case 5:
                    System.out.printf("Ma sinh vien can xoa: ");
                    str_send = new Scanner(System.in).nextLine();

                    data_send = new byte[1024];
                    data_send = (str_send + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, ip, 2349);
                    client.send(packet_send);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    client.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    result = str_re;
                    System.out.println(result);
                    break;
                case 6:
                    exit(0);
            }
        }

    }
}
