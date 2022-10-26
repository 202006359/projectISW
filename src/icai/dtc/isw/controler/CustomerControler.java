package icai.dtc.isw.controler;

import java.util.ArrayList;

import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.*;

public class CustomerControler {

	public Customer getPassword(String usuario) {return CustomerDAO.getContrasena(usuario);}

	public void createAccount(String usuario, String contrasena){CustomerDAO.crearCuenta(usuario, contrasena);}

	public void completeAccount(String usuario, String perfil){CustomerDAO.completarCuenta(usuario, perfil);}

	//public ArrayList<Actividad> getActividades(){CustomerDAO.getActividades(); return CustomerDAO.getActividades();}
}
