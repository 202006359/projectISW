package icai.dtc.isw.ui;
import icai.dtc.isw.client.Client;
import icai.dtc.isw.domain.Actividad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelActividades extends JFrame {

    public String usuario;
    public String contrasena;
    JPanel principal = new JPanel();
    Font font2 = new Font("Broadway", Font.PLAIN, 50);
    Color verde = new Color(107, 190, 97);
    Font font1 = new Font("SansSerif", Font.PLAIN, 30);

    public static void main(String[] args) {
        PanelActividades game = new PanelActividades();
    }

    public PanelActividades() {
        this.setSize(700, 700);

        ArrayList<Actividad> actividades= ObtenActividades();
        principal.setForeground(Color.BLUE);
        //JPanel activi = new JPanel();
        //JButton act1 = new JButton(String.valueOf(actividades.get(0)));
        //JButton act2 = new JButton(String.valueOf(actividades.get(1)));
        //principal.add(act1);
        //principal.add(act2);

        //LABEL 1
        JLabel Bienvenida = new JLabel("Bienvenido", 0);
        Bienvenida.setPreferredSize(new Dimension(700, 80));
        Bienvenida.setBackground(verde);
        Bienvenida.setOpaque(true);
        Bienvenida.setForeground(Color.BLACK);
        Bienvenida.setFont(font2);
        principal.add(Bienvenida, BorderLayout.NORTH);

        //LABEL 2
        JLabel tops = new JLabel("", 0);
        tops.setPreferredSize(new Dimension(700, 150));
        tops.setBackground(verde);
        tops.setOpaque(true);
        tops.setForeground(Color.BLACK);
        tops.setFont(font2);
        principal.add(tops, BorderLayout.NORTH);
        principal.setBackground(Color.gray);

        //actividades
        JPanel activi = new JPanel();
        GridLayout grid = new GridLayout(4, 4);
        activi.setLayout(grid);
        JButton act1 = new JButton(String.valueOf(actividades.get(0)));
        JButton act2 = new JButton(String.valueOf(actividades.get(1)));
        JButton act3 = new JButton();
        JButton act4= new JButton();
        act1.setPreferredSize(new Dimension(500,100));
        act2.setPreferredSize(new Dimension(500,100));
        act3.setPreferredSize(new Dimension(500,100));
        act4.setPreferredSize(new Dimension(500,100));
        activi.add(act1);
        activi.add(act2);
        activi.add(act3);
        activi.add(act4);

        principal.add(activi);
        //JButton act1 = new JButton(String.valueOf(actividades.get(0)));
        //JButton act2 = new JButton(String.valueOf(actividades.get(1)));
        //activi.add(act1);
        //activi.add(act2);


        //JButton[] btnActividades = new JButton[16];

        /*principal.add(activi, BorderLayout.SOUTH);
        int[][] nums = {{10, 11, 12}, {1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int i = 0;
        for (int f = 0; f < nums.length; f++) {
            for (int c = 0; c < nums[f].length; c++) {
                if (nums[f][c] != -1) {
                    btnActividades[i] = new JButton();
                    btnActividades[i].setPreferredSize(new Dimension(250, 100));
                    activi.add(btnActividades[i], f, c);
                    btnActividades[i].setFont(font1);

                    i += 1;
                } else
                    activi.add(new Panel(), f, c);
            }
        }*/

        //JButton act1 = new JButton(String.valueOf(actividades.get(0)));
        //JButton act2 = new JButton(String.valueOf(actividades.get(1)));
        //principal.add(act1);
        //principal.add(act2);

        this.setForeground(Color.BLUE);
        this.add(principal);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


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




