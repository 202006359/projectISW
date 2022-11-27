package dao;

import java.sql.*;

public class ReservaDAO {


    public static void crearReserva(String usuario, String nombrePlan, Date fecha, Time hora) { //Me crea la reserva
        Connection con=ConnectionDAO.getInstance().getConnection();

        try (PreparedStatement pst = con.prepareStatement("INSERT INTO reservas VALUES ('" + nombrePlan + "', '" + usuario + "', '" + fecha+ "', '" + hora +"')")) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
