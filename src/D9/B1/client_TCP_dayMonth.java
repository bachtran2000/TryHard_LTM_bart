package D9.B1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client_TCP_dayMonth {
    public static void main(String[] args) throws IOException {
        Socket ml = new Socket("localhost",2349);

        DataInputStream dis = new DataInputStream(ml.getInputStream());
        DataOutputStream dos=  new DataOutputStream(ml.getOutputStream());

        System.out.println("Nhap vao 1 thang bat ki: ");
        dos.writeInt(new Scanner(System.in).nextInt());
        System.out.println(dis.readUTF());
    }
}
