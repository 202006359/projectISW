package domain;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

/**
 *	Clase para definir el objeto Actividad
 */

public class Actividad implements Serializable{

    private String nombre;
    private String categoria;
    private String descripcion;
    private String ubicacion;

    private float precio;
    private float descuento;

    private Image imagen;

    private static final long serialVersionUID = 1L;

    public Actividad(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }
    public Actividad(String nombre, String categoria, Image imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Actividad(String nombre, String categoria, Image imagen, float precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.imagen = imagen;
        this.precio = precio;
    }
    public Actividad(String nombre, String valor, boolean tipo) { //Si tipo = true, tengo descripcion. Si tipo = false, tengo ubicacion
        this.nombre = nombre;
        if (tipo)
            this.descripcion = valor;
        else
            this.ubicacion = valor;

    }

    public Actividad(String nombre, Image imagen){
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Actividad(String nombre, float descuento){
        this.nombre = nombre;
        this.descuento = descuento;
    }

    public Actividad(String nombre, int descuento)
    {
        this.nombre = nombre;
        this.descuento= descuento;
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
    public Image getImagen(){return imagen;}
    public Float getPrecio(){return precio;}
    public Float getDescuento(){return descuento;}

}
