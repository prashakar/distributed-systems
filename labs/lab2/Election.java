import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
	String vote(String name, int number) throws RemoteException;
	double result(String name) throws RemoteException;
	
}
