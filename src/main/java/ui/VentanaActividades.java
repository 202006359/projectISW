package ui;
import client.Client;
import domain.Actividad;
import javax.swing.*;
import dao.ActivityDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import domain.Customer;
import domain.Oferta;

import static java.lang.Math.random;

/**
 * Clase para visualizar en pantalla la ventana de Actividades
 */
public class VentanaActividades extends JFrame implements ActionListener {

    String usuario;
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
    //ArrayList<Actividad> actividades = ObtenActividades();
    ArrayList<Actividad> actividades = ActivityDAO.getActividades();

    JButton deporte = new JButton("DEPORTES");
    JButton fiesta = new JButton("OCIO NOCTURNO");
    JButton gastronomia = new JButton("GASTRONOMIA");
    JButton aventuras = new JButton("AVENTURAS");
    JButton espiritual = new JButton("ESPIRITUAL");
    JButton cultura = new JButton("OCIO CULTURAL");
    JButton btnInfoUsuario = new JButton();
    ImageIcon im;ImageIcon im1;ImageIcon im2;ImageIcon im3;ImageIcon im4;ImageIcon im6;
    Icon ic1;Icon ic0;Icon ic2;Icon ic3;Icon ic4;Icon ic6;
    JButton btnDescuentos = new JButton("CONSULTAR DESCUENTOS");

    public static void main(String[] args) {
        new VentanaActividades("BEA");
   }

    public VentanaActividades(String usuario) {
        System.out.println("HE ENTRADDO");

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
        scrollPane.setBounds(5,225, 1300, 600);
        GridLayout grid3 = new GridLayout(10,3,20,20    );
        activi.setPreferredSize(new Dimension(700, 2500));
        activi.setBackground(oscuro);


        //CREAMOS EL GRID LAYOUT CORRESPONDIENTE AL TIPO DE PLANES QUE QUIERE EL QUIERE ~ ASOCIADO A SU PERFIL
        scrollPane.add(activi);

        //de su primera favorita categoria ponemos 9 actividades
        //recorremos la base de datos buscando aquellas actividades que correspondan con la categoria buscada
        int j = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil1)) == true) {
                System.out.println(actividades.get(i).getPrecio());
                Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                // System.out.println("prueba:" + img);
                Icon img2 = new ImageIcon(img);
                btnActividades[j] = new JButton(actividades.get(i).getNombre());
                btnActividades[j].setBackground(amarillo);
                btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                btnActividades[j].setPreferredSize(new Dimension(400, 100));
                String nombreAct = actividades.get(i).getNombre();
                btnActividades[j].setFont(font1);
                btnActividades[j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new VentanaReservas(usuario,nombreAct,img);
                    }
                });
                JPanel aux = new JPanel();
                aux.setBackground(oscuro);
                aux.setPreferredSize(new Dimension(400,400));
                aux.add(btnActividades[j]);

                JButton foto = new JButton();
                foto.setIcon(img2);
                foto.setPreferredSize(new Dimension(400,200));
                foto.setBackground(oscuro);
                aux.add(foto);

                //descuento
                double descuento=0;
                float suerte = generarNumeroAleatorio01();
                double precio;
                if (suerte == 3){

                    String nombre = actividades.get(i).getNombre();
                    descuento = generarNumeroDescuento();
                    new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                    ActivityDAO.completarActividad(nombre, (float) descuento);
                    descuento = generarNumeroDescuento();
                    precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                    JButton btnPrecio = new JButton("" + precio + " $");
                    btnPrecio.setBackground(oscuro);
                    btnPrecio.setForeground(Color.RED);
                    aux.add(btnPrecio);
                    aux.revalidate();
                }


                else {
                    JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString()+ " $");
                    btnPrecio.setBackground(oscuro);
                    btnPrecio.setForeground(Color.white);
                    aux.add(btnPrecio);
                    aux.revalidate();
                }

                /*precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();

                JButton precio2 = new JButton(actividades.get(i).getPrecio().toString());
                precio.setBackground(oscuro);
                precio.setForeground(Color.white);
                aux.add(precio);
                aux.revalidate();*/

                activi.add(aux);

                j++;
            }
        }

        //de su segunda categoria favorita ponemos 6 actividades
        int k = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil2)) == true)
                if (k<6){
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[k] = new JButton(actividades.get(i).getNombre());
                    btnActividades[k].setBackground(amarillo);
                    btnActividades[k].setPreferredSize(new Dimension(400, 100));
                    btnActividades[k].setFont(new Font("Rockwell",Font.BOLD,40));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[k].setFont(font1);
                    btnActividades[k].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });

                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[k]);
                    JButton foto = new JButton();
                    foto.setIcon(img2);
                    foto.setPreferredSize(new Dimension(400,200));
                    foto.setBackground(oscuro);
                    aux.add(foto);

                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){

                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);
                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString()+ " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/

                    activi.add(aux);

                    k++;
                }
        }

        //de su tercera categoria favorita ponemos 3 actividades
        int p = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil3)) == true)  {
                if (p<3) {
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[p] = new JButton(actividades.get(i).getNombre());
                    btnActividades[p].setBackground(amarillo);
                    btnActividades[p].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[p].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[p].setFont(font1);
                    btnActividades[p].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[p]);
                    JButton foto = new JButton();
                    foto.setIcon(img2);
                    foto.setPreferredSize(new Dimension(400,200));
                    foto.setBackground(oscuro);
                    aux.add(foto);
                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){
                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString() + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/

                    activi.add(aux);
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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(img2);
                    aux.add(X);
                    //activi.add(aux);

                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){
                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString()+ " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }
                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/

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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });

                    //descuento



                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(img2);
                    aux.add(X);

                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){
                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);

                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString() + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString()+" $");
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);
                    //activi.add(aux);*/
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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(img2);
                    aux.add(X);
                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){

                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString()+ " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/

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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);

                    JButton X= new JButton();
                    X.setPreferredSize(new Dimension(400,200));
                    X.setIcon(img2);
                    aux.add(X);

                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){

                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString() + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/

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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setIcon(img2);
                    X.setPreferredSize(new Dimension(400,200));
                    aux.add(X);
                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){
                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);

                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString()+ " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/
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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setIcon(img2);
                    X.setPreferredSize(new Dimension(400,200));
                    aux.add(X);

                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){
                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);

                        precio = actividades.get(i).getPrecio() - descuento*actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString()+ " $") ;
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/
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
                    Image img = actividades.get(i).getImagen().getScaledInstance(400,200, 1);
                    Icon img2 = new ImageIcon(img);
                    btnActividades[j] = new JButton(actividades.get(i).getNombre() );
                    btnActividades[j].setBackground(amarillo);
                    btnActividades[j].setFont(new Font("Rockwell",Font.BOLD,40));
                    btnActividades[j].setPreferredSize(new Dimension(400, 100));
                    String nombreAct = actividades.get(i).getNombre();
                    btnActividades[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new VentanaReservas(usuario,nombreAct,img);
                        }
                    });
                    JPanel aux = new JPanel();
                    aux.setBackground(oscuro);
                    aux.setPreferredSize(new Dimension(400,400));
                    aux.add(btnActividades[j]);
                    JButton X= new JButton();
                    X.setIcon(img2);
                    X.setPreferredSize(new Dimension(400,200));
                    aux.add(X);

                    double descuento=0;
                    float suerte = generarNumeroAleatorio01();
                    double precio;
                    if (suerte == 3){
                        String nombre = actividades.get(i).getNombre();
                        descuento = generarNumeroDescuento();
                        new Oferta(nombre,actividades.get(i).getDescripcion(), (float)descuento);
                        ActivityDAO.completarActividad(nombre, (float) descuento);
                        precio = actividades.get(i).getPrecio() - descuento * actividades.get(i).getPrecio();
                        JButton btnPrecio = new JButton("" + precio + " $");
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.RED);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }


                    else {
                        JButton btnPrecio = new JButton(actividades.get(i).getPrecio().toString());
                        btnPrecio.setBackground(oscuro);
                        btnPrecio.setForeground(Color.white);
                        aux.add(btnPrecio);
                        aux.revalidate();
                    }

                    /*JButton precio = new JButton(actividades.get(i).getPrecio().toString());
                    precio.setBackground(oscuro);
                    precio.setForeground(Color.white);
                    aux.add(precio);*/

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

        scrollPane.setBounds(5,225, 1300, 600);
        nuevoScroll.setPreferredSize(new Dimension(700, 1500));
        nuevoScroll.setBackground(oscuro);
        scrollPane.setViewportView(nuevoScroll);

        /*if (e.getSource()==btnDescuentos)
        {

        }*/

    }

    //Obtengo las actividades disponibles de la base de datos
    /*public ArrayList<Actividad> ObtenActividades() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getActividades";
        session.put("usuario",this.usuario);
        session = cliente.sentMessage(context, session);
        ArrayList<Actividad> actividades= (ArrayList<Actividad>) session.get("Actividades");
        return actividades;
    }*/

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


    public double generarNumeroDescuento()
    {
        double cantidad = 0;
        float numeroAleatorio= generarNumeroAleatorioExtra();
        if ( numeroAleatorio == 1.0) {cantidad=0.1;}
        if ( numeroAleatorio==2.0) {cantidad=0.2;}
        if ( numeroAleatorio==3.0) {cantidad=0.5;}
        else cantidad = 0.1;
        return cantidad;
    }

    public float generarNumeroAleatorio01()
    {
        float numeroAleatorio= (float)Math.floor(Math.random()*5);
        return numeroAleatorio;
    }

    public float generarNumeroAleatorioExtra()
    {
        float numeroAleatorio= (float)Math.floor(Math.random()*3);
        return numeroAleatorio;
    }


    public void completarActividad(float descuento) {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/completeActivity";
        session.put("usuario",usuario);
        session.put("descuento", descuento);
        cliente.sentMessage(context,session);

    }





}




