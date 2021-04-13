package D2.B2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Serializable {
    static ArrayList<sinhvien> listSV = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ReadFile();
        ServerSocket server = new ServerSocket(1506);
        Socket my_client = server.accept();

        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());
        DataInputStream dis = new DataInputStream(my_client.getInputStream());

        while (true) {
            int n = dis.readInt();
            switch (n) {
                case 1:
                    dos.writeUTF(showData());
                    break;
                case 2:
                    n = dis.readInt();
                    for (int i = 0; i < n; i++) {
                        addSV(dis);
                    }
                    WriteFile();
                    break;
                case 3:
                    findSV(dis, dos);
                    break;
                case 4:
                    findSV_Theonhom(dis, dos);
                    break;
            }
        }
    }


    public static void ReadFile() {
        try {
            File f = new File("sinhvien.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                sinhvien sv = new sinhvien();
                String result[] = new String[4];
                result = line.split("\\$");
                sv.setTen(result[0]);
                sv.setNgaysinh(result[1]);
                sv.setMaSV(result[2]);
                sv.setQuequan(result[3]);
                listSV.add(sv);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("loi roi :))" + e);

        }
    }

    public static void WriteFile() throws IOException {
        File f = new File("sinhvien.txt");
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < listSV.size(); i++) {
            if (i == listSV.size() - 1) {
                fw.write(listSV.get(i).toFile());
            } else {
                fw.write(listSV.get(i).toFile());
                fw.write("\n");
            }
        }
        fw.close();
    }

    public static String showData() {
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            result += listSV.get(i).toString() + "\n";
        }
        return result;
    }

    public static void addSV(DataInputStream dis) throws IOException, ClassNotFoundException {
        sinhvien sv = new sinhvien();
        sv.setTen(dis.readUTF());
        sv.setNgaysinh(dis.readUTF());
        sv.setMaSV(dis.readUTF());
        sv.setQuequan(dis.readUTF());
        listSV.add(sv);
    }

    public static void findSV(DataInputStream dis, DataOutputStream dos) throws IOException {
        String find = dis.readUTF();
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            if (find.equalsIgnoreCase(listSV.get(i).getTen())) {
                result += listSV.get(i).toString() + "\n";
            }
        }
        dos.writeUTF(result);
    }

    public static void findSV_Theonhom(DataInputStream dis, DataOutputStream dos) throws IOException {

        int n = dis.readInt();
        System.out.println(n);
        String find = dis.readUTF();
        System.out.println(find);
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            if (n == 1) {
                if (find.equalsIgnoreCase(listSV.get(i).getQuequan()))
                    result += listSV.get(i).toString() + "\n";
            } else if (n == 2) {
                if (find.equals(listSV.get(i).getNamsinh())) {
                    result += listSV.get(i).toString() + "\n";
                    System.out.println(listSV.get(i).getNamsinh());
                }
            }
        }
        dos.writeUTF(result);
    }
}
