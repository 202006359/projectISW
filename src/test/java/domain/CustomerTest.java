package domain;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

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
    }
    @org.junit.Test
    public void getContrasena() { //Verifico contrasena
        assertEquals("IvanPass", customer.getContrasena());
    }
    @org.junit.Test
    public void getPerfil() { //Verifico el perfil
        assertEquals("Aventurero", customer.getPerfil());
    }

}