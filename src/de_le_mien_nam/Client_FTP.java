package de_le_mien_nam;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client_FTP {
    public static void main(String[] args) throws IOException {
        Socket cl = new Socket("localhost", 2349);

        DataInputStream dis = new DataInputStream(cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());

        ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
        ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());

        System.out.println("Nhap vao ten file can tim: ");
        dos.writeUTF(new Scanner(System.in).nextLine());


    }
}
