package QLSV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client_TCP {

    public static void menu() {
        System.out.println("1. Tim sinh vien");
        System.out.println("2. Add sinh vien");
        System.out.println("3. Update thong tin");
        System.out.println("4. Xoa sinh vien");
        System.out.println("5. Exit");
        System.out.print("Chon: ");
    }

    public static void menu2() {
        System.out.println("1. Update All");
        System.out.println("2. Update ten");
        System.out.println("3. Update ngay sinh");
        System.out.println("4. Update MSV");
        System.out.println("5. Update diem");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        Socket cl = new Socket("localhost", 2349);

        DataInputStream dis = new DataInputStream(cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
        System.out.println(dis.readUTF());
        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();
            dos.writeInt(c);
            switch (c) {
                case 1:
                    System.out.println("Nhap MSV can tim: ");
                    String msv = new Scanner(System.in).nextLine();
                    dos.writeUTF(msv);
                    System.out.println(dis.readUTF());
                    break;
                case 2:
                    System.out.println("Nhap thong tin sinh vien");
                    System.out.println("Ho ten: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println("Ngay sinh: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println("MSV: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println("DTB: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println(dis.readUTF());
                    break;
                case 3:
                    System.out.println("Nhap MSV can update");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    if (dis.readInt() == 1) {
                        menu2();
                        System.out.println("Chon: ");
                        int chon = new Scanner(System.in).nextInt();
                        dos.writeInt(chon);
                        switch (chon) {
                            case 1:
                                System.out.println("Nhap ten: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());

                                System.out.println("Nhap ngay sinh: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());

                                System.out.println("Nhap MSV: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());

                                System.out.println("Nhap DTB: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());
                                break;
                            case 2:
                                System.out.println("Nhap ten: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());
                                break;
                            case 3:
                                System.out.println("Nhap ngay sinh: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());
                                break;
                            case 4:
                                System.out.println("Nhap MSV: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());
                                break;
                            case 5:
                                System.out.println("Nhap DTB: ");
                                dos.writeUTF(new Scanner(System.in).nextLine());
                                break;
                        }
                    }//else {
//                        System.out.println(dis.readUTF());
//                    }
                    System.out.println(dis.readUTF());
                    break;
                case 4:
                    System.out.println("Nhap MSV can xoa: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    break;
                case 5:
                    exit(0);
            }
        }
    }
}
