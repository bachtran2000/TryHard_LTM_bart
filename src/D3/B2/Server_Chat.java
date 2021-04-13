package D3.B2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server_Chat {
    static ArrayList<User> listUS = new ArrayList<>();

    public static boolean Login(String user, String pass){
        for (int i = 0; i < listUS.size(); i++) {
            if (user.equals(listUS.get(i).getUser()) && pass.equals(listUS.get(i).getPass()))
                return true;
        }
        return false;
    }

    public static void ReadFile() throws IOException {
        File f = new File("dataUser.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine())!=null){
            String[] result = new String[2];
            result = line.split("\\$");

            User us = new User();
            us.setUser(result[0]);
            us.setPass(result[1]);
            listUS.add(us);
        }
        br.close();
        fr.close();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ser = new ServerSocket(2349);
        Socket mcl = ser.accept();

        DataInputStream dis = new DataInputStream(mcl.getInputStream());
        DataOutputStream dos = new DataOutputStream(mcl.getOutputStream());
        ReadFile();

        //Nhap data user
        String user = dis.readUTF();
        String pass = dis.readUTF();
        if (Login(user,pass))
            dos.writeUTF("Dang nhap thanh cong!");
        else dos.writeUTF("Sai thong tin dang nhap!");

        String exit ="";

        while(true){
            System.out.println(dis.readUTF());
            exit = new Scanner(System.in).nextLine();
            if (exit.equals("y")||exit.equals("Y"))
                System.exit(0);
            dos.writeUTF(exit);
        }
    }
}
