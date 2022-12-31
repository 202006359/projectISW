package server;

import configuration.PropertiesISW;
import controler.ActivitiesControler;
import controler.CustomerControler;
import controler.ReservaControler;
import domain.Actividad;
import domain.Customer;
import domain.Reserva;
import message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

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
			ReservaControler reservaControler;
			String usuario;
			String contrasena;
			String perfil;
			Float descuento;
			Customer cu;
			ActivitiesControler activitiesControler;
			Actividad act;
			String nombre;
			String descripcion;
			String ubicacion;
			Reserva reserva;
			String plan;
			Date fecha;
			Time hora;
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
				case "/checkUser":
					boolean existe;
					usuario= (String) session.get("usuario");
					customerControler = new CustomerControler();
					existe = customerControler.checkUser(usuario);
					mensajeOut.setContext("/checkUserResponse");
					session.put("Customer",existe);
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
					activitiesControler =new ActivitiesControler();
					ArrayList<Actividad> actividades= activitiesControler.getActividades();
					mensajeOut.setContext("/getActividadesResponse");
					session.put("Actividades",actividades);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					System.out.println("ESTOY AQUIIII");
					break;

				case "/getPerfil":
					usuario= (String) session.get("usuario");
					customerControler=new CustomerControler();
					Customer c = customerControler.getPer(usuario);
					System.out.println("usuario:"+c.getUsuario());
					mensajeOut.setContext("/getPerfilResponse");
					session.put("Customer",c);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/getDescription":
					nombre = (String) session.get("nombre");
					activitiesControler =new ActivitiesControler();
					act = activitiesControler.getDescription(nombre);
					System.out.println("nombre:"+act.getNombre());
					mensajeOut.setContext("/getDescriptionResponse");
					session.put("Actividad",act);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/getLocation":
					nombre = (String) session.get("nombre");
					activitiesControler = new ActivitiesControler();
					act = activitiesControler.getLocation(nombre);
					System.out.println("nombre:"+act.getNombre());
					mensajeOut.setContext("/getLocationResponse");
					session.put("Actividad",act);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/getImagen":
					nombre = (String) session.get("nombre");
					activitiesControler = new ActivitiesControler();
					act = activitiesControler.getLocation(nombre);
					System.out.println("nombre:"+act.getNombre());
					mensajeOut.setContext("/getImagenResponse");
					session.put("Actividad",act);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/completeActivity":
					nombre = (String) session.get("nombre");
					descuento = (float) session.get("descuento");
					activitiesControler=new ActivitiesControler();
					act = new Actividad(nombre, descuento);
					activitiesControler.completeActivity(nombre,descuento);
					mensajeOut.setContext("/completeActivityResponse");
					session.put("Actividad",act);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;
				case "/createReservation":
					usuario= (String) session.get("usuario");
					plan = (String) session.get("plan");
					fecha = (Date) session.get("fecha");
					hora = (Time) session.get("hora");
					reservaControler=new ReservaControler();
					reserva = new Reserva(plan,fecha,hora,new Customer(usuario));
					reservaControler.createReservation(usuario,plan,fecha,hora);
					mensajeOut.setContext("/createReservationResponse");
					session.put("Reserva",reserva);
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
