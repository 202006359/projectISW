package main.dao;
import main.domain.Actividad;
import main.domain.Customer;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProfileDAO {

    public static ArrayList<Perfil> getPerfil(){
        ArrayList<Perfil> perfiles = new ArrayList<>();
        Connection con = ConnectionDAO.getInstance().getConnection();
        Perfil perf = null;
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM perfiles");
             ResultSet rs = pst.executeQuery(); {

            while (rs.next()) {
                act = new Perfile(rs.getString(1), rs.getString(4));
                perfiles.add(perf);
            }
        } catch (SQLException ex){
                 System.out.println(ex.getMessage());
        }
        return perfiles;
    }
}
