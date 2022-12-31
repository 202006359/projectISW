package dao;

import java.sql.*;

public class ValoracionDAO {

    public static void crearValoracion(String usuario, String nombrePlan, float nota, Date fecha, String comentario) { //Me crea la VALORACION
        Connection con=ConnectionDAO.getInstance().getConnection();

        try (PreparedStatement pst = con.prepareStatement("INSERT INTO Valoraciones VALUES('" + usuario + "', '" + nombrePlan + "', '" + nota + "', '" + fecha +"', '" + comentario +"')")) {
            pst.executeUpdate();
            System.out.println("ESTOY AQUI");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

