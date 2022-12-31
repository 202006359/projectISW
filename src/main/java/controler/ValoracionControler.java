package controler;
import dao.CustomerDAO;
import dao.ReservaDAO;
import dao.ValoracionDAO;

import java.sql.Date;
import java.sql.Time;

/**
 *  Esta clase gestiona las reservas de la base de datos
 */

public class ValoracionControler {
    public void createValoracion(String usuario, String nombrePlan, float nota, Date fecha, String comentario){
        ValoracionDAO.crearValoracion(usuario, nombrePlan, nota, fecha, comentario);
    }
}

