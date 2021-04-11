package D3.B1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_GT {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();
        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());

        int n = dis.readInt();
        int gt =1;
        for (int i = 1; i < n; i++) {
            gt *=i;
        }
        dos.writeInt(gt);
    }
}
