import java.rmi.*;
import java.util.Vector;

public interface ShapeList extends Remote {
	Shape newShape(GraphicalObject g) throws RemoteException;
	Vector<Shape> allShapes() throws RemoteException;
	void getVersion() throws RemoteException;
	int register(WhiteboardCallback callback) throws RemoteException;
	int unregister(int callbackId) throws RemoteException;
}
