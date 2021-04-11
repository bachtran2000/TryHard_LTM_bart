package D1.B1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 1506);//tạo socket với localhost, port như server
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

        System.out.println("Nhập một số nguyên dương bất kỳ: ");
        int n = new Scanner(System.in).nextInt();
        if (n > 0) {
            dos.writeInt(n);
            System.out.println(n + " la " + dis.readUTF());
        } else {
            System.out.println("Nhập số nguyên dương cơ mà :D???");
        }
    }
    }


