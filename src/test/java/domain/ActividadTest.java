package domain;

import static org.junit.Assert.assertEquals;

public class ActividadTest {
    /**
     * Clase para verificar el correcto funcionamiento de los metodos de la clase Actividad
     */
    private Actividad actividad;
    @org.junit.Before
    public void setUp() throws Exception { //Me creo mi Actividad
        actividad = new Actividad("Discoteca Liberty", "Ocio Nocturno");
    }
    @org.junit.Test
    public void getNombre() { ////Verifico el nombre del plan
        assertEquals("Discoteca Liberty", actividad.getNombre());
    }
    @org.junit.Test
    public void getCategoria() { //Verifico la categoria del plan
        assertEquals("Ocio Nocturno", actividad.getCategoria());
    }
}