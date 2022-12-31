package ui;
import client.Client;
import domain.Valoracion;

import java.awt.*;
import javax.swing.*;
import java.sql.Time;
import java.util.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class VentanaValoracion extends JFrame{

    public static void main(String[] args) {
        new VentanaValoracion("","");
    }
    String nombreActividad;
    String usuario;
    int val;
    Image imagen;
    String coment;
    float nota;
    Date fecha;
    public VentanaValoracion(String usuario, String nombreActividad){

        this.usuario= usuario;
        this.nombreActividad = nombreActividad;


        JButton btnGuardarValoracion = new JButton("Guardar Valoracion");
        btnGuardarValoracion.setForeground(Color.WHITE);
        btnGuardarValoracion.setFont(new Font("Calibri", Font.BOLD, 16));
        btnGuardarValoracion.setBackground(new Color(30, 46, 64));

        JPanel pnlReview = new JPanel(new BorderLayout());
        JPanel pnlCentral = new JPanel((new GridLayout(4,1)));
        JPanel pnlName = new JPanel(new FlowLayout());
        JPanel pnlVal = new JPanel(new FlowLayout());
        JPanel pnlComent = new JPanel(new FlowLayout());
        JPanel pnlCerrar = new JPanel(new FlowLayout());

        //nombre actividad
        JLabel name = new JLabel(nombreActividad);
        name.setFont(new Font("Serif", Font.PLAIN, 40));
        name.setForeground(new Color(30,46,64));
        pnlName.add(name);

        //valoracion
        JLabel valoracion= new JLabel("Valoracion(1/5): ");
        valoracion.setFont(new Font("Serif", Font.BOLD, 16));
        JFormattedTextField  txtValoracion= new JFormattedTextField(10);
        //nota = (float)  txtValoracion.getValue();
        pnlComent.add(valoracion);
        pnlComent.add(txtValoracion);

        //comentario

        JLabel comentario = new JLabel("Comentario: ");
        comentario.setFont(new Font("Serif", Font.BOLD, 16));

        JTextField txtComentario = new JTextField(10);
        coment = txtComentario.getText();
        pnlVal.add(comentario);
        pnlVal.add(txtComentario);

        //fecha
        long millis = System.currentTimeMillis();
        java.sql.Date fecha = new java.sql.Date(millis);


        btnGuardarValoracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Valoracion(usuario, nombreActividad, nota, fecha, coment);
                //guardarValoracion(); guarda la valoracion hecha por el usuario
                salirPrincipal();
            }
        });



        pnlCentral.add(pnlName);
        pnlCentral.add(pnlVal);
        pnlCentral.add(pnlComent);
        pnlReview.add(pnlCentral, BorderLayout.CENTER);
        pnlReview.add(pnlCerrar, BorderLayout.SOUTH);
        pnlCerrar.add(btnGuardarValoracion);
        add(pnlReview);


        this.pack();
        this.setVisible(true);
        this.setSize(700,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void guardarValoracion() //guardar valoracion y comentario del usuario
    {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/createValoracion";
        session.put("usuario",usuario);
        session.put("plan", nombreActividad);
        session.put("nota", nota);
        session.put("fecha", fecha);
        session.put("Comentario", coment);
        cliente.sentMessage(context,session);

    }

    public void salirPrincipal(){ //me voy a la ventana de actividades
        this.exit();
        new VentanaActividades(usuario);
    }

    public void exit(){
        this.dispose();
        this.setVisible(false);
    }

}
