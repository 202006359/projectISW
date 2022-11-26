package controler;

import dao.CustomerDAO;
import domain.*;

import java.util.Date;

/**
 * Esta clase gestiona la informacion de los usuarios guardada en la base de datos.
 */
public class CustomerControler {

	public Customer getPassword(String usuario) {return CustomerDAO.getContrasena(usuario);}

	public void createAccount(String usuario, String contrasena){CustomerDAO.crearCuenta(usuario, contrasena);}

	public void completeAccount(String usuario, String perfil){CustomerDAO.completarCuenta(usuario, perfil);}

	public Customer getPer(String usuario) {return CustomerDAO.getPerfilUsuario(usuario);}

	public boolean checkUser(String usuario){return CustomerDAO.checkUsuario(usuario);};

	//public void includeDescuentos(String usuario, Date descuento){CustomerDAO.includeDescuento(usuario, descuento);};

}
