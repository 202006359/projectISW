package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;


import org.apache.log4j.Logger;

import configuration.PropertiesISW;
import domain.*;
import message.Message;

public class Client {
	private String host;
	private int port;
	final static Logger logger = Logger.getLogger(Client.class);
	public Client(String host, int port) {
		this.host=host;
		this.port=port;
	}
	public Client() {
		this.host = PropertiesISW.getInstance().getProperty("host");
		this.port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
	}
	public HashMap<String, Object> sentMessage(String Context, HashMap<String, Object> session) {
		//Configure connections
		//String host = PropertiesISW.getInstance().getProperty("host");
		//int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
		Logger.getRootLogger().info("Host: "+host+" port"+port);
		System.out.println("Host: "+host+" port"+port);
		//Create a cliente class
		//Client cliente=new Client(host, port);
		
		//HashMap<String,Object> session=new HashMap<String, Object>();
		//session.put("/getCustomer","");
		
		Message mensajeEnvio=new Message();
		Message mensajeVuelta=new Message();
		mensajeEnvio.setContext(Context);///getCustomer"
		mensajeEnvio.setSession(session);
		this.sent(mensajeEnvio,mensajeVuelta);
		Customer customer;
		Actividad actividad;
		Reserva reserva;
		
		switch (mensajeVuelta.getContext()) {
			case "/getPasswordResponse":
				session=mensajeVuelta.getSession();
				customer =(Customer) (session.get("Customer"));
				System.out.println("He leído el usuario: "+customer.getUsuario()+" con contrasena: "+customer.getContrasena());
				break;
			case "/checkUserResponse":
				session=mensajeVuelta.getSession();
				break;
			case "/createAccountResponse":
				session = mensajeVuelta.getSession();
				customer =(Customer) (session.get("Customer"));
				System.out.println("He creado el usuario: "+customer.getUsuario()+" con contrasena: "+customer.getContrasena());
				break;
			case "/completeAccountResponse":
				session = mensajeVuelta.getSession();
				customer =(Customer) (session.get("Customer"));
				System.out.println("He añadido al usuario: "+customer.getUsuario()+" el perfil de "+ customer.getPerfil());
				break;
			case "/getActividadesResponse":
				session = mensajeVuelta.getSession();
				break;
			case "/getDescriptionResponse":
				session=mensajeVuelta.getSession();
				actividad =(Actividad) (session.get("Actividad"));
				System.out.println("He leído la actividad: "+actividad.getNombre()+" con descripcion: "+actividad.getDescripcion());
				break;
			case "/getLocationResponse":
				session=mensajeVuelta.getSession();
				actividad =(Actividad) (session.get("Actividad"));
				System.out.println("He leído la actividad: "+actividad.getNombre()+" con ubicacion: "+actividad.getUbicacion());
				break;

			case "/getPerfilResponse":
				session=mensajeVuelta.getSession();
				customer =(Customer) (session.get("Customer"));
				System.out.println("He leído el usuario: "+customer.getUsuario()+" con perfil: "+customer.getPerfil());
				break;

			case "/completeActivityResponse":
				session=mensajeVuelta.getSession();
				actividad =(Actividad) (session.get("Actividad"));
				System.out.println("He leído el descuento: "+actividad.getDescuento()+" de la actividad: "+actividad.getNombre());
				break;
			case "/createReservationResponse":
				session = mensajeVuelta.getSession();
				reserva =(Reserva) (session.get("Reserva"));
				System.out.println("He creado la reserva de " + reserva.getNombrePlan() + " para el dia " + reserva.getFecha() + " y la hora " + reserva.getHora());
				break;

			default:
				Logger.getRootLogger().info("Option not found");
				System.out.println("\nError a la vuelta");
				break;

		
		}
		//System.out.println("3.- En Main.- El valor devuelto es: "+((String)mensajeVuelta.getSession().get("Nombre")));
		return session;
	}


	public void sent(Message messageOut, Message messageIn) {
		try {

			System.out.println("Connecting to host " + host + " on port " + port + ".");

			Socket echoSocket = null;
			OutputStream out = null;
			InputStream in = null;

			try {
				echoSocket = new Socket(host, port);
				in = echoSocket.getInputStream();
				out = echoSocket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				
				//Create the objetct to send
				objectOutputStream.writeObject(messageOut);
				
				// create a DataInputStream so we can read data from it.
		        ObjectInputStream objectInputStream = new ObjectInputStream(in);
		        Message msg=(Message)objectInputStream.readObject();
		        messageIn.setContext(msg.getContext());
		        messageIn.setSession(msg.getSession());
		        /*System.out.println("\n1.- El valor devuelto es: "+messageIn.getContext());
		        String cadena=(String) messageIn.getSession().get("Nombre");
		        System.out.println("\n2.- La cadena devuelta es: "+cadena);*/
				
			} catch (UnknownHostException e) {
				System.err.println("Unknown host: " + host);
				System.exit(1);
			} catch (IOException e) {
				System.out.println(e.getStackTrace());
				System.err.println("Unable to get streams from server");
				System.exit(1);
			}		

			/** Closing all the resources */
			out.close();
			in.close();			
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}