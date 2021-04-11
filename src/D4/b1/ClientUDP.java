package D4.b1;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        System.out.println("Nhap vao mot cau gi do: ");
        String send = new Scanner(System.in).nextLine();

        byte[] sendData = new byte[1024];
        sendData = send.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packetSend = new DatagramPacket(sendData,sendData.length,ip,2349);
        client.send(packetSend);

        byte[] receiData = new byte[1024];
        DatagramPacket packetRecei = new DatagramPacket(receiData, receiData.length);

        client.receive(packetRecei);
        String recei = new String(packetRecei.getData(),0, packetRecei.getLength());

        System.out.println(recei);
    }
}
