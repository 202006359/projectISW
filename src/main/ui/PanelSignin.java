package main.ui;

import java.awt.*;
import javax.swing.*;
/**
 * Clase para crear el panel que se implementará en la ventana de SignIn
 */
public class PanelSignin extends JPanel{

    private Dimension dimension = new Dimension(VentanaLogin.WINDOW_WIDTH, VentanaLogin.WINDOW_HEIGTH);
    public PanelSignin(){
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
        g.setFont(new Font("Calibri",Font.BOLD,22));
        g.drawString("Crea una cuenta de Smart Plan", 60,40);
    }
}

