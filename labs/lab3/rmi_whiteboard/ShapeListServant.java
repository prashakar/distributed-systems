import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.*;
public class ShapeListServant extends UnicastRemoteObject implements ShapeList {
	private static final long serialVersionUID = 1L;
	private Vector<Shape> theList;
	private int version;
	private List<WhiteboardCallback> clients;
	
	public ShapeListServant() throws RemoteException{
		theList = new Vector<Shape>();
		clients = new ArrayList<WhiteboardCallback>();
		version = 0;
	}

	public Shape newShape(GraphicalObject g) throws RemoteException{
		version++;
		Shape s = new ShapeServant(g, version);
		theList.addElement(s);
		getVersion();
		return s;
	}

	public int register(WhiteboardCallback callback) throws RemoteException{
		Random rn = new Random();
		int callbackId = rn.nextInt();
		clients.add(callback);
		System.out.println(callback);
		//callback.callback(version);
		return callbackId;
	}

	public int unregister(int callbackId) throws RemoteException {
		Iterator itr = clients.iterator();
		int strElement = 0;
		while(itr.hasNext()){
			strElement = (int)itr.next();
			if(strElement == callbackId){
				itr.remove();
				break;
			}
		}
		return 1;
	}

	public Vector<Shape> allShapes() throws RemoteException{
		return theList;
	}

	public void getVersion() throws RemoteException{
		Iterator<WhiteboardCallback> itr = clients.iterator();
		while(itr.hasNext()){
			WhiteboardCallback element = itr.next();
			element.callback(version);
			//callback.callback(version);
                }
	}
}
