package D6.B1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client_doSo_UDP {
    public static void main(String[] args) throws IOException {
        Socket mcl = new Socket("localhost",2349);

        DataInputStream dis = new DataInputStream(mcl.getInputStream());
        DataOutputStream dos = new DataOutputStream(mcl.getOutputStream());

        System.out.print("Nhap vao 0-9: ");
        int n = new Scanner(System.in).nextInt();
        dos.writeInt(n);

        System.out.println(dis.readUTF());
    }
}
