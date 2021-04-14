package D8.B2;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class server_RMI_HCGD extends UnicastRemoteObject implements interface_RMI_HCGD {
    static ArrayList<matHang_RMI> listMH = new ArrayList<>();
    String id = "";

    public static void ReadFile() {
        try {
            File f = new File("matHangHCGD.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] result;
                result = line.split("\\$");
                matHang_RMI m = new matHang_RMI();
                m.setId(result[0]);
                m.setName(result[1]);
                m.setGia(Float.parseFloat(result[2]));
                listMH.add(m);
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public server_RMI_HCGD() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(2349);
        regis.rebind("server", this);

    }

    @Override
    public String ranDom() throws RemoteException {
        Random rd = new Random();
        int c = rd.nextInt(listMH.size());
        id = c + "";
        String result = listMH.get(c).getName() + " = ? ";
        listMH.remove(c);
        return result;
    }

    @Override
    public String checkGia(float gia_re) throws RemoteException {
        String result = "";
        for (int i = 0; i < listMH.size(); i++) {

            if (gia_re == listMH.get(Integer.parseInt(id)).getGia())
                result = "Gia du doan chinh xac";
            else if (gia_re < listMH.get(Integer.parseInt(id)).getGia())
                result = "Gia du doan thap";
            else if (gia_re > listMH.get(Integer.parseInt(id)).getGia())
                result = "Gia du doan cao";

        }
        return result;
    }

    public static void main(String[] args) throws RemoteException {
        ReadFile();
        server_RMI_HCGD sv = new server_RMI_HCGD();
    }
}
