package D7.B2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client_TCP_tinhLuong {
    public static void menu(){
        System.out.println("1. Them cau thu moi");
        System.out.println("2. Tinh luong cau thu");
        System.out.print("Chon: ");
    }
    public static void main(String[] args) throws IOException {
        Socket ml = new Socket("localhost",2349);

        DataInputStream dis = new DataInputStream(ml.getInputStream());
        DataOutputStream dos = new DataOutputStream(ml.getOutputStream());

        while (true){
            menu();
            int c = new Scanner(System.in).nextInt();
            dos.writeInt(c);
            switch (c){
                case 1:
                    System.out.println("Nhap ten cau thu: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap nam sinh cau thu: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap vi tri da cau thu: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap luong mac dinh cau thu: ");
                    dos.writeFloat(new Scanner(System.in).nextFloat());

                    System.out.println(dis.readUTF());
                    break;
                case 2:
                    System.out.println("Nhap id cau thu can tinh luong:");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println("Nhap so tran thi dau");
                    dos.writeInt(new Scanner(System.in).nextInt());

                    System.out.println(dis.readUTF());
                    break;

            }
        }
    }
}
