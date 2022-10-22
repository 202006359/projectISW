package icai.dtc.isw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import icai.dtc.isw.domain.*;

public class CustomerDAO {

	public static Customer getContrasena(String usuario) { //Me interesa para luego comprobar la contraseña y en caso de que sea incorrecta mostrarlo
		Connection con=ConnectionDAO.getInstance().getConnection();
		Customer cu=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT usuario, contrasena FROM login WHERE usuario= '"+usuario+"'");
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				cu= new Customer(rs.getString(1),rs.getString(2));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return cu;
	}

	public static void crearCuenta(String usuario, String contrasena) { //Me crea usuario y contraseña
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("INSERT INTO login (usuario, contrasena) VALUES ('" + usuario + "'," + "'" + contrasena + "')")) {
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void completarCuenta(String usuario, String perfil) { //Me añade el tipo de perfil al usuario
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("update login set perfil = '" + perfil + "' where usuario = '" + usuario + "'")) {
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	//public static ArrayList<Actividad> getActividades() {
	//	ArrayList<Actividad> actividades = new ArrayList<>();
	//	Connection con=ConnectionDAO.getInstance().getConnection();
	//	Actividad act=null;
	//	try (PreparedStatement pst = con.prepareStatement("SELECT * FROM actividades");
	//		 ResultSet rs = pst.executeQuery()) {
//
//			while (rs.next()) {
//				act= new Actividad(rs.getString(1),rs.getString(2));
//				actividades.add(act);
//			}
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//		}
//		return actividades;
//		//ME DEVUELVE UN ARRAY CON TODAS LAS ACTIVIDADES EN LA BASE DE DATOS;
	}
