package D5.B2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class server_UDP_QLTV {
    static ArrayList<Book> listBook = new ArrayList<>();

    public static void ReadFile() throws IOException {
        File f = new File("book.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            String[] result = new String[5];
            result = line.split("\\$");

            Book b = new Book();
            b.setId(result[0]);
            b.setTen(result[1]);
            b.setNxb(result[2]);
            b.setSl(Integer.parseInt(result[3]));
            b.setSlm(Integer.parseInt(result[4]));
            listBook.add(b);
        }
        fr.close();
        br.close();
    }

    public static String showAll() {
        String result = "";
        for (int i = 0; i < listBook.size(); i++) {
            result += listBook.get(i).toString() + "\n";
        }
        return result;
    }

    public static boolean checkEmty(String id) {
        for (int i = 0; i < listBook.size(); i++) {
            if (id.equals(listBook.get(i).getId())) {
                if (listBook.get(i).getSl() > listBook.get(i).getSlm()) {
                    listBook.get(i).setSlm(listBook.get(i).getSlm() + 1);
                    return true;
                }
            }
        }
        return false;
    }

    public static void choMuon(String id, DatagramSocket ser) throws IOException {
        if (checkEmty(id)) {
            byte[] str_send = new byte[1024];
            str_send = "Muon thanh cong!".getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(str_send, 0, str_send.length);
            ser.send(packet_send);
        } else {
            byte[] str_send = new byte[1024];
            str_send = "Sach khong con de muon!".getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(str_send, 0, str_send.length);
            ser.send(packet_send);
        }
    }

    public static void main(String[] args) throws IOException {
        ReadFile();
        DatagramSocket ser = new DatagramSocket(2349);
        System.out.println("success");

        while (true) {
            byte[] data_re = new byte[1024];
            DatagramPacket packet_re = new DatagramPacket(data_re, data_re.length);
            ser.receive(packet_re);
            String choice = new String(packet_re.getData(), 0, packet_re.getLength());

            switch (choice) {
                case "1":

                    String str_send = showAll();
                    System.out.println(str_send);
                    byte[] data_send = new byte[1024];
                    data_send = str_send.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
                case "2":
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, data_re.length);
                    ser.receive(packet_re);
                    String str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                    if (checkEmty(str_re)) {
                        data_send = new byte[1024];
                        data_send = "Muon thanh cong!".getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);
                    } else {
                        data_send = new byte[1024];
                        data_send = "Sach khong con de muon!".getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);
                    }
                    break;
                case "3":
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                    for (int i = 0; i < listBook.size(); i++) {
                        if (str_re.equals(listBook.get(i).getId())) {

                            listBook.get(i).setSlm(listBook.get(i).getSlm() - 1);
                        }

                    }
                    data_send = new byte[1024];
                    data_send = "Tra sach thanh cong!".getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
            }
        }
    }
}
