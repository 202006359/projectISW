package controler;

import dao.ActivityDAO;
import domain.Actividad;

import java.util.ArrayList;

public class ActivitiesControler {


    /**
     * Esta clase gestiona las actividades guardadas en la base de datos.
     */
    public static ArrayList<Actividad> getActividades() {
        ActivityDAO.getActividades();
        return ActivityDAO.getActividades();
    }


}
