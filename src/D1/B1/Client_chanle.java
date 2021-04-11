package D1.B1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client_chanle {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 2349);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        System.out.print("Nhap vao 1 so nguyen: ");
        int n = new Scanner(System.in).nextInt();

        dos.writeInt(n);
        System.out.printf(n + " la " + dis.readUTF());
    }
}
