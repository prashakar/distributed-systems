	import java.net.*;
	import java.io.*;
	import java.util.*;

	public class UDPClient{
	    public static void main(String args[]){ 
		// args give message contents and server hostname
		List<String> dropped = new ArrayList<String>();
		for (int x=1;x<10001;x++) {
		  DatagramSocket aSocket = null;
		  //init server port
		  int serverPort = 6789;
		  try {
			aSocket = new DatagramSocket(); 
   
			aSocket.setSoTimeout(1000);
			InetAddress aHost = InetAddress.getByName(args[0]);

                        byte [] m = Integer.toString(x).getBytes();
		        DatagramPacket request = new DatagramPacket(m, Integer.toString(x).length(), aHost, serverPort);
			aSocket.send(request);			                        
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			aSocket.receive(reply);
			String res = new String(reply.getData());
			System.out.println("Reply: " + res);
		  }catch (SocketTimeoutException e){
			System.out.println("-----PACKET TIMEOUT-----: " + e.getMessage());
			System.out.println("TIMEOUT ON: " + Integer.toString(x));			
		  	dropped.add(Integer.toString(x));
		  }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		  }catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		  } finally {
			if(aSocket != null) aSocket.close();
	    	  }
		}
		if (dropped.size() != 0) {
			System.out.println("Following numbers were dropped:\n" + dropped);
		}
	   } 
	
	}
	
