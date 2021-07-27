package QLSV;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Server_TCP {
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
            if (i < listSV.size())
                fw.write(listSV.get(i).writeFile() + "\n");
            else
                fw.write(listSV.get(i).writeFile());
        }
        fw.close();
    }

    public static String findSV(DataInputStream dis) throws IOException {
        String msv = dis.readUTF();
        for (int i = 0; i < listSV.size(); i++) {
            if (msv.equalsIgnoreCase(listSV.get(i).getMsv()))
                return listSV.get(i).toString();
        }
        return "Khong tim thay sinh vien!";
    }

    public static String addSV(DataInputStream dis) throws IOException {
        SinhVien sv = new SinhVien();
        String ht = dis.readUTF();
        sv.setHt(ht);

        String ns = dis.readUTF();
        sv.setNs(ns);

        String msv = dis.readUTF();
        sv.setMsv(msv);

        String dtb = dis.readUTF();
        sv.setDtb(dtb);

        listSV.add(sv);
        WriteFile();

        return "Add thanh cong sinh vien " + ht;
    }

    public static String deleteSV(DataInputStream dis) throws IOException {
        String msv = dis.readUTF();
        String ht;
        if (isExist(msv)) {
            for (int i = 0; i < listSV.size(); i++) {
                ht = listSV.get(i).getHt();
                if (msv.equalsIgnoreCase(listSV.get(i).getMsv())) {
                    listSV.remove(i);
                    WriteFile();
                    return "Da xoa thanh cong sinh vien " + ht;
                }
            }
        }
        return "Khong tim thay sinh vien nao!";
    }

    public static String modSV(DataInputStream dis, DataOutputStream dos) throws IOException {
        String msv = dis.readUTF();
        if (isExist(msv)) {
            dos.writeInt(1);
            for (int i = 0; i < listSV.size(); i++) {
                if (msv.equalsIgnoreCase(listSV.get(i).getMsv())) {
                    int c = dis.readInt();
                    switch (c) {
                        case 1:
                            listSV.get(i).setHt(dis.readUTF());

                            listSV.get(i).setNs(dis.readUTF());

                            listSV.get(i).setMsv(dis.readUTF());

                            listSV.get(i).setDtb(dis.readUTF());
                            break;
                        case 2:
                            listSV.get(i).setHt(dis.readUTF());
                            break;
                        case 3:
                            listSV.get(i).setNs(dis.readUTF());
                            break;
                        case 4:
                            listSV.get(i).setMsv(dis.readUTF());
                            break;
                        case 5:
                            listSV.get(i).setDtb(dis.readUTF());
                            break;
                    }
                    WriteFile();
                    return "Cap nhat thong tin thanh cong!";
                }
            }
        } else {
            dos.writeInt(0);
            return "Khong tim thay thong tin can thay doi!";
        }
        return "Khong tim thay thong tin can thay doi!";
    }

    public static String showAll() {
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            result += listSV.get(i).toString() + "\n";
        }
        return result;
    }

    public static boolean isExist(String msv) {
        for (int i = 0; i < listSV.size(); i++) {
            if (msv.equalsIgnoreCase(listSV.get(i).getMsv()))
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_cl = server.accept();

        DataInputStream dis = new DataInputStream(my_cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_cl.getOutputStream());
        ReadFile();
        dos.writeUTF(showAll());
        while (true) {
            int c = dis.readInt();
            switch (c) {
                case 1:
                    dos.writeUTF(findSV(dis));
                    break;
                case 2:
                    dos.writeUTF(addSV(dis));
                    break;
                case 3:
                    dos.writeUTF(modSV(dis, dos));
                    break;
                case 4:
                    dos.writeUTF(deleteSV(dis));
                    break;
                case 5:
                    exit(0);
            }
        }
    }
}
