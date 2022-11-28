package ui;

import client.Client;
import domain.Actividad;
import domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VentanaReservas extends JFrame {
    public static void main(String[] args) {
        new VentanaReservas("",null);
    }
    String nombre;
    Image imagen;
    String descripcion;
    String ubicacion;

    public VentanaReservas(String nombre, Image imagen){

        this.nombre = nombre;
        this.imagen = imagen;
        descripcion = leerDescripcion("PANDA CLUB");
        ubicacion = leerUbicacion("PANDA CLUB");

        this.setPreferredSize(new Dimension(700,700)); //Se puede cambiar

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public String leerDescripcion(String nombre) { //Metodo utilizado para obtener de la base de datos la descripcion del plan
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getDescription";
        session.put("nombre",nombre);
        session=cliente.sentMessage(context,session);
        Actividad act=(Actividad) session.get("Actividad");
        return act.getDescripcion();
    }
    public String leerUbicacion(String nombre) { //Metodo utilizado para obtener de la base de datos la ubicacion del plan
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getLocation";
        session.put("nombre",nombre);
        session=cliente.sentMessage(context,session);
        Actividad act=(Actividad) session.get("Actividad");
        return act.getUbicacion();
    }




}


