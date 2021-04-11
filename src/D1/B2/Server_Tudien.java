package D1.B2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server_Tudien extends UnicastRemoteObject implements interface_Tudien {
    static ArrayList<Word> listWord = new ArrayList<>();

    public static void createWord(){
        Word w = new Word("one","mot","so mot");
        listWord.add(w);
        w = new Word("two","hai","chi la so hai");
        listWord.add(w);
        w = new Word("three","ba","so mot");
        listWord.add(w);
        w = new Word("four","bon","so mot");
        listWord.add(w);
        w = new Word("five","nam","so mot");
        listWord.add(w);
        w = new Word("six","sau","so mot");
        listWord.add(w);
        w = new Word("seven","bay","so mot");
        listWord.add(w);
        w = new Word("eight","tam","so mot");
        listWord.add(w);
        w = new Word("nine","chin","so mot");
        listWord.add(w);
        w = new Word("ten","muoi","so mot");
        listWord.add(w);
        w = new Word("eleven","mot","so mot");
        listWord.add(w);

    }

    protected Server_Tudien() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(2349);
        regis.rebind("Server",this);
    }


    @Override
    public String EntoVn(String find) throws RemoteException {
        String result="";
        int flag=0;
        for (int i = 0; i < listWord.size(); i++) {
            if (find.equals(listWord.get(i).getEN())){
                result+=listWord.get(i).getVN()+"\n";
            }
        }
        if (flag==0) return result="Khong tim thay!";
        return result;
    }

    @Override
    public String VNtoEN(String find) throws RemoteException{
        String result="";
        int flag=0;
        for (int i = 0; i < listWord.size(); i++) {
            if (find.equals(listWord.get(i).getVN())){
                result+=listWord.get(i).getEN()+"\n";
            }
        }
        if (flag==0) return result="Khong tim thay!";
        return result;
    }

    @Override
    public String meanEN(String find) throws RemoteException{
        String result="";
        int flag=0;
        for (int i = 0; i < listWord.size(); i++) {
            if (find.equals(listWord.get(i).getEN())){
                result+=listWord.get(i).getMeaning()+"\t";
                flag++;
            }
        }
        if (flag==0) return result="Khong tim thay!";
        return result;
    }

    public static void main(String[] args) throws RemoteException {
        createWord();
        Server_Tudien server_tudien = new Server_Tudien();
    }
}
