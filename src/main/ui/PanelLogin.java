package main.ui;

import java.awt.*;
import javax.swing.*;

public class PanelLogin extends JPanel{

    private Dimension dimension = new Dimension(VentanaLogin.WINDOW_WIDTH, VentanaLogin.WINDOW_HEIGTH);
    public PanelLogin(){
        this.init();
    }

    public void init(){
        this.setVisible(true);
        this.setPreferredSize(dimension);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Rockwell",Font.BOLD,48));
        g.drawString("INICIO SESIÓN", 60,50);
    }
}
