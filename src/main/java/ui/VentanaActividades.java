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

    Color oscuro = new Color(40,55, 71);
    Color amarillo = new Color(247,220,111);
    Color azull= new Color(84,153,199);
    Color amarilloo = new Color(249,231,159);

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
    JButton btnInfoUsuario = new JButton();

    ImageIcon im;
    ImageIcon im1;
    ImageIcon im2;
    ImageIcon im3;
    ImageIcon im4;
    ImageIcon im6;
    Icon ic1;
    Icon ic0;
    Icon ic2;
    Icon ic3;
    Icon ic4;
    Icon ic6;




    public static void main(String[] args) {
        VentanaActividades game = new VentanaActividades("admin@gmail.com");
   }

    public VentanaActividades(String usuario) {

        this.usuario=usuario;
        this.setSize(1300, 900);
        principal.setBackground(oscuro);


        //label BIenvenida
        JLabel bienvenida = new JLabel("                              BIENVENIDO A TU PAGINA PERSONAL DE SMARTPLAN");
        bienvenida.setPreferredSize(new Dimension(700,100));
        bienvenida.setForeground(Color.WHITE);
        bienvenida.setFont(font1);

        bienvenida.setPreferredSize(new Dimension(700,50));
        principal.add(bienvenida,BorderLayout.NORTH);

        //Imagen deportes
        im = new ImageIcon("src/main/java/resources/deporte.png");
        ic0 = new ImageIcon(im.getImage().getScaledInstance(200,200, 5));

        im1 = new ImageIcon("src/main/java/resources/cultura.png");
        ic1 = new ImageIcon(im1.getImage().getScaledInstance(200,200, 5));

        im2 = new ImageIcon("src/main/java/resources/espiritual.png");
        ic2 = new ImageIcon(im2.getImage().getScaledInstance(200,200, 1));

        im3 = new ImageIcon("src/main/java/resources/moneda.png");
        ic3= new ImageIcon(im3.getImage().getScaledInstance(200,200,5));

        im4 = new ImageIcon("src/main/java/resources/gastronomia.png");
        ic4 = new ImageIcon(im4.getImage().getScaledInstance(200,200, 5));

        im6 = new ImageIcon("src/main/java/resources/fiesta.png");
        ic6 = new ImageIcon(im6.getImage().getScaledInstance(200,200, 5));


        //LABEL Categorias
        JLabel categorias = new JLabel();
        categorias.setPreferredSize(new Dimension(900, 80));
        GridLayout grid = new GridLayout(1, 4,20,20);
        categorias.setLayout(grid);
        principal.add(categorias, BorderLayout.WEST);

        deporte.setFont(new Font("Rockwell",Font.BOLD,10));
        deporte.setBackground(amarillo);
        deporte.addActionListener(this);


        fiesta.setFont(new Font("Rockwell",Font.BOLD,10));
        fiesta.setBackground(amarillo);
        fiesta.addActionListener(this);

        gastronomia.setFont(new Font("Rockwell",Font.BOLD,10));
        gastronomia.setBackground(amarillo);
        gastronomia.addActionListener(this);

        aventuras.setFont(new Font("Rockwell",Font.BOLD,10));
        aventuras.setBackground(amarillo);
        aventuras.addActionListener(this);

        espiritual.setFont(new Font("Rockwell",Font.BOLD,10));
        espiritual.setBackground(amarillo);
        espiritual.addActionListener(this);

        cultura.setFont(new Font("Rockwell",Font.BOLD,10));
        cultura.setBackground(amarillo);
        cultura.addActionListener(this);


        categorias.add(deporte);
        categorias.add(cultura);
        categorias.add(fiesta);
        categorias.add(gastronomia);
        categorias.add(aventuras);
        categorias.add(espiritual);

        //btn info usuario

        ImageIcon imm = new ImageIcon("src/main/java/resources/icono.png");
        Icon icc = new ImageIcon(imm.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
        btnInfoUsuario.setIcon(icc);
        btnInfoUsuario.setBackground(amarillo);
        principal.add(btnInfoUsuario, BorderLayout.NORTH);
        btnInfoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(activi);
            }
        });


        //OBTENGO PERFIL USUARIO, sus tres categorias favoritas segun el cuestionario rellenado y lo separo para poder trabajr con las categorias.
        String perfil =  ObtenPerfil();

        String[] perfilUsuario = perfil.split(";");
        String perfil1 = perfilUsuario[0];

        String perfil2 = perfilUsuario[1];
        String perfil3 =perfilUsuario[2];


        //Scroll actividades
        scrollPane.setBounds(5,150, 1300, 600);
        GridLayout grid3 = new GridLayout(10,3,20,20    );
        activi.setPreferredSize(new Dimension(700, 2000));
        activi.setBackground(amarilloo);
        //activi.add(icn);
        //CREAMOS EL GRID LAYOUT CORRESPONDIENTE AL TIPO DE PLANES QUE QUIERE EL QUIERE ~ ASOCIADO A SU PERFIL
        scrollPane.add(activi);

        //de su primera favorita categoria ponemos 9 actividades
        //recorremos la base de datos buscando aquellas actividades que correspondan con la categoria buscada
        int j = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil1)) == true) {
                btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                btnActividades[j].setBackground(amarillo);
                btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                btnActividades[j].setPreferredSize(new Dimension(400, 100));
                String nombre = actividades.get(i).getNombre();
                btnActividades[j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new VentanaReservas(nombre);
                    }
                });
                JPanel aux = new JPanel();
                aux.setPreferredSize(new Dimension(400,300));
                aux.add(btnActividades[j]);
                if (perfil1.compareTo("Deporte")==0){ JButton X = new JButton(); X.setIcon(ic0); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                else if (perfil1.compareTo("Ocio Cultural")==0) { JButton X= new JButton();  X.setIcon(ic1); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                else if (perfil1.compareTo("Espiritual")==0){JButton X= new JButton();  X.setIcon(ic2); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                else if (perfil1.compareTo("Aventuras")==0){JButton X= new JButton();  X.setIcon(ic3); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                else if (perfil1.compareTo("Gastronomia")==0){ JButton X= new JButton();  X.setIcon(ic4); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                else if (perfil1.compareTo("Ocio Nocturno")==0){ JButton X= new JButton();  X.setIcon(ic6); aux.add(X); X.setPreferredSize(new Dimension(400,200));}

                //aux.add(new JButton(im2));
                //btnActividades[j].add(icn);
                aux.revalidate();
                activi.add(aux);
                btnActividades[j].setFont(font1);
                j++;
            }
        }
        //de su segunda categoria favorita ponemos 6 actividades
        int k = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            JPanel aux = new JPanel();
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil2)) == true)
                if (k<6){
                    btnActividades[k] = new JButton(actividades.get(i).getNombre());
                    btnActividades[k].setBackground(amarillo);
                    btnActividades[k].setPreferredSize(new Dimension(400, 100));
                    btnActividades[k].setFont(new Font("Rockwell",Font.BOLD,40));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[k].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });

                    aux.setPreferredSize(new Dimension(400,300));
                    aux.setBackground(amarillo);
                    aux.add(btnActividades[k]);
                    if (perfil1.compareTo("Deporte")==0){ JButton X = new JButton(); X.setIcon(ic0); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil2.compareTo("Ocio Cultural")==0) { JButton X= new JButton();  X.setIcon(ic1); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil2.compareTo("Espiritual")==0){JButton X= new JButton();  X.setIcon(ic2); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil2.compareTo("Aventuras")==0){JButton X= new JButton();  X.setIcon(ic3); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil2.compareTo("Gastronomia")==0){ JButton X= new JButton();  X.setIcon(ic4); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil2.compareTo("Ocio Nocturno")==0){ JButton X= new JButton();  X.setIcon(ic6); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    activi.add(aux);
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
                    btnActividades[p].setBackground(amarillo);
                    btnActividades[p].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[p].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[p].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[p]);
                    if (perfil1.compareTo("Deporte")==0){ JButton X = new JButton(); X.setIcon(ic0); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil3.compareTo("Ocio Cultural")==0) { JButton X= new JButton();  X.setIcon(ic1); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil3.compareTo("Espiritual")==0){JButton X= new JButton();  X.setIcon(ic2); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil3.compareTo("Aventuras")==0){JButton X= new JButton();  X.setIcon(ic3); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil3.compareTo("Gastronomia")==0){ JButton X= new JButton();  X.setIcon(ic4); aux.add(X); X.setPreferredSize(new Dimension(400,200));}
                    else if (perfil3.compareTo("Ocio Nocturno")==0){ JButton X= new JButton();  X.setIcon(ic6); aux.add(X); X.setPreferredSize(new Dimension(400,200));}

                    activi.add(aux);
                    btnActividades[p].setFont(font1);
                    p++;
                }
            }
        }
        //del resto de categorias que no se ajustan a su perfil no pongo ninguna actividad

        scrollPane.setViewportView(activi);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        JLabel FINAL = new JLabel("PULSA PARA VER EL DETALLE DE CADA ACTIVIDAD");
        FINAL.setFont(font1);
        FINAL.setForeground(Color.WHITE);
        FINAL.setBounds(350, 9000, 600,50);


        //Btn actividades mas afines
       /* BtnAfines.setFont(font1);
        BtnAfines.setBackground(azul);
        BtnAfines.setPreferredSize(new Dimension(400,100));
        BtnAfines.addActionListener(this);
        principal.add(BtnAfines, BorderLayout.SOUTH);*/


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
        nuevoScroll.setPreferredSize(new Dimension(700,1500));
        if (e.getSource()== deporte)
        {
            int j = 0;
            for (int i = 0; i < actividades.toArray().length; i++) {

                if ((Objects.equals(actividades.get(i).getCategoria(),"Deportes")) == true) {
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(ic0);
                    aux.add(X);
                    //activi.add(aux);
                    nuevoScroll.add(aux);
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
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(ic6);
                    aux.add(X);

                    //activi.add(aux);
                    nuevoScroll.add(aux);
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
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(ic4);
                    aux.add(X);

                    //activi.add(aux);
                    nuevoScroll.add(aux);
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
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);

                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(ic1);
                    aux.add(X);

                    //activi.add(aux);
                    nuevoScroll.add(aux);
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
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setIcon(ic3);
                    X.setPreferredSize(new Dimension(400,200));
                    aux.add(X);
                    //activi.add(aux);
                    nuevoScroll.add(aux);
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
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setIcon(ic2);
                    X.setPreferredSize(new Dimension(400,200));
                    aux.add(X);
                    //activi.add(aux);
                    nuevoScroll.add(aux);
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
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombre = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(nombre);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setPreferredSize(new Dimension(400,300));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setIcon(ic4);
                    X.setPreferredSize(new Dimension(400,200));
                    aux.add(X);

                    //activi.add(aux);
                    nuevoScroll.add(aux);
                    btnActividades[j].setFont(font1);
                    j++;
                }
            }

        }

        if (e.getSource()==btnInfoUsuario) {
           nuevoScroll= activi;
        }

        scrollPane.setBounds(5,150, 1300, 600);
        nuevoScroll.setPreferredSize(new Dimension(700, 1000));
        nuevoScroll.setBackground(oscuro);
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




