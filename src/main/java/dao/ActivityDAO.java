package dao;
import domain.Actividad;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDAO {


	/**
	 *  Esta clase obtiene las actividades de la base de datos
	 */

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
				act= new Actividad(rs.getString(1),rs.getString(4));
				actividades.add(act);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return actividades;
}
}

