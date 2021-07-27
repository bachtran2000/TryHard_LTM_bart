package QLSV;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Server_UDP {
    static ArrayList<SinhVien> listSV = new ArrayList<>();

    public static void ReadFile() throws IOException {
        File f = new File("sinhdien.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] result;
            result = line.split("\\$");

            SinhVien sv = new SinhVien();
            sv.setHt(result[0]);
            sv.setNs(result[1]);
            sv.setMsv(result[2]);
            sv.setDtb(result[3]);
            listSV.add(sv);
        }
        br.close();
        fr.close();
    }

    public static void WriteFile() throws IOException {
        File f = new File("sinhdien.txt");
        FileWriter fw = new FileWriter(f);

        for (int i = 0; i < listSV.size(); i++) {
            if (i <= listSV.size() - 1) {
                fw.write(listSV.get(i).writeFile() + "\n");
            } else fw.write(listSV.get(i).writeFile());
        }
        fw.close();
    }

    public static String showAll() {
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            result += listSV.get(i).toString() + "\n";
        }
        return result;
    }

    public static String findSV(String msv) {

        for (int i = 0; i < listSV.size(); i++) {
            if (msv.equalsIgnoreCase(listSV.get(i).getMsv()))
                return "Tim thay sinh vien: " + listSV.get(i).getHt() + "\n" + listSV.get(i).toString() + "\n";
        }
        return "Khong ton tai sinh vien co ma " + msv + "\n";
    }

    public static boolean isExist(String msv) {
        for (int i = 0; i < listSV.size(); i++) {
            if (msv.equalsIgnoreCase(listSV.get(i).getMsv()))
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket ser = new DatagramSocket(2349);
        ReadFile();

        while (true) {
            byte[] data_re = new byte[1024];
            DatagramPacket packet_re = new DatagramPacket(data_re, 0, data_re.length);
            ser.receive(packet_re);
            String str_re = new String(packet_re.getData(), 0, packet_re.getLength());
            String c = str_re;

            switch (c) {
                case "1":
                    byte[] data_send = new byte[1024];
                    data_send = (showAll() + "").getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
                case "2":
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String msv = str_re;

                    data_send = new byte[1024];
                    data_send = (findSV(msv) + "").getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
                case "3":
                    SinhVien sv = new SinhVien();

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    msv = str_re;
                    sv.setMsv(msv);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String ht = str_re;
                    sv.setHt(ht);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String ns = str_re;
                    sv.setNs(ns);

                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    String dtb = str_re;
                    sv.setDtb(dtb);

                    listSV.add(sv);
                    WriteFile();
                    data_send = new byte[1024];
                    data_send = ("Add thanh cong sinh vien: " + ht).getBytes(StandardCharsets.UTF_8);
                    packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                    ser.send(packet_send);
                    break;
                case "4":
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    msv = str_re;

                    if (isExist(msv)) {
                        data_send = new byte[1024];
                        data_send = ("1").getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);

                        data_re = new byte[1024];
                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                        ser.receive(packet_re);
                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                        String chon = str_re;
                        for (int i = 0; i < listSV.size(); i++) {
                            if (msv.equalsIgnoreCase(listSV.get(i).getMsv())) {
                                switch (chon) {
                                    case "1":
                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        ht = str_re;
                                        listSV.get(i).setHt(ht);

                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        ns = str_re;
                                        listSV.get(i).setNs(ns);

                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        msv = str_re;
                                        listSV.get(i).setMsv(msv);

                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        dtb = str_re;
                                        listSV.get(i).setDtb(dtb);
                                        break;
                                    case "2":
                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        ht = str_re;
                                        listSV.get(i).setHt(ht);
                                        break;
                                    case "3":
                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        ns = str_re;
                                        listSV.get(i).setNs(ns);
                                        break;
                                    case "4":
                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        msv = str_re;
                                        listSV.get(i).setMsv(msv);
                                        break;
                                    case "5":
                                        data_re = new byte[1024];
                                        packet_re = new DatagramPacket(data_re, 0, data_re.length);
                                        ser.receive(packet_re);
                                        str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                                        dtb = str_re;
                                        listSV.get(i).setDtb(dtb);
                                        break;
                                }
                            }
                        }
                        WriteFile();
                        data_send = new byte[1024];
                        data_send = ("Update thanh cong!").getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);
                    } else {
                        data_send = new byte[1024];
                        data_send = ("0").getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);

                        data_send = new byte[1024];
                        data_send = ("Khong tim thay sinh vien can cap nhat thong tin!").getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);
                    }
                    break;
                case "5":
                    data_re = new byte[1024];
                    packet_re = new DatagramPacket(data_re, 0, data_re.length);
                    ser.receive(packet_re);
                    str_re = new String(packet_re.getData(), 0, packet_re.getLength());
                    msv = str_re;
                    int f = 0;

                    for (int i = 0; i < listSV.size(); i++) {
                        if (msv.equalsIgnoreCase(listSV.get(i).getMsv())) {
                            f++;
                            ht = listSV.get(i).getHt();
                            listSV.remove(i);

                            data_send = new byte[1024];
                            data_send = ("Xoa thanh cong sinh vien: " + ht).getBytes(StandardCharsets.UTF_8);
                            packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                            ser.send(packet_send);
                        }
                    }
                    if (f == 0) {
                        data_send = new byte[1024];
                        data_send = ("Khong tim thay sinh vien can xoa ").getBytes(StandardCharsets.UTF_8);
                        packet_send = new DatagramPacket(data_send, data_send.length, packet_re.getAddress(), packet_re.getPort());
                        ser.send(packet_send);
                    }
                    WriteFile();
                    break;
                case "6":
                    exit(0);
                    break;
            }
        }
    }
}
