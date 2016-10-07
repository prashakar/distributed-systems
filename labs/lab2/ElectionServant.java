import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.io.*;

public class ElectionServant extends UnicastRemoteObject implements Election {
	private int count;
	HashMap hm = new HashMap();
	public ElectionServant() throws RemoteException { count = 0; }
	
	public String vote(String name, int number) {
        	try{
			File f = new File("map.ser");
        		if (f.exists() && !f.isDirectory()) {
				System.out.println("file exitss!!!!");
        		        FileInputStream fis = new FileInputStream("map.ser");
        		        ObjectInputStream ois = new ObjectInputStream(fis);
        		        hm = (HashMap) ois.readObject();
        		        ois.close();
        		}
		}catch (Exception e){
			System.out.println(e.getClass());
		}
		System.out.println("Voting for: " + name);
		if (hm.size() == 0) {
			System.out.println("new canidate");
			hm.put(name, new Double(0));
		}
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			System.out.println(me.getKey());
			if (me.getKey().equals(name)) {
				System.out.println("canidate exists in map");
				System.out.println("total votes: " + me.getValue());
				double new_vote_count = (Double)me.getValue() + 1;
				hm.put(name, new Double(new_vote_count));
				System.out.println("new total votes: " + me.getValue());
			}
			else {
				System.out.println("canidate DOES NOT exist");
				System.out.println("now has 1 vote");
				hm.put(name, new Double(1));
				double new_vote_count = 1;
			}
		}
		double new_vote_count = 1;
		try{
			FileOutputStream fos = new FileOutputStream("map.ser");
        		ObjectOutputStream oos = new ObjectOutputStream(fos);
        		oos.writeObject(hm);
        		oos.close();
                }catch (Exception e){
                        System.out.println(e.getClass());
                }
		return "Vote for " + name + " recorded and is now: " + Integer.toString(hm.size());
//+ " is " + Double.toString(new_vote_count);
	}
	public double result(String name) {
		System.out.println("Requesting votes for" + name);
		double num_votes = 0;
		if (hm.size() == 0) {
			System.out.println("no votes recorded at all");
			num_votes = 0;
		} else {
			num_votes = ((Double)hm.get(name)).doubleValue();
			System.out.println("Total votes: " + num_votes);
		}
		return num_votes; 
	}
}
