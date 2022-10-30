package main.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import main.configuration.PropertiesISW;
import main.controler.*;
import main.domain.Actividad;
import main.domain.Customer;
import main.message.Message;

public class SocketServer extends Thread {
	public static int port = 8081; //Valor por defecto

	protected Socket socket;

	private SocketServer(Socket socket) {
		this.socket = socket;
		//Configure connections
		this.port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
		start();
	}

	public void run() {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			//first read the object that has been sent
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
		    Message mensajeIn= (Message)objectInputStream.readObject();
		    //Object to return informations 
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
		    Message mensajeOut=new Message();
			HashMap<String,Object> session=mensajeIn.getSession();
			CustomerControler customerControler;
			String usuario;
			String contrasena;
			String perfil;
			Customer cu;
		    switch (mensajeIn.getContext()) {
				case "/getPassword":
					usuario= (String) session.get("usuario");
					customerControler=new CustomerControler();
					cu=customerControler.getPassword(usuario);
					System.out.println("usuario:"+cu.getUsuario());
					mensajeOut.setContext("/getPasswordResponse");
					session.put("Customer",cu);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/createAccount":
					usuario= (String) session.get("usuario");
					contrasena = (String) session.get("contrasena");
					customerControler=new CustomerControler();
					cu = new Customer(usuario,contrasena);
					customerControler.createAccount(usuario,contrasena);
					mensajeOut.setContext("/createAccountResponse");
					session.put("Customer",cu);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/completeAccount":
					usuario= (String) session.get("usuario");
					contrasena = (String) session.get("contrasena");
					perfil = (String) session.get("perfil");
					customerControler=new CustomerControler();
					cu = new Customer(usuario,contrasena, perfil);
					customerControler.completeAccount(usuario,perfil);
					mensajeOut.setContext("/completeAccountResponse");
					session.put("Customer",cu);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/getActividades":
					customerControler =new CustomerControler();
					ArrayList<Actividad> actividades= ActivitiesControler.getActividades();
					mensajeOut.setContext("/getActividadesResponse");
					session.put("Actividades",actividades);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;

		    	
		    	default:
		    		System.out.println("\nParámetro no encontrado");
		    		break;
		    }
		    
		    //Lógica del controlador 
		    //System.out.println("\n1.- He leído: "+mensaje.getContext());
		    //System.out.println("\n2.- He leído: "+(String)mensaje.getSession().get("Nombre"));
		    
		    
		    
		    //Prueba para esperar
		    /*try {
		    	System.out.println("Me duermo");
				Thread.sleep(15000);
				System.out.println("Me levanto");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			// create an object output stream from the output stream so we can send an object through it
			/*ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
			
			//Create the object to send
			String cadena=((String)mensaje.getSession().get("Nombre"));
			cadena+=" añado información";
			mensaje.getSession().put("Nombre", cadena);
			//System.out.println("\n3.- He leído: "+(String)mensaje.getSession().get("Nombre"));
			objectOutputStream.writeObject(mensaje);*
			*/

		} catch (IOException ex) {
			System.out.println("Unable to get streams from client");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("SocketServer Example");
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				/**
				 * create a new {@link SocketServer} object for each connection
				 * this will allow multiple client connections
				 */
				new SocketServer(server.accept());
			}
		} catch (IOException ex) {
			System.out.println("Unable to start server.");
		} finally {
			try {
				if (server != null)
					server.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}