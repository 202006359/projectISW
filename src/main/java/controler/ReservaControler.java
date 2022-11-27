package controler;

import dao.CustomerDAO;
import dao.ReservaDAO;

import java.sql.Date;
import java.sql.Time;

/**
 *  Esta clase gestiona las reservas de la base de datos
 */

public class ReservaControler {
    public void createReservation(String usuario, String nombrePlan, Date fecha, Time hora){
        ReservaDAO.crearReserva(usuario, nombrePlan, fecha, hora);
    }
}
