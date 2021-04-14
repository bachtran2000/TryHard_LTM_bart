package D6.B2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client_QLBH_TCP {
    public static void menu(){
        System.out.println("1. Tim kiem hang theo id");
        System.out.println("2. Lap hoa don thanh toan");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        Socket mcl = new Socket("localhost",2349);

        DataInputStream dis = new DataInputStream(mcl.getInputStream());
        DataOutputStream dos = new DataOutputStream(mcl.getOutputStream());

        while (true){
            menu();
            int n = new Scanner(System.in).nextInt();
            dos.writeInt(n);
            switch (n){
                case 1:
                    System.out.println("Nhap vao thong tin id: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println(dis.readUTF());
                    break;
                case 2:
                    System.out.println("Nhap id: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println("Nhap so luong: ");
                    dos.writeInt(new Scanner(System.in).nextInt());

                    System.out.println(dis.readUTF());
                    break;
            }
        }
    }
}
