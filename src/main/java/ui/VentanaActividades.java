package ui;
import client.Client;
import domain.Actividad;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import domain.Customer;

/**
 * Clase para visualizar en pantalla la ventana de Actividades
 */
public class VentanaActividades extends JFrame{

    public String usuario;
    public String contrasena;
    JPanel principal = new JPanel();
    Font font2 = new Font("Broadway", Font.PLAIN, 20);
    Font font1 = new Font("SansSerif", Font.PLAIN, 15);



    public static void main(String[] args) {
        VentanaActividades game = new VentanaActividades("beatrizorbe");
    }

    public VentanaActividades(String usuario) {

        this.usuario=usuario;

        this.setSize(700, 700);
        ArrayList<Actividad> actividades = ObtenActividades();
        principal.setForeground(Color.BLUE);

        //LABEL Categorias
        JLabel categorias = new JLabel();
        categorias.setPreferredSize(new Dimension(600, 80));
        GridLayout grid = new GridLayout(1, 4);
        categorias.setLayout(grid);
        principal.add(categorias, BorderLayout.WEST);
        JButton deporte = new JButton("DEPORTES");
        JButton fiesta = new JButton("FIESTAS");
        JButton cultura = new JButton("CULTURA");
        JButton musica = new JButton("MUSICA");
        categorias.add(deporte);
        categorias.add(cultura);
        categorias.add(fiesta);
        categorias.add(musica);

        //btn info usuario
        JButton btnInfoUsuario = new JButton("INFO");
        btnInfoUsuario.setBorder(new RoundedBorder(15));
        principal.add(btnInfoUsuario, BorderLayout.NORTH);


        //OBTENGO PERFIL USUARIO, sus tres categorias favoritas segun el cuestionario rellenado y lo separo para poder trabajr con las categorias.
        String perfil =  ObtenPerfil();

        String[] perfilUsuario = perfil.split(";");
        String perfil1 = perfilUsuario[0];
        String perfil2 = perfilUsuario[1];
        String perfil3 =perfilUsuario[2];


        //Scroll actividades
        ImageIcon fondo = new ImageIcon("resources/fotoFondo.jpg");
        JLabel img = new JLabel(fondo,0);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5,100, 700, 600);
        GridLayout grid3 = new GridLayout();
        JPanel activi = new JPanel();
        activi.add(img);
        activi.setPreferredSize(new Dimension(700, 2000));


        //CREAMOS EL GRID LAYOUT CORRESPONDIENTE AL TIPO DE PLANES QUE QUIERE EL QUIERE ~ ASOCIADO A SU PERFIL

        JButton[] btnActividades = new JButton[60];
        scrollPane.add(activi);

        //de su primera favorita categoria ponemos 9 actividades
        //recorremos la base de datos buscando aquellas actividades que correspondan con la categoria buscada
        int j = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            System.out.println(actividades.get(i).getCategoria());
            System.out.println(actividades.get(i).getNombre());
            System.out.println(Objects.equals(actividades.get(i).getCategoria(), perfil1));
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil1)) == true) {
                btnActividades[j] = new JButton(actividades.get(i).getNombre());
                btnActividades[j].setPreferredSize(new Dimension(200, 100));
                btnActividades[j].setBorder(new RoundedBorder(50));
                activi.add(btnActividades[j]);
                btnActividades[j].setFont(font1);
                j++;
            }
        }

        //de su segunda categoria favorita ponemos 6 actividades
        int k = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            System.out.println(actividades.get(i).getCategoria());
            System.out.println(perfil2);
            System.out.println(actividades.get(i).getNombre());

            System.out.println(Objects.equals(actividades.get(i).getCategoria(), perfil2));
            if ((Objects.equals(actividades.get(i).getCategoria(), perfil2)) == true)
                if (k<6){
                btnActividades[k] = new JButton(actividades.get(i).getNombre());
                btnActividades[k].setPreferredSize(new Dimension(200, 100));
                btnActividades[k].setBorder(new RoundedBorder(50));
                activi.add(btnActividades[k]);
                btnActividades[k].setFont(font1);
                k++;
            }
        }

        //de su tercera categoria favorita ponemos 3 actividades
        int p = 0;
        for (int i = 0; i < actividades.toArray().length; i++) {
            System.out.println(actividades.get(i).getCategoria());
            System.out.println(perfil2);
            System.out.println(actividades.get(i).getNombre());

            System.out.println(Objects.equals(actividades.get(i).getCategoria(), perfil2));

            if ((Objects.equals(actividades.get(i).getCategoria(), perfil3)) == true)  {
                if (p<3) {
                    btnActividades[p] = new JButton(actividades.get(i).getNombre());
                    btnActividades[p].setPreferredSize(new Dimension(200, 100));
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




