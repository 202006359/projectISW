package controler;

import dao.ActivityDAO;
import dao.CustomerDAO;
import domain.Actividad;
import domain.Customer;

import java.util.ArrayList;

/**
 * Esta clase gestiona las actividades guardadas en la base de datos.
 */

public class ActivitiesControler {

    public static ArrayList<Actividad> getActividades() {
        ActivityDAO.getActividades();
        return ActivityDAO.getActividades();
    }
    public Actividad getDescription(String nombre) {return ActivityDAO.getDescripcion(nombre);}
    public Actividad getLocation(String nombre) {return ActivityDAO.getUbicacion(nombre);}


}
