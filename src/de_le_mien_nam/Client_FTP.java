package de_le_mien_nam;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client_FTP {
    public static void WriteFile(String data, String fileName) throws IOException {
        File f = new File("");
        String preFix_path = f.getAbsolutePath() + "/src/de_le_mien_nam/Clientfile/";

        f = new File(preFix_path + fileName);

        FileWriter fw = new FileWriter(f);
        fw.write(data);
        fw.close();
        System.out.println("Ghi file " + fileName + " thanh cong!");
    }

    public static void main(String[] args) throws IOException {
        Socket cl = new Socket("localhost", 2349);

        DataInputStream dis = new DataInputStream(cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());

        //nhap ten file, trong vi du la test.txt
        System.out.println("Nhap vao ten file can tim: ");
        String fileName = new Scanner(System.in).nextLine();
        dos.writeUTF(fileName);

        //Doc va xuat len man hinh data server tra lai
        String data = dis.readUTF();

        //Xac dinh co null khong thi ghi vao file o folder Clientfile
        if (!data.equalsIgnoreCase("Khong ton tai file!")) {
            System.out.println("Noi dung trong file " + fileName + ": \n" + data);
            WriteFile(data, fileName);
        } else System.out.println(data);
    }
}
