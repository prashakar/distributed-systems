import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class WhiteboardCallbackServant extends UnicastRemoteObject implements WhiteboardCallback {
	public WhiteboardCallbackServant() throws RemoteException{}

	public void callback(int version) throws RemoteException {
		System.out.println("Ive been updated to version: " + version + "!");
	}

}
