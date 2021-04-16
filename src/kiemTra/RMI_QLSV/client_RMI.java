package kiemTra.RMI_QLSV;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLOutput;
import java.util.Scanner;

public class client_RMI {
    public static void menu() {
        System.out.println("1. Show all");
        System.out.println("2. Danh sach hoc bong");
        System.out.println("3. Nhap diem");
        System.out.print("Chon: ");
    }
    public client_RMI() throws IOException, NotBoundException {
        Registry cl = LocateRegistry.getRegistry("localhost",8383);
        inter_RMI regis = (inter_RMI) cl.lookup("server");

        while (true){
            menu();
            int c = new Scanner(System.in).nextInt();
            switch (c){
                case 1:
                    System.out.println(regis.showAll());
                    break;
                case 2:
                    System.out.println(regis.showHB());
                    break;
                case 3:
                    System.out.println("Nhap id hoc sinh: ");
                    String find = new Scanner(System.in).nextLine();
                    if (regis.checkTontai(find)){
                        System.out.println("Nhap diem: ");
                        float n = new Scanner(System.in).nextFloat();
                        System.out.println(regis.nhapDiem(n,find));
                    }else {
                        System.out.println("Them thong tin sinh vien");
                        System.out.print("Ten: ");
                        String name = new Scanner(System.in).nextLine();

                        System.out.print("Dia chi: ");
                        String ad = new Scanner(System.in).nextLine();


                        System.out.print("Gioi tinh: ");
                        String gt = new Scanner(System.in).nextLine();


                        System.out.print("Diem tong ket: ");
                        String DTK = new Scanner(System.in).nextLine();


                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws NotBoundException, IOException {
        client_RMI cl = new client_RMI();
    }
}
