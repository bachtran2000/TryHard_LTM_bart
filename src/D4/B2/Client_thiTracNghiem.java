package D4.B2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client_thiTracNghiem {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 2349);

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

        String[] ans = new String[3];

        for (int i = 0; i < 3; i++) {
            System.out.println(dis.readUTF());
            System.out.print("Nhap vao dap an: ");
            ans[i] = new Scanner(System.in).nextLine();
        }
        oos.writeObject(ans);

        System.out.println(dis.readUTF());


    }
}
