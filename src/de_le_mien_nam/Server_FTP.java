package de_le_mien_nam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_FTP {
    public static void ReadFile(String fileName) throws IOException {
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_cl = server.accept();
        DataInputStream dis = new DataInputStream(my_cl.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(my_cl.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(my_cl.getInputStream());


    }
}
