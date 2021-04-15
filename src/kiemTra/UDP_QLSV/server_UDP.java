package kiemTra.UDP_QLSV;

import kiemTra.TCP_QLSV.hocSinh;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class server_UDP {
    static ArrayList<hocSinh> listHS = new ArrayList<>();

    public static void ReadFile() throws IOException {
        File f = new File("hocsinh.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] result = new String[5];
            result = line.split("\\$");
            hocSinh hs = new hocSinh();
            hs.setMHS(result[0]);
            hs.setTen(result[1]);
            hs.setDiaChi(result[2]);
            hs.setGt(result[3]);
            hs.setDTK(Float.parseFloat(result[4]));
            listHS.add(hs);
        }
        fr.close();
        br.close();
    }

    public static void WriteFile() throws IOException {
        File f = new File("hocsinh.txt");
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < listHS.size(); i++) {
            fw.write(listHS.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }

    public static String checkHB() {
        String result = "";
        for (int i = 0; i < listHS.size(); i++) {
            if ((listHS.get(i).getDTK()) >= 8)
                result += listHS.get(i).toShow() + "\n";
        }
        return result;
    }

    public static String checkTontai(String find) {
        String str_send = "Khong ton tai";
        for (int i = 0; i < listHS.size(); i++) {
            if (find.equals(listHS.get(i).getMHS())) {
                str_send = "Nhap diem";
            }
        }
        return str_send;
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket ser = new DatagramSocket(1234);

        ReadFile();
        while (true) {
            byte[] data_re = new byte[1024];
            DatagramPacket packet_re = new DatagramPacket(data_re, 0, data_re.length);
            ser.receive(packet_re);
            String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
            String c = str_re;
            switch (c) {
                case "1":

                    String str_send = "";
                    for (int i = 0; i < listHS.size(); i++) {
                        str_send += listHS.get(i).toShow() + "\n";
                    }
                    byte[] data_send = new byte[1024];
                    data_send = str_send.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);

                    break;
                case "2":
                    str_send = checkHB();
                    data_send = new byte[1024];
                    data_send = str_send.getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
                case "3":
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String id = str_re;

                    str_send = checkTontai(str_re);

                    if (str_send.equalsIgnoreCase("Nhap diem")) {
                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);

                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        ser.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                        for (int i = 0; i < listHS.size(); i++) {
                            if (id.equals(listHS.get(i).getMHS()))
                                listHS.get(i).setDTK(Float.parseFloat(str_re));
                        }
                        str_send = "Nhap diem thanh cong!";
                    } else {
                        data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);

                        hocSinh hs = new hocSinh();
                        hs.setMHS(id);

                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        ser.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                        hs.setTen(str_re);

                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        ser.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                        hs.setDiaChi(str_re);

                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        ser.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                        hs.setGt(str_re);

                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        ser.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());

                        hs.setDTK(Float.parseFloat(str_re));
                        listHS.add(hs);
                        str_send = "Nhap thong tin thanh cong!";
                    }
                    WriteFile();

                    data_send = new byte[1024];
                    data_send = str_send.getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
            }
        }
    }
}
