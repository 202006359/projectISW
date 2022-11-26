package dao;
import domain.Actividad;
import domain.Customer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Esta clase obtiene las actividades de la base de datos
 */

public class ActivityDAO {


   /*public static void main(String[] args) {

        ArrayList<Actividad> lista = new ArrayList<>();

        lista = ActivityDAO.getActividades();
        System.out.println(lista);

    }*/



	//Obtengo un arrayList con cada actividad, cada una de ellas asociada a una categoria (columna 4 en la base de datos).
    public static ArrayList<Actividad> getActividades() {
    	ArrayList<Actividad> actividades = new ArrayList<>();
    	Connection con=ConnectionDAO.getInstance().getConnection();
    	Actividad act=null;
    	try (PreparedStatement pst = con.prepareStatement("SELECT * FROM planes");
    		 ResultSet rs = pst.executeQuery()) {

			//la columna 4 me da la categoria
			while (rs.next()) {


				if(rs.getBinaryStream(5)!=null){
					InputStream inputStream = rs.getBinaryStream(5);
					Image image = ImageIO.read(inputStream);
					act= new Actividad(rs.getString(1),rs.getString(4),image,rs.getFloat(6));
				}
				else{
					act= new Actividad(rs.getString(1),rs.getString(4));
				}

				actividades.add(act);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actividades;
	}

	public static Actividad getDescripcion(String nombre) { //Me interesa para luego comprobar la contraseña y en caso de que sea incorrecta mostrarlo
		Connection con=ConnectionDAO.getInstance().getConnection();
		Actividad act=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT nombre, descripcion FROM planes WHERE nombre= '"+nombre+"'");
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				act= new Actividad(rs.getString(1),rs.getString(2), true);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return act;
	}
	public static Actividad getUbicacion(String nombre) { //Leo la ubicacion
		Connection con=ConnectionDAO.getInstance().getConnection();
		Actividad act=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT nombre, ubicacion FROM planes WHERE nombre= '"+nombre+"'");
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				act= new Actividad(rs.getString(1),rs.getString(2), false);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return act;
	}
	public static void addImagen(String nombre, File file) { //Me guarda una imagen en la base de datos
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("UPDATE planes SET imagen = ? WHERE nombre ='" + nombre + "';")) {
			FileInputStream fileInputStream = new FileInputStream(file);
			pst.setBinaryStream(1,fileInputStream,(int) file.length());

			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Actividad getImagen(String nombre) {//Obtengo la imagen de la base de datos
		Connection con=ConnectionDAO.getInstance().getConnection();
		Actividad act=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT nombre, imagen FROM planes WHERE nombre = '" + nombre +"';" );
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				InputStream inputStream = rs.getBinaryStream(2);
				Image image = ImageIO.read(inputStream);
				act= new Actividad(rs.getString(1),image);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return act;
	}

	public static ArrayList<String> getNombres() {//Obtengo todos los nombres
		Connection con=ConnectionDAO.getInstance().getConnection();
		ArrayList<String> nombres = new ArrayList<>();
		try (PreparedStatement pst = con.prepareStatement("SELECT nombre FROM planes;" );
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				nombres.add(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return nombres;
	}


	public static void completarActividad(String nombre , float descuento) { //Me añade el tipo de perfil al usuario
		Connection con=ConnectionDAO.getInstance().getConnection();

		try (PreparedStatement pst = con.prepareStatement("update descuentos set descuento = '" + descuento + "' where nombre_plan = '" + nombre + "'")) {
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

}

