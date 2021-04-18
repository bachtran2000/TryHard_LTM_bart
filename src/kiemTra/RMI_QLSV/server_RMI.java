package kiemTra.RMI_QLSV;

import kiemTra.TCP_QLSV.hocSinh;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class server_RMI extends UnicastRemoteObject implements inter_RMI {
    static ArrayList<hocSinh> listHS = new ArrayList<>();

    protected server_RMI() throws IOException {
        Registry regis = LocateRegistry.createRegistry(8383);
        regis.rebind("server", this);
        ReadFile();
    }

    public static void ReadFile() throws IOException {
        File f = new File("hocsinh.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] result = new String[5];
            result = line.split("\\$");
            hocSinh hs = new hocSinh();
            hs.setMHS(result[0]);
            hs.setTen(result[1]);
            hs.setDiaChi(result[2]);
            hs.setGt(result[3]);
            hs.setDTK(Float.parseFloat(result[4]));
            listHS.add(hs);
        }
        fr.close();
        br.close();
    }

    public static void WriteFile() throws IOException {
        File f = new File("hocsinh.txt");
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < listHS.size(); i++) {
            fw.write(listHS.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }
    
    @Override
    public String showAll() throws RemoteException {
        String result = "";
        for (int i = 0; i < listHS.size(); i++) {
            result += listHS.get(i).toShow() + "\n";
        }
        return result;
    }

    @Override
    public String showHB() throws RemoteException {
        String result = "";
        for (int i = 0; i < listHS.size(); i++) {
            if ((listHS.get(i).getDTK()) >= 8)
                result += listHS.get(i).toShow() + "\n";
        }
        return result;
    }

    @Override
    public boolean checkTontai(String find) throws RemoteException {
        for (int i = 0; i < listHS.size(); i++) {
            if (find.equals(listHS.get(i).getMHS())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String nhapDiem(Float n, String id) throws RemoteException {
        String str_send = "";
        for (int i = 0; i < listHS.size(); i++) {
            if (id.equals(listHS.get(i).getMHS())) {
                System.out.print("Nhap diem: ");
                listHS.get(i).setDTK(n);
            }
        }
        str_send = "Nhap diem thanh cong!";
        return str_send;
    }

    @Override
    public String Add(String find, String name, String ad, String gt, String dtk) throws IOException {
        String str_send = "";
        hocSinh hs = new hocSinh();

        hs.setMHS(find);

        hs.setTen(name);


        hs.setDiaChi(ad);


        hs.setGt(gt);


        hs.setDTK(Float.parseFloat(dtk));

        listHS.add(hs);
        str_send = "Nhap thong tin thanh cong!";

        WriteFile();
        return str_send;
    }

    public static void main(String[] args) throws IOException {
        server_RMI sv = new server_RMI();
    }
}
