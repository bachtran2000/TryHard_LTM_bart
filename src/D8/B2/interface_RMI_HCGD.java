package D8.B2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_RMI_HCGD extends Remote {
    public String ranDom() throws RemoteException;
    public String checkGia(float gia_re) throws RemoteException;
}
