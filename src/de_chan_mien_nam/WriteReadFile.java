package de_chan_mien_nam;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class WriteReadFile {
    public static void ReadFile(String fileName) throws IOException {
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line + "\n";
        }
        fr.close();
        br.close();
        System.out.println(result);
    }

    public static void WriteFile(String fileName, int n) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap phan tu thu " + (i + 1) + ": ");
            fw.write(new Scanner(System.in).nextInt() + "\t");
        }
        fw.close();
        System.out.println("Nhap thanh cong!\n");
    }

    public static void ReadFile_binary(String fileName,int n) throws IOException {
        //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
        FileInputStream fis = new FileInputStream(fileName);
        DataInputStream dis = new DataInputStream(fis);
        //Bước 2: Đọc dữ liệu
        String result="";
        for (int i = 0; i < n; i++) {
            result+=dis.readUTF();
        }
        //Bước 3: Đóng luồng
        fis.close();
        dis.close();
        //Hiển thị nội dung đọc từ file
        System.out.println(result+"\n");
    }

    public static void WriteFile_binary(String fileName, int n) throws IOException {
        //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos);
        //Bước 2: Ghi dữ liệu
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap phan tu thu " + (i + 1) + ": ");
            dos.writeUTF(new Scanner(System.in).nextLine() + "\t");
        }
        //Bước 3: Đóng luồng
        fos.close();
        dos.close();
        System.out.println("Nhap thanh cong!\n");
    }

    public static void menu() {
        System.out.println("1. Nhap va doc dang thuong");
        System.out.println("2. Nhap va doc dang nhi phan");
        System.out.println("3. Exit");
        System.out.print("Chon: ");
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            menu();
            int c = new Scanner(System.in).nextInt();
            System.out.println("Nhap so luong phan tu: ");
            int n = new Scanner(System.in).nextInt();
            switch (c) {
                case 1:
                    System.out.println("Nhap ten file: ");
                    String fileName = new Scanner(System.in).nextLine();
                    fileName += ".txt";
                    WriteFile(fileName, n);
                    ReadFile(fileName);
                    break;
                case 2:
                    System.out.println("Nhap ten file: ");
                    fileName = new Scanner(System.in).nextLine();
                    fileName += "_binary.txt";
                    WriteFile_binary(fileName, n);
                    ReadFile_binary(fileName,n);
                    break;
                case 3:
                    exit(0);
            }
        }
    }
}
