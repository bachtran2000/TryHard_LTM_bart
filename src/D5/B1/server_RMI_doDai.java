package D5.B1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class server_RMI_doDai extends UnicastRemoteObject implements interface_doDai {
    @Override
    public int doDai(String s) {
        return s.length();
    }

    public server_RMI_doDai() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(2349);
        regis.rebind("Server",this);
    }

    public static void main(String[] args) throws RemoteException {
        server_RMI_doDai sv = new server_RMI_doDai();
    }
}
