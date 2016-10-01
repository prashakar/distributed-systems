import java.net.*;
import java.io.*;
public class TCPClient2 {
	public static void main (String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		BufferedReader br = null;
		String test = args[0];
		try{
			for (int x=0;x<10001;x++){
			int serverPort = 7896;
			s = new Socket(args[1], serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =
				new DataOutputStream( s.getOutputStream());
			out.writeUTF(test+x);
			String data = in.readUTF();	      
			System.out.println("Received: "+ data) ; 
			//out.writeUTF(args[0]);        	// UTF is a string encoding see Sn 4.3
			}     
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

