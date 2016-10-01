import java.net.*;
import java.io.*;
public class UDPServer{
	public static void main(String args[]){ 
		DatagramSocket aSocket = null;
		try{
			aSocket = new DatagramSocket(6789);
			while(true){
				byte[] buffer = new byte[10000];			
				DatagramPacket request =
					new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				DatagramPacket reply = new DatagramPacket(request.getData(), 
						request.getLength(), request.getAddress(), request.getPort());
				String s = new String(reply.getData());
				System.out.println(s);
				aSocket.send(reply);
			}
		}catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}
}
