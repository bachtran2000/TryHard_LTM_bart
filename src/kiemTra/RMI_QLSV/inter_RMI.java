package kiemTra.RMI_QLSV;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface inter_RMI extends Remote {
    public String showAll() throws RemoteException;
    public String showHB() throws  RemoteException;
    public String Add(String find, String name, String ad, String gt, String dtk) throws IOException;
    public boolean checkTontai(String find) throws RemoteException;
    public String nhapDiem(Float n, String id) throws  RemoteException;
}
