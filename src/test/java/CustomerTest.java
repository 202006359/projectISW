package test.java;

import main.domain.Customer;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Test
public class CustomerTest implements Serializable{
	/**
	 * Clase para verificar el correcto funcionamiento de los metodos de la clase Customer
	 */
	private Customer customer;
	@org.junit.Before
	public void setUp() throws Exception { //Me creo mi Customer
		customer=new Customer("Ivan", "IvanPass", "Aventurero");
	}
	@org.junit.Test
	public void getUsuario() { //Verifico usuario
		assertEquals("Ivan", customer.getUsuario());
	} //Verifico el usuario
	@org.junit.Test
	public void getContrasena() { //Verifico contrasena
		assertEquals("IvanPass", customer.getContrasena());
	} //Verifico la contrasena
	@org.junit.Test
	public void getPerfil() { //Verifico el perfil
		assertEquals("Aventurero", customer.getPerfil());
	} //Verifico el perfil

	
}
