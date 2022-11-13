package ui;
import client.Client;
import domain.Actividad;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import domain.Customer;
/**
 * Clase para visualizar en pantalla la ventana de Actividades
 */
public class VentanaActividades extends JFrame implements ActionListener {

    public String usuario;
    JPanel principal = new JPanel();
    Font font1 = new Font("SansSerif", Font.PLAIN, 15);
    Image fondo= null;
    Color verdoso = new Color(209,242,235);
    Color azul = new Color(168,228,215);

    JScrollPane scrollPane = new JScrollPane();
    JPanel activi = new JPanel();
    JButton[] btnActividades = new JButton[60];
    ArrayList<Actividad> actividades = ObtenActividades();

    JButton deporte = new JButton("DEPORTES");
    JButton fiesta = new JButton("OCIO NOCTURNO");
    JButton gastronomia = new JButton("GASTRONOMIA");
    JButton aventuras = new JButton("AVENTURAS");
    JButton espiritual = new JButton("ESPIRITUAL");
    JButton cultura = new JButton("OCIO CULTURAL");

    public static void main(String[] args) {
        VentanaActividades game = new VentanaActividades("beatrizorbe");
    }

    public VentanaActividades(String usuario) {

        this.usuario=usuario;
        this.setSize(1300, 900);
        principal.setBackground(verdoso);
        ImageIcon im = new ImageIcon("src/main/java/resources/deporte2.png");
        Icon icono = new ImageIcon(im.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));


        //LABEL Categorias
        JLabel categorias = new JLabel();
        categorias.setPreferredSize(new Dimension(900, 80));
        GridLayout grid = new GridLayout(1, 4);
        categorias.setLayout(grid);
        principal.add(categorias, BorderLayout.WEST);

        deporte.setFont(new Font("Rockwell",Font.BOLD,10));
        deporte.setBackground(azul);
        deporte.addActionListener(this);


        fiesta.setFont(new Font("Rockwell",Font.BOLD,10));
        fiesta.setBackground(azul);
        fiesta.addActionListener(this);

        gastronomia.setFont(new Font("Rockwell",Font.BOLD,10));
        gastronomia.setBackground(azul);
        gastronomia.addActionListener(this);

        aventuras.setFont(new Font("Rockwell",Font.BOLD,10));
        aventuras.setBackground(azul);
        aventuras.addActionListener(this);

        espiritual.setFont(new Font("Rockwell",Font.BOLD,10));
        espiritual.setBackground(azul);
        espiritual.addActionListener(this);

        cultura.setFont(new Font("Rockwell",Font.BOLD,10));
        cultura.setBackground(azul);
        cultura.addActionListener(this);


        categorias.add(deporte);
        categorias.add(cultura);
        categorias.add(fiesta);
        categorias.add(gastronomia);
        categorias.add(aventuras);
        categorias.add(espiritual);

        //btn info usuario
        JButton btnInfoUsuario = new JButton();
        btnInfoUsuario.setBorder(new RoundedBorder(15));
        ImageIcon im1 = new ImageIcon("src/main/java/resources/icono.png");
        Icon ic1 = new ImageIcon(im1.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
        btnInfoUsuario.setIcon(ic1);
        btnInfoUsuario.setBackground(new Color(168,228,215));
        principal.add(btnInfoUsuario, BorderLayout.NORTH);


        //OBTENGO PERFIL USUARIO, sus tres categorias favoritas segun el cuestionario rellenado y lo separo para poder trabajr con las categorias.
        String perfil =  ObtenPerfil();

        String[] perfilUsuario = perfil.split(";");
        String perfil1 = perfilUsuario[0];
        String perfil2 = perfilUsuario[1];
        String perfil3 =perfilUsuario[2];


        //Scroll actividades
        scrollPane.setBounds(5,100, 1300, 600);
        GridLayout grid3 = new GridLayout();
        activi.setPreferredSize(new Dimension(700, 1400));
        activi.setBackground(verdoso);


        //CREAMOS EL GRID LAYOUT CORRESPONDIENTE AL TIPO DE PLANES QUE QUIERE EL QUIERE ~ ASOCIADO A SU PERFIL

        scrollPane.add(activi);

        //de su primera favorita categoria ponemos 9 actividades
        //recorremos la base de datos buscando aquellas actividades que correspondan con la categoria buscada
        int j = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {

            if ((Objects.equals(actividades.get(i).getCategoria(), perfil1)) == true) {
                btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                btnActividades[j].setBackground(azul);
                btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                btnActividades[j].setPreferredSize(new Dimension(400, 200));
                btnActividades[j].setBorder(new RoundedBorder(50));
                activi.add(btnActividades[j]);
                btnActividades[j].setFont(font1);
                j++;
            }
        }

        //de su segunda categoria favorita ponemos 6 actividades
        int k = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {

            if ((Objects.equals(actividades.get(i).getCategoria(), perfil2)) == true)
                if (k<6){
                    btnActividades[k] = new JButton(actividades.get(i).getNombre());
                    btnActividades[k].setBackground(azul);
                    btnActividades[k].setPreferredSize(new Dimension(400, 200));
                    btnActividades[k].setFont(new Font("Rockwell",Font.BOLD,40));

                    btnActividades[k].setBorder(new RoundedBorder(50));
                    activi.add(btnActividades[k]);
                    btnActividades[k].setFont(font1);
                    k++;
                }
        }

        //de su tercera categoria favorita ponemos 3 actividades
        int p = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil3)) == true)  {
                if (p<3) {
                    btnActividades[p] = new JButton(actividades.get(i).getNombre());
                    btnActividades[p].setBackground(azul);
                    btnActividades[p].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[p].setPreferredSize(new Dimension(400, 200));
                    btnActividades[p].setBorder(new RoundedBorder(50));
                    activi.add(btnActividades[p]);
                    btnActividades[p].setFont(font1);
                    p++;
                }
            }
        }
        //del resto de categorias que no se ajustan a su perfil no pongo ninguna actividad


        scrollPane.setViewportView(activi);
        this.add(scrollPane);
        this.setForeground(Color.BLUE);
        this.add(principal);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel nuevoScroll = new JPanel();
        if (e.getSource()== deporte)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Deportes")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()== fiesta)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Ocio Nocturno")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()== gastronomia)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Gastronomia")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()== cultura)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Ocio Cultural")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()== aventuras)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Aventuras")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()== espiritual)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Espiritual")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()== gastronomia)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Gastronomía")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(azul);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 200));
                    btnActividades[j].setBorder(new RoundedBorder(50));
                    nuevoScroll.add(btnActividades[j]);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }
        scrollPane.setBounds(5,100, 1300, 600);
        nuevoScroll.setPreferredSize(new Dimension(700, 700));
        nuevoScroll.setBackground(verdoso);
        scrollPane.setViewportView(nuevoScroll);

    }



    //En este metodo intento hacer los botones redondos
    class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    //Obtengo las actividades disponibles de la base de datos
    public ArrayList<Actividad> ObtenActividades() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getActividades";
        session = cliente.sentMessage(context, session);
        ArrayList<Actividad> actividades= (ArrayList<Actividad>) session.get("Actividades");
        return actividades;
    }

    //Obtengo perfil del usuario (tres categorias de preferencia)
    public String ObtenPerfil()  {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getPerfil";
        session.put("usuario",this.usuario);
        session=cliente.sentMessage(context,session);
        Customer cu=(Customer)session.get("Customer");
        return cu.getPerfil();
    }

}




