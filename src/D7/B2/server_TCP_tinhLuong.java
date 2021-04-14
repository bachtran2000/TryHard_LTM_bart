package D7.B2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class server_TCP_tinhLuong {
    static ArrayList<cauThu> listCT = new ArrayList<>();

    public static void ReadFile() {
        try {
            File f = new File("cauThu.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] result;
                result = line.split("\\$");
                cauThu m = new cauThu();
                m.setId(result[0]);
                m.setTen(result[1]);
                m.setNs(result[2]);
                m.setVt(result[3]);
                m.setLmd(Float.parseFloat(result[4]));
                listCT.add(m);
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
            File f = new File("cauThu.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < listCT.size(); i++) {
                if (i < listCT.size())
                    fw.write(listCT.get(i).toString() + "\n");
                else
                    fw.write(listCT.get(i).toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String addCT(DataInputStream dis) throws IOException {
        cauThu ct = new cauThu();
        ct.setId(String.valueOf(listCT.size() + 1));
        ct.setTen(dis.readUTF());
        ct.setNs(dis.readUTF());
        ct.setVt(dis.readUTF());
        ct.setLmd(dis.readFloat());
        listCT.add(ct);
        WriteFile();
        return "Add thanh cong!";
    }

    public static boolean checkValid(String id) {
        for (int i = 0; i < listCT.size(); i++) {
            if (id.equals(listCT.get(i).getId()))
                return true;
        }
        return false;
    }

    public static String tinhLuong(String id, int st) {
        float sumLuong = 0;
        String result = "Not found!";
        String vt = "";
        String name = "";
        float lmd = 0;
        for (int i = 0; i < listCT.size(); i++) {
            if (id.equals(listCT.get(i).getId())) {
                vt = listCT.get(i).getVt();
                name = listCT.get(i).getTen();
                lmd = listCT.get(i).getLmd();
                switch (vt) {
                    case "tien dao":
                        sumLuong = (float) (lmd + st * 0.025);
                        break;
                    case "tien ve":
                    case "hau ve":
                        sumLuong = (float) (lmd + st * 0.02);
                        break;
                    case "thu mon":
                        sumLuong = (float) (lmd + st * 0.015);
                        break;
                }
                result = "Tong luong cua " + name.toUpperCase(Locale.ROOT) + " la: " + sumLuong;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        ReadFile();
        ServerSocket sv = new ServerSocket(2349);
        Socket ml = sv.accept();

        DataInputStream dis = new DataInputStream(ml.getInputStream());
        DataOutputStream dos = new DataOutputStream(ml.getOutputStream());

        while (true) {
            int c = dis.readInt();
            switch (c) {
                case 1:
                    dos.writeUTF(addCT(dis));
                    break;
                case 2:
                    String id_re = dis.readUTF();
                    int st = dis.readInt();
                    String str_send = tinhLuong(id_re,st);
                    System.out.println(str_send);
                    dos.writeUTF(str_send);
                    break;
            }
        }
    }
}
