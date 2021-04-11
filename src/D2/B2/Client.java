package D2.B2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Client implements Serializable {
    static ArrayList<sinhvien> listSV = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket("localhost", 1506);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        while (true) {
            menu();
            int n = new Scanner(System.in).nextInt();
            switch (n) {
                case 1: {
                    dos.writeInt(n);
                    listSV = (ArrayList<sinhvien>) ois.readObject();
                    for (int i = 0; i < listSV.size(); i++) {
                        System.out.println(listSV.get(i).toString());
                    }
                    break;
                }
                case 2:
                    dos.writeInt(n);

                    System.out.println("Nhap so luong sinh vien can them: ");
                    n = new Scanner(System.in).nextInt();
                    dos.writeInt(n);
                    for (int i = 0; i < n; i++) {
                        addSV(dos);
                    }
                    break;
                case 3:
                    dos.writeInt(n);
                    findInfoSV(dos,dis, ois);
                    break;
                case 4:
                    dos.writeInt(n);
                    menu2();
                    n = new Scanner(System.in).nextInt();
                    switch (n){
                        case 1:
                            dos.writeInt(n);
                            System.out.println("Nhap que can tim theo nhom: ");
                            dos.writeUTF(new Scanner(System.in).nextLine());
                            listSV = (ArrayList<sinhvien>) ois.readObject();
                            for (int i = 0; i < listSV.size(); i++) {
                                System.out.println(listSV.get(i).toString());
                            }
                            break;
                        case 2:
                            dos.writeInt(n);
                            System.out.println("Nhap nam sinh can tim theo nhom: ");
                            dos.writeUTF(new Scanner(System.in).nextLine());
                            listSV = (ArrayList<sinhvien>) ois.readObject();
                            for (int i = 0; i < listSV.size(); i++) {
                                System.out.println(listSV.get(i).toString());
                            }
                            break;
                    }
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("===Quan ly sinh vien===");
        System.out.println("1.  Xem danh sach sinh vien");
        System.out.println("2. Them sinh vien");
        System.out.println("3. Xem thong tin sinh vien");
        System.out.println("4. Tim nhom sinh vien");
        System.out.println("Chon 1 cai di :D");
    }

    public static void menu2() {
        System.out.println("1. Nhom theo que quan");
        System.out.println("2. Nhom theo nam sinh");
        System.out.print("Chon: ");
    }

    public static void addSV(DataOutputStream dos) throws IOException {
        System.out.println("Nhap ten: ");
        dos.writeUTF(String.valueOf(new Scanner(System.in).nextLine()));
        System.out.println("Nhap ngay sinh: ");
        dos.writeUTF(String.valueOf(new Scanner(System.in).nextLine()));
        System.out.println("Nhap ma sinh vien: ");
        dos.writeUTF(String.valueOf(new Scanner(System.in).nextLine()));
        System.out.println("Nhap que quan: ");
        dos.writeUTF(String.valueOf(new Scanner(System.in).nextLine()));
    }

    public static void findInfoSV(DataOutputStream dos, DataInputStream dis, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("Nhap ten sinh vien can tim: ");
        dos.writeUTF(String.valueOf(new Scanner(System.in).nextLine()));
        int n = dis.readInt();
        if (n == 1) {
            sinhvien sv = new sinhvien();
            sv = (sinhvien) ois.readObject();
            System.out.println(sv.toString());
        }
        else System.out.println(dis.readUTF());
    }

}
