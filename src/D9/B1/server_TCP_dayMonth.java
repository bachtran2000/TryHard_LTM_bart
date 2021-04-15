package D9.B1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server_TCP_dayMonth {
    public static void main(String[] args) throws IOException {
        ServerSocket sv = new ServerSocket(2349);
        Socket ml = sv.accept();

        DataInputStream dis = new DataInputStream(ml.getInputStream());
        DataOutputStream dos = new DataOutputStream(ml.getOutputStream());

        int n = dis.readInt();
        switch (n) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dos.writeUTF("Thang " + n + " co 31 ngay");
                break;
            case 2:
                dos.writeUTF("Thang 2 co 28 or 29 ngay");
            case 4:
            case 6:
            case 9:
            case 11:
                dos.writeUTF("Thang " + n + " co 30 ngay");
                break;
            default:
                dos.writeUTF("Khong ton tai thang " + n);
                break;
        }
    }
}
