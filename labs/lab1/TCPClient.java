import java.net.*;
import java.io.*;
public class TCPClient {
	public static void main (String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		BufferedReader br = null;
		try{
			int serverPort = 7896;
			s = new Socket(args[0], serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =
				new DataOutputStream( s.getOutputStream());	
			br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println("Enter something to send:");
				String input = br.readLine();
				if (input.length() == 0) {
					out.writeUTF("user_leave");
					String data = in.readUTF();
					System.out.println("Message from server: "+data);
					break;
				}
				out.writeUTF(input);
				String data = in.readUTF();	      
				System.out.println("Received: "+ data) ; 
			}
			//out.writeUTF(args[0]);        	// UTF is a string encoding see Sn 4.3
			     
		}catch (UnknownHostException e){
			System.out.println("Sock:"+e.getMessage()); 
		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){
			System.out.println("IO:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}
	catch (IOException e){System.out.println("close:"+e.getMessage());}}
	}
}

