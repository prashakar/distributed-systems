import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class HelloClient {
	private HelloClient() {};
	
	public static void main(String args[]) {
		// args[0]: hostname of server where registry resides
		// args[1]: port of registry
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		Scanner console = new Scanner(System.in);
		try {
			Registry registry = LocateRegistry.getRegistry(host, port);
			Hello stub = (Hello) registry.lookup("Hello");
			String response = stub.sayHello(console.nextLine());
			System.out.println("response: " + response);
		}catch (Exception e) {
			System.err.println("HelloClient exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
