package icai.dtc.isw.controler;

import icai.dtc.isw.dao.ActivityDAO;
import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.Actividad;
import icai.dtc.isw.domain.Customer;
import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.*;
import java.util.ArrayList;

public class ActivitiesControler {

    public static ArrayList<Actividad> getActividades() {
        ActivityDAO.getActividades();
        return ActivityDAO.getActividades();
    }
}
