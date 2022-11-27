package domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


/**
 *	Clase para definir el objeto Reserva
 */

public class Reserva {
    private String nombrePlan;
    private Date fecha;
    private Time hora;
    private ArrayList<Customer> usuarios = new ArrayList<>();



    public Reserva(String nombrePlan, Date fecha, Time hora, Customer usuario) {
        this.nombrePlan = nombrePlan;
        this.fecha = fecha;
        this.hora = hora;
        usuarios.add(usuario);
    }

    public void addUsuario(Customer usuario){
        usuarios.add(usuario);
    }
    public void eliminarUsuario(Customer usuario){
        usuarios.remove(usuario);
    }


    public String getNombrePlan() {
        return nombrePlan;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public ArrayList<Customer> getUsuarios() {
        return usuarios;
    }
}
