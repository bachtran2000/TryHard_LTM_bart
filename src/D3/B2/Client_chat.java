package D3.B2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client_chat {
    public static void main(String[] args) throws IOException {

        Socket mcl = new Socket("localhost",2349);

        DataInputStream dis = new DataInputStream(mcl.getInputStream());
        DataOutputStream dos = new DataOutputStream(mcl.getOutputStream());

        //Nhap thong tin dang nhap
        System.out.println("Nhap username: ");
        dos.writeUTF(new Scanner(System.in).nextLine());

        System.out.println("Nhap password: ");
        dos.writeUTF(new Scanner(System.in).nextLine());
        //Doc thong bao dang nhap
        System.out.println(dis.readUTF());

        String exit ="";

        System.out.println("Nhap tin: ");
        dos.writeUTF(new Scanner(System.in).nextLine());
        while(true){
            System.out.println(dis.readUTF());
            exit = new Scanner(System.in).nextLine();
            if (exit.equals("y")||exit.equals("Y"))
                System.exit(0);
            dos.writeUTF(exit);
        }
    }
}
