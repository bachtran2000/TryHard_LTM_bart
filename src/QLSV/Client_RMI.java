package QLSV;

import kiemTra.RMI_QLSV.inter_RMI;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client_RMI {
    public static void menu() {
        System.out.println("1. Show all");
        System.out.println("2. Tim sinh vien");
        System.out.println("3. Add sinh vien");
        System.out.println("4. Update thong tin sinh vien");
        System.out.println("5. Delete sinh vien");
        System.out.println("6. Exit");
        System.out.printf("Chon: ");
    }

    public static void menu2() {
        System.out.println("1. Update All");
        System.out.println("2. Update ten");
        System.out.println("3. Update ngay sinh");
        System.out.println("4. Update MSV");
        System.out.println("5. Update Diem");
        System.out.print("Chon: ");
    }

    public Client_RMI() throws RemoteException, NotBoundException {
        Registry cl = LocateRegistry.getRegistry("localhost",8383);
        inter_RMI regis = (inter_RMI) cl.lookup("server");
    }
}
