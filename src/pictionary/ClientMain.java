package pictionary;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String host = "localhost";
		int port = 9999;		
		
		Socket s = new Socket(host, port);
		
		ClientThread cli = new ClientThread(s);
		cli.start();

	}

}
