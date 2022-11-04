package controler;

import dao.CustomerDAO;
import domain.*;

public class CustomerControler {

	public Customer getPassword(String usuario) {return CustomerDAO.getContrasena(usuario);}

	public void createAccount(String usuario, String contrasena){CustomerDAO.crearCuenta(usuario, contrasena);}

	public void completeAccount(String usuario, String perfil){CustomerDAO.completarCuenta(usuario, perfil);}

	public Customer getPer(String usuario) {return CustomerDAO.getPerfilUsuario(usuario);}


	//public ArrayList<Actividad> getActividades(){CustomerDAO.getActividades(); return CustomerDAO.getActividades();}
}
