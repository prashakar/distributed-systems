import java.rmi.*;
import java.util.Scanner;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;

public class ShapeListClient{
	   public static void main(String args[]){
		   	String client_host = args[0];
			System.setProperty("java.rmi.server.hostname", client_host);
		   	String host = args[1];
			int port = Integer.parseInt(args[2]);
			try{
			Registry registry = LocateRegistry.getRegistry(host, port);
			ShapeList aList = (ShapeList) registry.lookup("ShapeList");
			System.out.println("Found server");
			System.out.println("registering for callback");
			WhiteboardCallback wbcb = new WhiteboardCallbackServant();
			System.out.println(wbcb);
			int client_reg = aList.register(wbcb);
			System.out.println("Client registered at: " + client_reg);
			while(true){
				String option = "";
				String type = "rectangle";
				Vector<Shape> shapeVec = aList.allShapes();
				System.out.println("Got vector");
				Scanner scanner = new Scanner(System.in);
				do{
					System.out.println("-------------------\nEnter request type (read/write/exit)");
					option = scanner.nextLine();
				} while (option.equals(""));
				if (option.equals("exit")) {
					System.out.println("exiting now");
					aList.unregister(client_reg);
					System.exit(0);
				}
				System.out.println("Enter shape type (rectangle/circle)");
				type = scanner.nextLine();
				System.out.println("option = " + option + ", shape = " + type);
				if(option.equals("read")){
					for(int i = 0; i < shapeVec.size(); i++){
						GraphicalObject g = ((Shape)shapeVec.elementAt(i)).getAllState();
						g.print();
					}
				} else if (option.equals("write")){
					GraphicalObject g = new GraphicalObject(type, 
					new Rectangle(50,50,300,400), Color.red, Color.blue, false);
					System.out.println("Created graphical object");
					aList.newShape(g);
					System.out.println("Stored shape with ShapeList object on server");
				} else {
					System.out.println();
					continue;
				}
			}
			}catch(RemoteException e) {System.out.println("allShapes: " + e.getMessage());
			}catch(Exception e) {System.out.println("Registry: " + e.getMessage());
			}	
	}
}
