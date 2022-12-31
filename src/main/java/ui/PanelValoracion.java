package ui;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class PanelValoracion extends JFrame implements ActionListener{

    String nombreActividad;
    String usuario;
    int valoracion;
    String comentario;
    public PanelValoracion(String usuario, String nombreActividad, int valoracion, String comentario){

        this.usuario= usuario;
        this.nombreActividad = nombreActividad;
        this.valoracion = valoracion;
        this.comentario = comentario;

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

        JLabel valoracion = new JLabel("Valoracion(1/5): ");
        JTextField txtValoracion = new JTextField(3);
        pnlVal.add(valoracion);
        pnlVal.add(txtValoracion);

        JLabel comentario = new JLabel("Comentario: ");
        JTextField txtComentario = new JTextField(30);
        pnlComent.add(comentario);
        pnlComent.add(txtComentario);

        JButton btnCerrar = new JButton("Guardar Valoracion");
        pnlCerrar.add(btnCerrar);
        btnCerrar.setFont(font1);
        btnCerrar.setBackground(azull);
        btnCerrar.addActionListener(this);

        pnlCentral.add(pnlName);
        pnlCentral.add(pnlVal);
        pnlCentral.add(pnlComent);
        pnlReview.add(pnlCentral, BorderLayout.CENTER);
        pnlReview.add(pnlCerrar, BorderLayout.SOUTH);
        add(pnlReview);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        //guardarValoracion
    }
}
