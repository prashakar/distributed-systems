import java.net.*;
import java.io.*;
public class TCPServer {
  public static void main (String args[]) {
		try{
			int serverPort = 7896; 
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e){
				System.out.println("Listen :"+e.getMessage());}
  }
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
	  try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
	    } catch(IOException e)  {
				System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
	  try {	
			while(true) {		                 // an echo server
				String data = in.readUTF();
				if (data.equals("user_leave")) {
					out.writeUTF("Bye!");
				} else {
					out.writeUTF(data);
					System.out.println(data);
				}
			}
	    } catch(EOFException e) {System.out.println("User left :)");
	    } catch(IOException e) {System.out.println("IO:"+e.getMessage());
	    } finally{
	    	try {
	    		clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}
