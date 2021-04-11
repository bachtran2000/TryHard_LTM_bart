package D1.B2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client_Tudien {

    public void menu(){
        System.out.println("1. En to Vn");
        System.out.println("2. VN to En");
        System.out.println("3. Show meaning");
        System.out.print("Choose: ");
    }

    public Client_Tudien() throws RemoteException, NotBoundException {
        Registry client = LocateRegistry.getRegistry("localhost", 2349);
        interface_Tudien regis = (interface_Tudien) client.lookup("Server");
        while (true){
            menu();
            int chon = new Scanner(System.in).nextInt();

            switch (chon){
                case 1:
                    System.out.print("Nhap tu can tra nghia: ");
                    String find = new Scanner(System.in).nextLine();
                    System.out.println(find+" la "+regis.EntoVn(find));
                    break;
                case 2:
                    System.out.print("Nhap tu can tra nghia: ");
                    find = new Scanner(System.in).nextLine();
                    System.out.println(find+" la "+regis.VNtoEN(find));
                    break;
                case 3:
                    System.out.print("Nhap tu can tra nghia: ");
                    find = new Scanner(System.in).nextLine();
                    System.out.println(find+" la "+regis.meanEN(find));
                    break;
                case 4:
                    System.exit(0);
            }
        }

    }

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Client_Tudien client_tudien = new Client_Tudien();
    }
}
