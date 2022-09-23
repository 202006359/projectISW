package icai.dtc.isw.controler;

import java.util.ArrayList;

import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.Customer;

public class CustomerControler {

	public void getCustomers(ArrayList<Customer> lista) {
		CustomerDAO.getClientes(lista);
	}
	public Customer getCustomer(int id) {return(CustomerDAO.getCliente(id));}

	public Customer checkPassword(String usuario, String contrasena) {return CustomerDAO.checkContrasena(usuario, contrasena);}
}
