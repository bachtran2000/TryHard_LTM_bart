package D9.B2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class server_UDP_QLTV2 {
    static ArrayList<Sach> listS = new ArrayList<>();

    public static void ReadFile() {
        try {
            File f = new File("sach.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] result;
                result = line.split("\\$");
                Sach m = new Sach();
                m.setId(result[0]);
                m.setTen(result[1]);
                m.setNm(result[2]);
                listS.add(m);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteFile() {
        try {
            File f = new File("sach.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < listS.size(); i++) {
                if (i < listS.size())
                    fw.write(listS.get(i).toString() + "\n");
                else
                    fw.write(listS.get(i).toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkValid(String id) {
        for (int i = 0; i < listS.size(); i++) {
            if (id.equalsIgnoreCase(listS.get(i).getId()))
                return true;
        }
        return false;
    }

    public static boolean checkMuon(String id) {
        if (checkValid(id)) {
            for (int i = 0; i < listS.size(); i++)
                if (id.equalsIgnoreCase(listS.get(i).getId()))
                    if ("chua muon".equalsIgnoreCase(listS.get(i).getNm()))
                        return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        DatagramSocket sv = new DatagramSocket(2349);
        ReadFile();

        byte[] data_re = new byte[1024];
        DatagramPacket pack_re = new DatagramPacket(data_re, 0, data_re.length);
        sv.receive(pack_re);
        String str_re = new String(pack_re.getData(), 0, pack_re.getLength());
        String c = str_re;
        String str_send = "";

        while (true) {
            switch (c) {
                case "1":
                    str_send = "";
                    for (int i = 0; i < listS.size(); i++) {
                        str_send += listS.get(i).toString() + "\n";
                    }
                    break;
                case "2":

                    data_re = new byte[1024];
                    pack_re = new DatagramPacket(data_re, 0, data_re.length);
                    sv.receive(pack_re);
                    str_re = new String(pack_re.getData(), 0, pack_re.getLength());
                    String id = str_re;
                    if (checkValid(id)) {
                        byte[] data_send = new byte[1024];
                        data_send = "true".getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, pack_re.getAddress(), pack_re.getPort());
                        sv.send(packet_send);

                        if (checkMuon(id)) {
                            data_send = new byte[1024];
                            data_send = "true".getBytes(StandardCharsets.UTF_8);
                            packet_send = new DatagramPacket(data_send, data_send.length, pack_re.getAddress(), pack_re.getPort());
                            sv.send(packet_send);

                            data_re = new byte[1024];
                            pack_re = new DatagramPacket(data_re, 0, data_re.length);
                            sv.receive(pack_re);
                            str_re = new String(pack_re.getData(), 0, pack_re.getLength());
                            String name = str_re;
                            for (int i = 0; i < listS.size(); i++) {
                                if (id.equalsIgnoreCase(listS.get(i).getId()))
                                    listS.get(i).setNm(name);
                            }
                            str_send = "Muon sach thanh cong";
                        } else {
                            str_send = "Sach dang co nguoi muon";
                        }
                    } else {
                        str_send = "Sach khong co trong thu vien";
                        byte[] data_send = new byte[1024];
                        data_send = str_send.getBytes(StandardCharsets.UTF_8);
                        DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, pack_re.getAddress(), pack_re.getPort());
                        sv.send(packet_send);
                    }
                    WriteFile();
                    break;
            }
            byte[] data_send = new byte[1024];
            data_send = str_send.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet_send = new DatagramPacket(data_send, data_send.length, pack_re.getAddress(), pack_re.getPort());
            sv.send(packet_send);
        }
    }
}
