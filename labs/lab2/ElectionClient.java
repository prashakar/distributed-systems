import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ElectionClient {
	private ElectionClient() {};
	
	public static void main(String args[]) {
		// args[0]: hostname of server where registry resides
		// args[1]: port of registry
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		Scanner console = new Scanner(System.in);
		try {
			Registry registry = LocateRegistry.getRegistry(host, port);
			Election stub = (Election) registry.lookup("Election");
			System.out.println("Enter desired operation:");
			String op = console.nextLine();
			if (op.equals("vote")) {
				System.out.println("Enter name:");
				String name = console.nextLine();
				System.out.println("Enter vote number:");
				int vote_num = console.nextInt();
				String response = stub.vote(name,vote_num);
				System.out.println("response: " + response);
			} else if (op.equals("results")){
				System.out.println("Who would you like to see results for:");
				String name = console.nextLine();
				double response = stub.result(name);
				System.out.println("response: " + response);
			}
		}catch (Exception e) {
			System.err.println("ElectionClient exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
