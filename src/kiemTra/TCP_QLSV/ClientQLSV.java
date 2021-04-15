package kiemTra.TCP_QLSV;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientQLSV {

    public static void menu() {
        System.out.println("1. Show all");
        System.out.println("2. Danh sach hoc bong");
        System.out.println("3. Nhap diem");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        Socket cl = new Socket("localhost", 2349);

        DataInputStream dis = new DataInputStream(cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());

        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();

            dos.writeInt(c);
            switch (c) {
                case 1:
                    System.out.println("Danh sach hoc sinh");
                    System.out.println(dis.readUTF());
                    break;
                case 2:
                    System.out.println("Danh sach dat hoc bong");
                    System.out.println(dis.readUTF());
                    break;
                case 3:
                    System.out.println("Nhap ma sinh vien:");
                    String str_send = new Scanner(System.in).nextLine();
                    dos.writeUTF(str_send);

                    String str_re = dis.readUTF();

                    if (str_re.equalsIgnoreCase("Nhap diem")) {
                        System.out.println(str_re);
                        str_send = new Scanner(System.in).nextLine();
                        dos.writeUTF(str_send);
                    }
                    else {
                        System.out.println("Them thong tin sinh vien");
                        System.out.print("Ten: ");
                        str_send = new Scanner(System.in).nextLine();
                        dos.writeUTF(str_send);
                        System.out.print("Dia chi: ");
                        str_send = new Scanner(System.in).nextLine();
                        dos.writeUTF(str_send);
                        System.out.print("Gioi tinh: ");
                        str_send = new Scanner(System.in).nextLine();
                        dos.writeUTF(str_send);
                        System.out.print("Diem tong ket: ");
                        str_send = new Scanner(System.in).nextLine();
                        dos.writeUTF(str_send);
                    }

                    System.out.println(dis.readUTF());
                    break;
            }
        }

    }
}
