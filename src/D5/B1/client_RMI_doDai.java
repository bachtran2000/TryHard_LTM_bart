package D5.B1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client_RMI_doDai {
    public client_RMI_doDai() throws NotBoundException, RemoteException {
        Registry cl = LocateRegistry.getRegistry("127.0.0.1",2349);
        interface_doDai regí = (interface_doDai) cl.lookup("Server");

        System.out.println("Nhap vao 1 chuoi bat ki:");
        String str = new Scanner(System.in).nextLine();
        System.out.println("Do dai chuoi '"+str+"' la: "+regí.doDai(str));
    }

    public static void main(String[] args) throws NotBoundException, RemoteException {
        client_RMI_doDai cl = new client_RMI_doDai();
    }
}
