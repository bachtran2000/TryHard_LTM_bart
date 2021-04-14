package D7.B1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client_RMI_checkTriagle {
    public client_RMI_checkTriagle() throws RemoteException, NotBoundException {
        Registry cl = LocateRegistry.getRegistry("localhost", 2349);
        interface__RMI_checkTriagle regis = (interface__RMI_checkTriagle) cl.lookup("server");

        System.out.println("Nhap vao a: ");
        int a = new Scanner(System.in).nextInt();
        System.out.println("Nhap vao b: ");
        int b = new Scanner(System.in).nextInt();
        System.out.println("Nhap vao c: ");
        int c = new Scanner(System.in).nextInt();

        System.out.println(regis.checkTriagle(a,b,c));;
    }

    public static void main(String[] args) throws NotBoundException, RemoteException {
        client_RMI_checkTriagle cl = new client_RMI_checkTriagle();
    }
}
