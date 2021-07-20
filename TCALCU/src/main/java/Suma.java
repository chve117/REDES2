import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Suma extends Remote {
    int suma(int a,int b) throws RemoteException;
    int resta(int a,int b) throws RemoteException;
    int multi(int a,int b) throws RemoteException;
    float divi(int a,int b) throws RemoteException;
}

