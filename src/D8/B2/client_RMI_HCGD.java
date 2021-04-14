package D8.B2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client_RMI_HCGD {
    public client_RMI_HCGD() throws RemoteException, NotBoundException {
        Registry cl = LocateRegistry.getRegistry("localhost", 2349);
        interface_RMI_HCGD regis = (interface_RMI_HCGD) cl.lookup("server");
        System.out.println("Ready?");
        new Scanner(System.in).nextLine();
        int c = 0, flag = 0;
        while (c < 7) {
            System.out.println(regis.ranDom());
            System.out.println("Nhap vao gia san pham: ");
            float gia_send = new Scanner(System.in).nextFloat();
            String result = regis.checkGia(gia_send);
            if (result.equals("Gia du doan chinh xac"))
                flag++;
            System.out.println(result);
            c++;
        }
        if (flag == 7)
            System.out.println("Chuc mung ban da chien thang!");
        else System.out.println("Ban da chon sai " + (7 - flag) + "/7");
    }

    public static void main(String[] args) throws NotBoundException, RemoteException {
        client_RMI_HCGD cl = new client_RMI_HCGD();
    }
}
