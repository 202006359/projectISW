package main.controler;

import java.util.ArrayList;

import main.dao.CustomerDAO;
import main.domain.*;

public class CustomerControler {

	public Customer getPassword(String usuario) {return CustomerDAO.getContrasena(usuario);}

	public void createAccount(String usuario, String contrasena){CustomerDAO.crearCuenta(usuario, contrasena);}

	public void completeAccount(String usuario, String perfil){CustomerDAO.completarCuenta(usuario, perfil);}

	public Customer getPerfil(String usuario) {return CustomerDAO.getPerfil(usuario);}


	//public ArrayList<Actividad> getActividades(){CustomerDAO.getActividades(); return CustomerDAO.getActividades();}
}
