package kiemTra.TCP_QLSV;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class serverQLSV {
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

    public static void WriteFile() throws IOException {
        File f = new File("hocsinh.txt");
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i <listHS.size(); i++) {
            fw.write(listHS.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_cl = server.accept();

        DataInputStream dis = new DataInputStream(my_cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_cl.getOutputStream());
        ReadFile();
        while (true) {
            int c = dis.readInt();
            switch (c) {
                case 1:

                    String str_send = "";
                    for (int i = 0; i < listHS.size(); i++) {
                        str_send += listHS.get(i).toShow() + "\n";
                    }
                    dos.writeUTF(str_send);

                    break;
                case 2:
                    str_send = checkHB();
                    dos.writeUTF(str_send);
                    break;
                case 3:
                    String str_re = dis.readUTF();
                    String id = str_re;
                    str_send = checkTontai(str_re);

                    if (str_send.equalsIgnoreCase("Nhap diem")) {
                        dos.writeUTF(str_send);
                        str_re = dis.readUTF();
                        for (int i = 0; i < listHS.size(); i++) {
                            if (id.equals(listHS.get(i).getMHS()))
                                listHS.get(i).setDTK(Float.parseFloat(str_re));
                        }
                        str_send = "Nhap diem thanh cong!";
                    } else {
                        dos.writeUTF(str_send);
                        hocSinh hs = new hocSinh();
                        hs.setMHS(id);
                        hs.setTen(dis.readUTF());
                        hs.setDiaChi(dis.readUTF());
                        hs.setGt(dis.readUTF());
                        hs.setDTK(Float.parseFloat(dis.readUTF()));
                        listHS.add(hs);
                        str_send = "Nhap thong tin thanh cong!";
                    }
                    WriteFile();
                    dos.writeUTF(str_send);
                    break;
            }
        }
    }
}
