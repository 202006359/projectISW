package main.domain;

import java.io.Serializable;

public class Actividad implements Serializable{

    private String nombre;
    private String categoria;


    public Actividad(String nombre) {
     this.setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}
