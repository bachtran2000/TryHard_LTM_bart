package kiemTra.UDP_uocN;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class server_uoc {
    public static void main(String[] args) throws IOException {
        DatagramSocket ser = new DatagramSocket(9432);
        while (true) {
            byte[] data_re = new byte[1024];
            DatagramPacket packet_re = new DatagramPacket(data_re, 0, data_re.length);
            ser.receive(packet_re);
            String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
            String str_send = "";
            int n = Integer.parseInt(str_re);
            int flag=0;
            for (int i = 1; i <= n / 2; i++) {
                if (n % i == 0) {
                    str_send += i + "\t";
                    flag++;
                }
            }

            byte[] data_send = new byte[1024];
            data_send = str_send.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
            ser.send(packet_send);

            data_send = new byte[1024];
            data_send = (flag+"").getBytes(StandardCharsets.UTF_8);
            packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
            ser.send(packet_send);
        }
    }
}
