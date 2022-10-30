package main.domain;

import java.io.Serializable;

public class Actividad implements Serializable{
    /**
     *
     */
    //private static final long serialVersionUID = 1L;
    private String nombre;
    private String localizacion;

    public Actividad(String nombre, String localizacion) {
     this.setNombre(nombre);
     this.setLocalizacion(localizacion);
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }


    public String getUsuario() {
        return nombre;
    }
    public void setLocalizacion(String localizacion) {
        this.localizacion=localizacion;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}
