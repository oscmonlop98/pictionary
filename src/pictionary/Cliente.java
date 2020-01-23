package pictionary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
	
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton = new JButton("Enviar");
		
		EnviaTexto mievento = new EnviaTexto();
		
		miboton.addActionListener(mievento);
		
		add(miboton);	
		
	}
	
	
	private class EnviaTexto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				Socket misocket = new Socket("localhost", 9999);
				
				ObjectOutputStream flujo_salida = new ObjectOutputStream(misocket.getOutputStream());
				
				PaqueteEnvio datos = new PaqueteEnvio();
				
				String ip, nick, mensaje;
				
				ip = misocket.getInetAddress().toString();
				nick = "Pepe";
				mensaje = campo1.getText();
				
				datos.setIp(ip);
				datos.setMensaje(mensaje);
				datos.setNick(nick);
				
				flujo_salida.writeObject(datos);
				
				flujo_salida.close();
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		
		
	}
	
	
	
		
		
		
	private JTextField campo1;
	
	private JButton miboton;
	
}