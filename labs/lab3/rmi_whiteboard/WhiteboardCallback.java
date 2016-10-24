import java.rmi.*;
import java.util.Vector;

public interface WhiteboardCallback extends Remote {
	void callback(int version) throws RemoteException;
}
