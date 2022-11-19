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
        new VentanaReservas("");
    }
    String nombre;

    public VentanaReservas(String nombre){

        this.nombre = nombre;

        this.setPreferredSize(new Dimension(700,700));

        //System.out.println(leerDescripcion("PANDA CLUB"));
        System.out.println(leerUbicacion("PANDA CLUB"));

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


