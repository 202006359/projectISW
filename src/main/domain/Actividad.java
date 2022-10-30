package main.domain;

import java.io.Serializable;

public class Actividad implements Serializable{

    private String nombre;
    private String categoria;



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
