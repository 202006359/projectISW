package ui;

import client.Client;
import com.sendemail.SendEmailThroughGmail;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

/**
 * Clase para visualizar en pantalla la ventana de Gestionar la fecha y hora de la reserva
 */

public class VentanaGestionReserva extends JFrame {

    public static void main(String[] args) {
        new VentanaGestionReserva("","");
    }

    String usuario;
    String nombreActividad;
    Date fechaReserva;
    Time horaReserva;



    public VentanaGestionReserva(String usuario, String nombreActividad){
        this.usuario = usuario;
        this.nombreActividad = nombreActividad;

        PanelGestionReserva pnlReserva = new PanelGestionReserva();
        this.add(pnlReserva);



        JLabel lblIn = new JLabel("Reserve su actividad");
        lblIn.setBounds(265,125,500,50);
        lblIn.setFont(new Font("Serif", Font.PLAIN, 40));
        lblIn.setForeground(new Color(30,46,64));
        pnlReserva.add(lblIn);

        JButton btnReserva = new JButton("Reservar");
        btnReserva.setBounds(200,445,600,40);
        btnReserva.setForeground(Color.WHITE);
        btnReserva.setFont(new Font("Calibri", Font.BOLD, 16));
        btnReserva.setBackground(new Color(30,46,64));
        pnlReserva.add(btnReserva);

        JLabel lblUsr = new JLabel("Fecha de la reserva (Formato: yyyy-mm-dd)");
        lblUsr.setBounds(200,290,800,30);
        lblUsr.setFont(new Font("Serif", Font.BOLD, 16));
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(200,320,600,30);
        txtUsr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtUsr.setText("2023-01-10");

        JLabel lblPassword= new JLabel("Hora de la reserva (Formato: hh-mm-ss)");
        lblPassword.setBounds(200,360,800,30);
        lblPassword.setFont(new Font("Serif", Font.BOLD, 16));
        JTextField txtPassword = new JTextField(20);
        txtPassword.setBounds(200,390,600,30);
        txtPassword.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtPassword.setText("18-30-00");//Valor por defecto para pruebas


        txtUsr.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){ //Para ocultar el texto al pinchar en el JTextField
                if(txtUsr.getText().equals("2023-01-10"))
                    txtUsr.setText("");

            }
        });
        txtUsr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(txtUsr.getText().equals("2023-01-10"))
                    txtUsr.setText("");
            }
        });
        txtPassword.addMouseListener(new MouseAdapter() { //Para ocultar el texto al pinchar en el JTextField
            @Override
            public void mouseClicked(MouseEvent e) {

                if(String.valueOf(txtPassword.getText()).equals("18-30-00")){
                    txtPassword.setText("");
                }
            }
        });

        String finalUsuario = usuario;
        String finalNombreActividad = nombreActividad;

        btnReserva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fechaReserva = obtenerFecha(txtUsr.getText());
                horaReserva = obtenerHora(txtPassword.getText());
                guardarReserva(finalUsuario, finalNombreActividad,fechaReserva,horaReserva);
                mandarCorreo(finalUsuario,finalNombreActividad);
                salirPrincipal();
            }
        });

        pnlReserva.add(lblUsr);
        pnlReserva.add(txtUsr);
        pnlReserva.add(lblPassword);
        pnlReserva.add(txtPassword);





        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Date obtenerFecha(String strFecha) {

        int anio;
        int mes;
        int dia;

        anio = Integer.parseInt(strFecha.substring(0,4));
        mes = Integer.parseInt(strFecha.substring(5,7));
        dia = Integer.parseInt(strFecha.substring(8,10));


        return new Date(anio,mes,dia);
    }

    public Time obtenerHora(String strHora) {

        int hora;
        int minuto;
        int segundo;

        hora = Integer.parseInt(strHora.substring(0,2));
        minuto = Integer.parseInt(strHora.substring(3,5));
        segundo = Integer.parseInt(strHora.substring(6,8));

        return new Time(hora,minuto,segundo);
    }

    public void guardarReserva(String usuario, String nombreActividad, Date fechaReserva, Time horaReserva) { //Metodo para guardar en la base de datos la reserva

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



    public void salirPrincipal() { //Me voy a la ventana de actividades
        this.exit();
        new VentanaActividades(usuario);
    }

    private void exit(){
        this.dispose();
        this.setVisible(false);
    }

}
