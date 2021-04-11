package D3.B1;

import D2.B2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client_GT {
    public static void main(String[] args) throws IOException {
        Socket client  = new Socket("localhost",2349);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        System.out.println("Nhap vao so can tinh: ");
        dos.writeInt(new Scanner(System.in).nextInt());

        System.out.println(dis.readInt());
    }
}
