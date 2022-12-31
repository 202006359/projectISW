package ui;

import client.Client;
import com.sendemail.SendEmailThroughGmail;
import domain.Actividad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Date;
import java.util.HashMap;

public class VentanaReservas extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new VentanaReservas("","",null);
    }
    String nombreActividad;
    Image imagen;
    String descripcion;
    String ubicacion;
    String usuario;
    int valoracion;
    String comentario;

    Date fechaReserva; // = new Date(2022-1900,11,28);
    Time horaReserva; // = new Time(20,00,00));

    Font font1= new Font("SansSerif", Font.PLAIN, 15);

    Color azull = new Color(84, 153, 199);

    JPanel panelSuperior;
    JPanel panelMedio;
    JPanel panelMedioDos;
    JPanel panelInferior;
    JPanel panelInferiorDos;

    JLabel nombreLabel;
    JLabel descripcionLabel;
    JLabel fotoLabel;
    JLabel ubicacionLabel;

    JButton btnReservarPlan = new JButton("Reservar");
    JButton btnValorarPlan = new JButton("Valorar");




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
        //this.guardarReserva(usuario, nombreActividad, fechaReserva, horaReserva);
        //FIN GUARDAR RESERVA

        //COMO SE MANDA UN CORREO
        usuario = "a@gmail.com";
        nombreActividad = "PANDA CLUB";
        //this.mandarCorreo(usuario, nombreActividad);
        //FIN MADAR CORREO

        btnReservarPlan.setFont(font1);
        btnReservarPlan.setBackground(azull);
        btnReservarPlan.addActionListener(e->{
            this.reservar();
        });

        btnValorarPlan.setFont(font1);
        btnValorarPlan.setBackground(azull);
        btnValorarPlan.addActionListener(this); //¿Modificar?

        construyePanelSuperior();
        construyePanelMedio();
        construyePanelMedioDos();
        construyePanelInferior();
        construyePanelInferiorDos();
        construyeVentana();

        this.setPreferredSize(new Dimension(700,700)); //Se puede cambiar

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void reservar() { //Me voy a la ventana de Registro
        this.exit();
        new VentanaGestionReserva(usuario,nombreActividad);
    }

    private void exit(){
        this.dispose();
        this.setVisible(false);
    }


    public void construyePanelSuperior(){
        panelSuperior = new JPanel();
        nombreLabel = new JLabel(nombreActividad);
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        panelSuperior.add(nombreLabel);
    }

    public void construyePanelMedio(){
        panelMedio = new JPanel();
        descripcionLabel = new JLabel(leerDescripcion(nombreActividad));
        panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.X_AXIS));
        panelMedio.add(descripcionLabel);
    }
    public void construyePanelMedioDos(){
        panelMedioDos = new JPanel();
        fotoLabel = new JLabel(new ImageIcon(imagen));
        panelMedioDos.setLayout(new BoxLayout(panelMedioDos, BoxLayout.X_AXIS));
        panelMedioDos.add(fotoLabel);
    }

    public void construyePanelInferior(){
        panelInferior = new JPanel();
        ubicacionLabel = new JLabel(leerUbicacion(nombreActividad));
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.X_AXIS));
        panelInferior.add(ubicacionLabel);
    }

    public void construyePanelInferiorDos(){
        panelInferiorDos = new JPanel();
        panelInferiorDos.setLayout(new BoxLayout(panelInferiorDos, BoxLayout.X_AXIS));
        panelInferiorDos.add(btnReservarPlan);
        panelInferiorDos.add(btnValorarPlan);
    }

    public void construyeVentana(){
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(panelSuperior);
        this.add(panelMedio);
        this.add(panelMedioDos);
        this.add(panelInferior);
        this.add(panelInferiorDos);
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
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getLocation";
        session.put("nombre", nombreActividad);
        session = cliente.sentMessage(context, session);
        Actividad act = (Actividad) session.get("Actividad");
        return act.getUbicacion();
    }



    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == btnValorarPlan){
            PanelValoracion create = new PanelValoracion(usuario, nombreActividad);
        }
    }


}


