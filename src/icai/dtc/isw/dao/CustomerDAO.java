package icai.dtc.isw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import icai.dtc.isw.domain.*;

public class CustomerDAO {

	public static void getClientes(ArrayList<Customer> lista) {
		Connection con=ConnectionDAO.getInstance().getConnection();
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
            	lista.add(new Customer(rs.getString(1),rs.getString(2)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
	}
	public static Customer getCliente(int id) {
		Connection con=ConnectionDAO.getInstance().getConnection();
		Customer cu=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios WHERE id="+id);
			 ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				cu= new Customer(rs.getString(1),rs.getString(2));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return cu;
		//return new Customer("1","Atilano");
	}
	
	public static void main(String[] args) {
		
		
		ArrayList<Actividad> lista= new ArrayList<>();

		lista=CustomerDAO.getActividades();
		
		 for (Actividad act : lista) {
			System.out.println("He leído el usuario: "+act.getUsuario());
		}


		
	
	}



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
		//return new Customer("1","Atilano");
	}

	public static void crearCuenta(String usuario, String contrasena) {
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("INSERT INTO login (usuario, contrasena) VALUES ('" + usuario + "'," + "'" + contrasena + "')")) {


			pst.executeUpdate();



		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
	}

	public static ArrayList<Actividad> getActividades() {
		ArrayList<Actividad> actividades = new ArrayList<>();
		Connection con=ConnectionDAO.getInstance().getConnection();
		Actividad act=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM actividades");
			 ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				act= new Actividad(rs.getString(1),rs.getString(2));
				actividades.add(act);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return actividades;
		//ME DEVUELVE UN ARRAY CON TODAS LAS ACTIVIDADES EN LA BASE DE DATOS;
	}
}
