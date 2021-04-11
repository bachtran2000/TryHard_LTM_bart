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
        ObjectInputStream ois = new ObjectInputStream(my_client.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(my_client.getOutputStream());
        while (true) {
            int n = dis.readInt();
            switch (n) {
                case 1:

                    showData(oos);
                    break;
                case 2:
                    n = dis.readInt();
                    for (int i = 0; i < n; i++) {
                        addSV(dis);
                    }
                    break;
                case 3:
                    findSV(dis, dos, oos);
                    break;
                case 4:
                    findSV_Theonhom(dis, oos);
                    break;
            }
        }
    }


    public static void ReadFile() {
        try {
            File f = new File("C:\\Users\\84965\\Desktop\\sinhvien.txt");
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
            System.out.println("loi roi :))");

        }
    }

    public static void showData(ObjectOutputStream oos) throws IOException {
        oos.writeObject(listSV);
    }

    public static void addSV(DataInputStream dis) throws IOException, ClassNotFoundException {
        sinhvien sv = new sinhvien();
        sv.setTen(dis.readUTF());
        sv.setNgaysinh(dis.readUTF());
        sv.setMaSV(dis.readUTF());
        sv.setQuequan(dis.readUTF());
        listSV.add(sv);
    }

    public static void findSV(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
        String find = dis.readUTF();
        for (int i = 0; i < listSV.size(); i++) {
            if (find.equalsIgnoreCase(listSV.get(i).getTen())) {
                dos.writeInt(1);
                oos.writeObject(listSV.get(i));
            } else {
                dos.writeInt(0);
                dos.writeUTF("Not found!");
            }
        }
    }

    public static void findSV_Theonhom(DataInputStream dis, ObjectOutputStream oos) throws IOException {
        ArrayList<sinhvien> tempList = new ArrayList<>();

        int n = dis.readInt();
        System.out.println(n);
        String find = dis.readUTF();
        System.out.println(find);
        for (int i = 0; i < listSV.size(); i++) {
            if (n == 1) {
                if (find.equalsIgnoreCase(listSV.get(i).getQuequan()))
                    tempList.add(listSV.get(i));
            } else if (n == 2) {
                if (find.equalsIgnoreCase(listSV.get(i).getNgaysinh()))
                    tempList.add(listSV.get(i));
            }
        }
        oos.writeObject(tempList);
    }
}
