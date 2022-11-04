package controler;

import dao.ActivityDAO;
import domain.Actividad;

import java.util.ArrayList;

public class ActivitiesControler {

    public static ArrayList<Actividad> getActividades() {
        ActivityDAO.getActividades();
        return ActivityDAO.getActividades();
    }


}
