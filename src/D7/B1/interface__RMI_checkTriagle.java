package D7.B1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface__RMI_checkTriagle extends Remote {
    public String checkTriagle(int a, int b, int c) throws RemoteException;
}
