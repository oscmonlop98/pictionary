package pictionary;

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import pictionary.PaqueteEnvio;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread mihilo = new Thread(this);
		mihilo.start();
		
		}
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			ServerSocket servidor = new ServerSocket(9999);
			PaqueteEnvio datos = new PaqueteEnvio();
			
			while (true) {
				
				Socket misocket = servidor.accept();
				String mensaje = "";		
				
				ObjectInputStream flujo_entrada = new ObjectInputStream(misocket.getInputStream());
				
				ObjectOutputStream flujo_salida = new ObjectOutputStream(misocket.getOutputStream());
				
				try {
					
					datos = (PaqueteEnvio) flujo_entrada.readObject();
					mensaje = "IP: " + datos.getIp() + "\n Nick: " + datos.getNick() + "\n Mensaje: " + datos.getMensaje();
					
					flujo_salida.writeObject(datos);
					
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				areatexto.append("\n" + mensaje);
				
				
				
				misocket.close();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}
}
