package icai.dtc.isw.dao;
import icai.dtc.isw.domain.Actividad;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDAO {

    public static void main(String[] args) {

        ArrayList<Actividad> lista = new ArrayList<>();

        lista = ActivityDAO.getActividades();

        for (Actividad act : lista) {
          System.out.println("He leído el usuario: " + act.getNombre() + "," + act.getLocalizacion());
        }
    }

    public static ArrayList<Actividad> getActividades() {
        ArrayList<Actividad> actividades = new ArrayList<>();
        Connection con=ConnectionDAO.getInstance().getConnection();
        Actividad act=null;
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM planes;");
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
