package domain;

import java.io.Serializable;

/**
 *	Clase para definir el objeto Actividad
 */

public class Actividad implements Serializable{

    private String nombre;
    private String categoria;
    private static final long serialVersionUID = 1L;


    public Actividad(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
        return categoria;
    }


}
