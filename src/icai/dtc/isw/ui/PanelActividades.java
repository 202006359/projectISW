package icai.dtc.isw.ui;
import icai.dtc.isw.client.Client;
import icai.dtc.isw.domain.Actividad;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class PanelActividades extends JFrame{

    public String usuario;
    public String contrasena;
    JPanel principal = new JPanel();
    Font font2 = new Font("Broadway", Font.PLAIN, 20);
    Font font1 = new Font("SansSerif", Font.PLAIN, 15);



    public static void main(String[] args) {
        PanelActividades game = new PanelActividades();
    }

    public PanelActividades() {
        this.setSize(700, 700);
        //fondo = Toolkit.getDefaultToolkit().getImage("resources/fotoFondo.jpg").getScaledInstance(700,680, Image.SCALE_SMOOTH);
        //Color rosa = new Color(236,112,99 );
        //Color naranja = new Color(245,176,99);
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


        //Scroll actividades
        ImageIcon fondo = new ImageIcon("resources/fotoFondo.jpg");
        JLabel img = new JLabel(fondo,0);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5,100, 700, 600);
        GridLayout grid3 = new GridLayout();
        JPanel activi = new JPanel();
        //activi.setLayout(grid3);
        activi.add(img);
        activi.setPreferredSize(new Dimension(700, 2000));

        JButton[] btnActividades = new JButton[30];
        scrollPane.add(activi);

        for (int i = 0; i < actividades.toArray().length; i++) {
            btnActividades[i] = new JButton(actividades.get(i).getNombre());
            btnActividades[i].setPreferredSize(new Dimension(200, 100));
            btnActividades[i].setBorder(new RoundedBorder(50));
            activi.add(btnActividades[i]);
            btnActividades[i].setFont(font1);



        }

        scrollPane.setViewportView(activi);
        //this.drawObjects(g);
        this.add(scrollPane);
        this.setForeground(Color.BLUE);
        this.add(principal);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


    }

    /*@Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
        drawObjects(g);
    }

   /* public void drawObjects(Graphics g){
        g.drawImage(fondo, 0, 0, this);
    }*/

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


    public ArrayList<Actividad> ObtenActividades() {
        Client cliente = new Client();
        HashMap<String, Object> session = new HashMap<>();
        String context = "/getActividades";
       session = cliente.sentMessage(context, session);
       ArrayList<Actividad> actividades= (ArrayList<Actividad>) session.get("Actividades");
        return actividades;
    }
}




