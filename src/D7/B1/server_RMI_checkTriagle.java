package D7.B1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Locale;

public class server_RMI_checkTriagle extends UnicastRemoteObject implements interface__RMI_checkTriagle {

    protected server_RMI_checkTriagle() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(2349);
        regis.rebind("server",this);
    }

    public static void main(String[] args) throws RemoteException {
        server_RMI_checkTriagle sv = new server_RMI_checkTriagle();
    }

    @Override
    public String checkTriagle(int a, int b, int c) throws RemoteException {
        if ((a+b)>c || (a+c)>b || (b+c)>a)
            return "La tam giac";
        else return "Khong la tam giac";
    }
}
