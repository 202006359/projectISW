package main.controler;

import main.dao.ActivityDAO;
import main.dao.CustomerDAO;
import main.domain.Actividad;
import main.domain.Customer;
import main.dao.CustomerDAO;
import main.domain.*;
import java.util.ArrayList;

public class ActivitiesControler {

    public static ArrayList<Actividad> getActividades() {
        ActivityDAO.getActividades();
        return ActivityDAO.getActividades();
    }
}
