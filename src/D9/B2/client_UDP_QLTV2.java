package D9.B2;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class client_UDP_QLTV2 {
    public static void menu(){
        System.out.println("1. Show all");
        System.out.println("2. Muon sach");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        while (true){
            menu();
            String c = new Scanner(System.in).nextLine();
            byte[] data_send  =new byte[1024];
            data_send = c.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length,ip,2349);
            client.send(packet_send);

            switch (c){
                case "1":
                    byte[] data_re = new byte[1024];
                    DatagramPacket pack_re  =  new DatagramPacket(data_re,0,data_re.length);
                    client.receive(pack_re);
                    String str_re = new String(pack_re.getData(),0,pack_re.getLength());
                    System.out.println(str_re);
                    break;
                case "2":

                    System.out.println("Nhap id sach can muon: ");
                    String str_send = new Scanner(System.in).nextLine();
                    data_send  =new byte[1024];
                    data_send = str_send.getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length,ip,2349);
                    client.send(packet_send);

                    data_re = new byte[1024];
                    pack_re  =  new DatagramPacket(data_re,0,data_re.length);
                    client.receive(pack_re);
                    str_re = new String(pack_re.getData(),0,pack_re.getLength());
                    String result = str_re;

                    if (!result.equals("true"))
                        System.out.println(result);
                    else {
                        data_re = new byte[1024];
                        pack_re  =  new DatagramPacket(data_re,0,data_re.length);
                        client.receive(pack_re);
                        str_re = new String(pack_re.getData(),0,pack_re.getLength());
                        result = str_re;
                        if (result.equals("true")){
                            System.out.println("Nhap ten nguoi muon: ");
                            str_send = new Scanner(System.in).nextLine();
                            data_send  =new byte[1024];
                            data_send = str_send.getBytes(StandardCharsets.UTF_8);
                            packet_send = new DatagramPacket(data_send, data_send.length,ip,2349);
                            client.send(packet_send);

                            data_re = new byte[1024];
                            pack_re  =  new DatagramPacket(data_re,0,data_re.length);
                            client.receive(pack_re);
                            str_re = new String(pack_re.getData(),0,pack_re.getLength());
                            result = str_re;
                            System.out.println(result);
                        }
                        System.out.println(result);
                    }
                    break;
            }
        }
    }
}
