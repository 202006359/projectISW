package domain;

import java.awt.*;
import java.io.Serializable;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

/**
 *	Clase para definir el objeto Actividad
 */

public class Oferta implements Serializable {
    private String nombre;

    private String descripcion;
    private float descuento;

    private Image imagen;

    private static final long serialVersionUID = 1L;

    public Oferta(String nombre, String descripcion, Float descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
    }


    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getDescuento() {
        return descripcion;
    }


}
