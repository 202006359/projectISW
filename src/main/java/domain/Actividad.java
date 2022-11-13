package domain;

import java.io.Serializable;

/**
 *	Clase para definir el objeto Actividad
 */

public class Actividad implements Serializable{

    private String nombre;
    private String categoria;
    private String descripcion;
    private String ubicacion;

    private static final long serialVersionUID = 1L;


    public Actividad(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }
    public Actividad(String nombre, String valor, boolean tipo) { //Si tipo = true, tengo descripcion. Si tipo = false, tengo ubicacion
        this.nombre = nombre;
        if (tipo)
            this.descripcion = valor;
        else
            this.ubicacion = valor;

    }

    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public String getDescripcion() {
        return descripcion;
    }


}
