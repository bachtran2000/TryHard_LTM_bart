package D6.B2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class server_QLBH_TCP {
    static ArrayList<matHang> listMH = new ArrayList<>();

    public static void ReadFile() {
        try {
            File f = new File("mathang.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] result;
                result = line.split("\\$");
                matHang m = new matHang();
                m.setId(result[0]);
                m.setTen(result[1]);
                m.setGb(Float.parseFloat(result[2]));
                m.setSltk(Integer.parseInt(result[3]));
                listMH.add(m);
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
            File f = new File("test.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < listMH.size(); i++) {
                if (i < listMH.size())
                    fw.write(listMH.get(i).toString() + "\n");
                else
                    fw.write(listMH.get(i).toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findID(DataInputStream dis) throws IOException {
        String id_re = dis.readUTF();
        String result = "Not found!";
        for (int i = 0; i < listMH.size(); i++) {
            if (id_re.equals(listMH.get(i).getId()))
                result = listMH.get(i).toShow();
        }
        return result;
    }

    public static boolean checkValid(String id_re) throws IOException {

        for (int i = 0; i < listMH.size(); i++) {
            if (id_re.equals(listMH.get(i).getId()))
                return true;
        }
        return false;
    }

    public static String tinhHD(DataInputStream dis) throws IOException {
        String id_re = dis.readUTF();
        System.out.println(id_re);
        if (checkValid(id_re)) {
            int sl_re = dis.readInt();
            System.out.println(sl_re);
            for (int i = 0; i < listMH.size(); i++) {
                if (id_re.equals(listMH.get(i).getId()))
                    if (sl_re < listMH.get(i).getSltk()) {
                        System.out.println(String.valueOf(sl_re * listMH.get(i).getGb()));
                        return listMH.get(i).getTen() +" * "+sl_re+" = "+String.valueOf(sl_re * listMH.get(i).getGb());
                    } else return "Khong du so luong de dat";
            }
        }
        return "Not Found";
    }

    public static String invoid(String id_re, int sl) throws IOException {
        if (checkValid(id_re)){
            for (int i = 0; i < listMH.size(); i++) {
                if (id_re.equals(listMH.get(i).getId()))
                    if (sl<listMH.get(i).getSltk())
                        return listMH.get(i).getTen() +" * "+sl+" = "+String.valueOf(sl * listMH.get(i).getGb());
                    else return "Khong du so luong!";
            }
        }
        else return "Not found!";
        return "Not found";
    }

    public static void main(String[] args) throws IOException {
        ReadFile();
        ServerSocket sr = new ServerSocket(2349);
        Socket mcl = sr.accept();

        DataInputStream dis = new DataInputStream(mcl.getInputStream());
        DataOutputStream dos = new DataOutputStream(mcl.getOutputStream());

        while (true) {
            int n = dis.readInt();
            switch (n) {
                case 1:
                    dos.writeUTF(findID(dis));
                    break;
                case 2:
                    String id_re =dis.readUTF();
                    int sl = dis.readInt();

                    dos.writeUTF(invoid(id_re,sl));
                    break;
            }
        }
    }
}
