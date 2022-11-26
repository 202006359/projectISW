package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.*;
import ui.VentanaActividades;

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

	public static boolean checkUsuario(String usuario){ //Me devuelve true si el usuario existe en la base de datos
		Connection con=ConnectionDAO.getInstance().getConnection();
		boolean existe = false;
		try (PreparedStatement pst = con.prepareStatement("SELECT usuario FROM login WHERE usuario= '"+usuario+"'");
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				if(rs.getString(1).equals(usuario)){
					existe = true;
				}else{
					existe = false;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return existe;

	}

	public static void crearCuenta(String usuario, String contrasena) { //Me crea usuario y contraseña
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("INSERT INTO login (usuario, contrasena) VALUES ('" + usuario + "'," + "'" + contrasena + "')")) {
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	//ESTE METODO ACTUALIZA LA BASE DE DATOS, ASOCIANDO UNA CATEGORIA A CADA USUARIO TRAS LA REALIZACION DEL CUESTIONARIO
	public static void completarCuenta(String usuario, String perfil) { //Me añade el tipo de perfil al usuario
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("update login set perfil = '" + perfil + "' where usuario = '" + usuario + "'")) {
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	//ESTE METODO OBTIENE DE LA BASE DE DATOS LA CATEGORIA ASOCIADA AL USUARIO (perfil) QUE SE HA GUARDADO EL METODO ANTERIOR.
	public static Customer getPerfilUsuario(String usuario) {
		Connection con=ConnectionDAO.getInstance().getConnection();
		Customer c=null;
		//Customer cu;
		System.out.println("La sentencia es> "+"SELECT usuario, contrasena, perfil FROM login WHERE usuario= '"+usuario+"'");
		try (PreparedStatement pst = con.prepareStatement("SELECT usuario, contrasena, perfil FROM login WHERE usuario= '"+usuario+"'");
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				c= new Customer(rs.getString(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return c;
	}


	/*public static void main(String[] args) {
		CustomerDAO cu = new CustomerDAO();
		Customer c = cu.getPerfilUsuario("beatrizorbe");
		System.out.println(c.getPerfil());


	}*/

}
