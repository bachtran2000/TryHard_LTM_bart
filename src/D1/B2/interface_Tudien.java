package D1.B2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_Tudien extends Remote {
    public String EntoVn(String find)throws RemoteException;
    public String VNtoEN(String find)throws RemoteException;
    public String meanEN(String find)throws RemoteException;
}
