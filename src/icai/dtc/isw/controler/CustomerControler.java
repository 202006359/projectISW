package icai.dtc.isw.controler;

import java.util.ArrayList;

import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.*;

public class CustomerControler {

	public void getCustomers(ArrayList<Customer> lista) {
		CustomerDAO.getClientes(lista);
	}
	public Customer getCustomer(int id) {return(CustomerDAO.getCliente(id));}

	public Customer getPassword(String usuario) {return CustomerDAO.getContrasena(usuario);}

	public void createAccount(String usuario, String contrasena){CustomerDAO.crearCuenta(usuario, contrasena);}

	//public ArrayList<Actividad> getActividades(){CustomerDAO.getActividades(); return CustomerDAO.getActividades();}
}
