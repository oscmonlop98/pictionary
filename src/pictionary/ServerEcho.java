package pictionary;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServerEcho extends Thread{

	ObjectInputStream in;
	Socket s;
	PaqueteEnvio messageRead;
	public ServerEcho(Socket s) {
		this.s = s;
		
	}

	@Override
	public void run() {
		
		try {
			in = new ObjectInputStream(s.getInputStream());
			
			while(true) {				
				PaqueteEnvio message = receiveMessage();
				System.out.println(message);
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public PaqueteEnvio receiveMessage() {
		try {
			try {
				messageRead = (PaqueteEnvio) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return messageRead;
	}
	
	
	
}
