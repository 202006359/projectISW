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

    public ArrayList<Actividad> getActividades() {
        ActivityDAO.getActividades();
        return ActivityDAO.getActividades();
    }
    public Actividad getDescription(String nombre) {return ActivityDAO.getDescripcion(nombre);}
    public Actividad getLocation(String nombre) {return ActivityDAO.getUbicacion(nombre);}

    public static void completeActivity(String nombre, float descuento){ActivityDAO.completarActividad(nombre, descuento);}


    //public Actividad getImagen(String nombre) {return ActivityDAO.getImagen(nombre);}
    //public Actividad getDescuento(String nombre) {return ActivityDAO.getDescuento(nombre);}
    //public void addDescuento(String nombre, int descuento) {ActivityDAO.addDescuento(nombre,descuento);}

}
