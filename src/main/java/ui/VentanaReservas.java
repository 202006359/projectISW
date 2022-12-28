package ui;

import client.Client;
import com.sendemail.SendEmailThroughGmail;
import domain.Actividad;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.sql.Date;
import java.util.HashMap;

public class VentanaReservas extends JFrame {
    public static void main(String[] args) {
        new VentanaReservas("","",null);
    }
    String nombreActividad;
    Image imagen;
    String descripcion;
    String ubicacion;
    String usuario;

    Date fechaReserva; // = new Date(2022-1900,11,28);
    Time horaReserva; // = new Time(20,00,00));

    public VentanaReservas(String usuario, String nombreActividad, Image imagen){

        this.usuario = usuario;
        this.nombreActividad = nombreActividad;
        this.imagen = imagen;

        descripcion = leerDescripcion("PANDA CLUB");
        ubicacion = leerUbicacion("PANDA CLUB");

        //COMO SE GUARDA UNA RESERVA-> Importante que los formatos de la fecha y la reserva
        usuario = "admin@gmail.com";
        nombreActividad = "PANDA CLUB";
        fechaReserva = new Date(2022-1900,11,28); //Esta info la obtendrá del boton que cree Jaime
        horaReserva = new Time(20,00,00); //Esta info la obtendrá del boton que cree Jaime
        this.guardarReserva(usuario, nombreActividad, fechaReserva, horaReserva);
        //FIN GUARDAR RESERVA

        //COMO SE MANDA UN CORREO
        usuario = "a@gmail.com";
        nombreActividad = "PANDA CLUB";
        this.mandarCorreo(usuario, nombreActividad);
        //FIN MADAR CORREO

        this.setPreferredSize(new Dimension(700,700)); //Se puede cambiar

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public String leerDescripcion(String nombreActividad) { //Metodo utilizado para obtener de la base de datos la descripcion del plan
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getDescription";
        session.put("nombre",nombreActividad);
        session=cliente.sentMessage(context,session);
        Actividad act=(Actividad) session.get("Actividad");
        return act.getDescripcion();
    }
    public String leerUbicacion(String nombreActividad) { //Metodo utilizado para obtener de la base de datos la ubicacion del plan
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getLocation";
        session.put("nombre",nombreActividad);
        session=cliente.sentMessage(context,session);
        Actividad act=(Actividad) session.get("Actividad");
        return act.getUbicacion();
    }


    public void guardarReserva(String usuario,String nombreActividad,Date fechaReserva, Time horaReserva) { //Metodo para guardar en la base de datos la reserva

        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/createReservation";
        session.put("usuario",usuario);
        session.put("plan", nombreActividad);
        session.put("fecha", fechaReserva);
        session.put("hora", horaReserva);
        cliente.sentMessage(context,session);

    }


    public void mandarCorreo(String usuario, String nombreActividad){ //Metodo utilizado para mandar el correo de confirmación
        new SendEmailThroughGmail(usuario, "Reserva Completada", "Buenas,\n\n Le comunicamos que tu reserva en " + nombreActividad + " se ha realizado correctamente.");
    }




}


