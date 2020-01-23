package pictionary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientThread extends Thread{
	
	/*private String name;
	
	
	*/
	Socket s;
	String host; 
	int port;
	DataOutputStream out;
	Scanner sn;
	String message;
	
	public ClientThread(Socket s) {
		this.s = s;
		}


	public void run() {
		try {
			
			Socket s = new Socket("localhost", 9999);
			ObjectOutputStream flujo_salida = new ObjectOutputStream(s.getOutputStream());
			
			PaqueteEnvio datos = new PaqueteEnvio();
			
			//Puede escuchar al servidor siempre
			ServerEcho e = new ServerEcho(s);
			e.start();
			
			

			while(true) {
				String message = askMessage(s, datos);
				sendMessage(message);
				flujo_salida.writeObject(datos);
				flujo_salida.close();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String askMessage(Socket socket, PaqueteEnvio datos) {
		
		String ip, nick, mensaje;
		
		ip = socket.getInetAddress().toString();
		nick = "Pepe";
		mensaje = "Hhhh";
		
		datos.setIp(ip);
		datos.setMensaje(mensaje);
		datos.setNick(nick);
		
		message = mensaje;
		return message;
	}

}
