package D5.B1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_doDai extends Remote {
    public int doDai(String s) throws RemoteException;
}
