import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServant extends UnicastRemoteObject implements Hello {
	private int count;
	public HelloServant() throws RemoteException { count = 0; }
	
	public String sayHello(String m) {
		System.out.println(m);
		return "Hello " + Integer.toString(count++);
	}
}
