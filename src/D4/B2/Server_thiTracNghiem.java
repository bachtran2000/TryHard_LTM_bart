package D4.B2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server_thiTracNghiem {
    static ArrayList<Question> listQues = new ArrayList<>();

    public static void ReadFile() throws IOException {
        File f = new File("D:\\University\\Tryhard_LMT\\src\\D4\\B2\\QnA.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] result = new String[6];
            result = line.split("\\$");

            Question ques = new Question();
            ques.setQues(result[0]);
            ques.setA1(result[1]);
            ques.setA2(result[2]);
            ques.setA3(result[3]);
            ques.setA4(result[4]);
            ques.setAns(result[5]);
            listQues.add(ques);
        }
        br.close();
        fr.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ReadFile();

        ServerSocket server = new ServerSocket(2349);
        Socket my_client = server.accept();

        DataInputStream dis = new DataInputStream(my_client.getInputStream());
        DataOutputStream dos = new DataOutputStream(my_client.getOutputStream());

        ObjectInputStream ois = new ObjectInputStream(my_client.getInputStream());

        Random rd = new Random();
        String[] ans_server = new String[3];

        for (int i = 0; i < 3; i++) {
            int n = rd.nextInt(listQues.size());
            dos.writeUTF(listQues.get(n).getQues());
            ans_server[i] = listQues.get(n).getAns();

            listQues.remove(n);
        }
        String[] ans_client = new String[3];

        ans_client = (String[]) ois.readObject();

        int count = 0;
        for (int i = 0; i < ans_client.length; i++) {
            if (ans_client[i].equalsIgnoreCase(ans_server[i]))
                count++;
        }
        if (count >= 2) {
            dos.writeUTF("Chuc mung ban qua mon! Ban tra loi dung " + count + "/3 cau");
        } else dos.writeUTF("Tach roi ban oi!");

    }
}
