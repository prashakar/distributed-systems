import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.*;
public class ShapeListServant extends UnicastRemoteObject implements ShapeList {
	private static final long serialVersionUID = 1L;
	private Vector<Shape> theList;
	private int version;
	private List<WhiteboardCallback> clients;
	private HashMap<Integer,WhiteboardCallback> hm;
	private int callbackId;

	public ShapeListServant() throws RemoteException{
		theList = new Vector<Shape>();
		clients = new ArrayList<WhiteboardCallback>();
		hm = new HashMap<Integer,WhiteboardCallback>();
		version = 0;
		callbackId = 0;
	}

	public Shape newShape(GraphicalObject g) throws RemoteException{
		version++;
		Shape s = new ShapeServant(g, version);
		theList.addElement(s);
		getVersion();
		return s;
	}

	public int register(WhiteboardCallback callback) throws RemoteException{
		callbackId+=1;
		hm.put(callbackId, callback);
		//clients.add(callback);
		System.out.println("Client registered");
		//callback.callback(version);
		return callbackId;
	}

	public int unregister(int callbackId) throws RemoteException {
		hm.remove(callbackId);
		System.out.println("Client unregistered");
		return 1;
	}

	public Vector<Shape> allShapes() throws RemoteException{
		return theList;
	}

	public void getVersion() throws RemoteException{
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			WhiteboardCallback element = (WhiteboardCallback)me.getValue();
			element.callback(version);
			//callback.callback(version);
                }
	}
}
