package test.java;

import main.domain.Actividad;

import static org.junit.Assert.assertEquals;

public class ActividadTest {
    /**
     * Clase para verificar el correcto funcionamiento de los metodos de la clase Actividad
     */
    private Actividad actividad;
    @org.junit.Before
    public void setUp() throws Exception { //Me creo mi Customer
        actividad = new Actividad("Discoteca Liberty", "Ocio Nocturno");
    }
    @org.junit.Test
    public void getNombre() { //Verifico usuario
        assertEquals("Discoteca Liberty", actividad.getNombre());
    } //Verifico el nombre del plan
    @org.junit.Test
    public void getCategoria() { //Verifico la categoria del plan
        assertEquals("Ocio Nocturno", actividad.getCategoria());
    }


}
