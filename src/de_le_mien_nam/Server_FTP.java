package de_le_mien_nam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_FTP {
    public static String ReadFile(String fileName) throws IOException {
        File f = new File("");
        String preFix_path = f.getAbsolutePath()+"/src/de_le_mien_nam/Serverfile/";

        f = new File(preFix_path+fileName);
        if (f.exists()) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
            fr.close();
            br.close();
            return result;
        } else return "Khong ton tai file!";
    }

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(2349);
        Socket my_cl = server.accept();
        DataInputStream dis = new DataInputStream(my_cl.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_cl.getOutputStream());

        String fileName = dis.readUTF();

        dos.writeUTF(ReadFile(fileName));

    }
}

