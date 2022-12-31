package domain;

import java.io.Serializable;
import java.sql.Date;

public class Valoracion implements Serializable {

    String usuario;
    String nombreActividad;
    float nota;
    Date fecha;
    String comentario;

    public Valoracion(String usuario, String nombreActividad,float nota, Date fecha, String comentario)
    {
        this.usuario=usuario;
        this.nombreActividad=nombreActividad;
        this.nota= nota;
        this.fecha=fecha;
        this.comentario=comentario;

    }


    public String getNombreActividad()
    {
        return nombreActividad;
    }
}
