package ui;
import client.Client;
import domain.Valoracion;

import java.awt.*;
import javax.swing.*;
import java.sql.Time;
import java.util.*;
import java.awt.event.*;
public class PanelValoracion extends JFrame implements ActionListener{

    String nombreActividad;
    String usuario;
    int val;
    String coment;
    float nota;
    Date fecha;
    public PanelValoracion(String usuario, String nombreActividad){

        this.usuario= usuario;
        this.nombreActividad = nombreActividad;

        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    public void init(){
        Font font1 = new Font("SansSerif", Font.PLAIN, 15);
        Color azull = new Color(84, 153, 199);

        JPanel pnlReview = new JPanel(new BorderLayout());
        JPanel pnlCentral = new JPanel(new GridLayout(4,1));

        JPanel pnlName = new JPanel(new FlowLayout());
        JPanel pnlVal = new JPanel(new FlowLayout());
        JPanel pnlComent = new JPanel(new FlowLayout());
        JPanel pnlCerrar = new JPanel(new FlowLayout());

        JLabel name = new JLabel(nombreActividad);
        pnlName.add(name);


        //valoracion
        JLabel valoracion= new JLabel("Valoracion(1/5): ");
        JFormattedTextField  txtValoracion= new JFormattedTextField(10);
        //nota = (float)  txtValoracion.getValue();
        pnlComent.add(valoracion);
        pnlComent.add( txtValoracion);

        //comentario

        JLabel comentario = new JLabel("Comentario: ");
        JTextField txtComentario = new JTextField(3);
        coment = txtComentario.getText();
        pnlVal.add(comentario);
        pnlVal.add(txtComentario);


        JButton btnCerrar = new JButton("Guardar Valoracion");
        pnlCerrar.add(btnCerrar);
        btnCerrar.setFont(font1);
        btnCerrar.setBackground(azull);

        //fecha
       fecha = new Date();

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Valoracion(usuario, nombreActividad, 10 , fecha, coment );
                guardarValoracion();
            }
        });

        pnlCentral.add(pnlName);
        pnlCentral.add(pnlVal);
        pnlCentral.add(pnlComent);
        pnlReview.add(pnlCentral, BorderLayout.CENTER);
        pnlReview.add(pnlCerrar, BorderLayout.SOUTH);
        add(pnlReview);

    }

    public void guardarValoracion()
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

    @Override
    public void actionPerformed(ActionEvent e){
        //guardarValoracion
    }
}
