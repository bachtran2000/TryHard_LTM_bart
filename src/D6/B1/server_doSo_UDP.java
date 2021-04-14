package D6.B1;

import D2.B2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server_doSo_UDP {
    public static void main(String[] args) throws IOException {
        ServerSocket ser = new ServerSocket(2349);
        Socket mcl = ser.accept();

        DataInputStream dis = new DataInputStream(mcl.getInputStream());
        DataOutputStream dos = new DataOutputStream(mcl.getOutputStream());

        int str_re = dis.readInt();
        String str_send = "";
        switch (str_re) {
            case 1:
                str_send = "Mot";
                break;
            case 2:
                str_send = "Hai";
                break;
            case 3:
                str_send = "Ba";
                break;
            case 4:
                str_send = "Bon";
                break;
            case 5:
                str_send = "Nam";
                break;
            case 6:
                str_send = "Sau";
                break;
            case 7:
                str_send = "Bay";
                break;
            case 8:
                str_send = "Tam";
                break;
            case 9:
                str_send = "Chin";
                break;
            case 10:
                str_send = "Muoi";
                break;
            case 0:
                str_send = "Khong";
                break;
            default:
                str_send = "Nhap sai roi";
                break;
        }
        dos.writeUTF(str_send);
    }
}
