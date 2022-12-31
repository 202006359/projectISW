package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Clase para crear el panel que se implementará en la ventana de Login
 */
public class PanelGestionReserva extends JPanel{

    private Dimension dimension = new Dimension(VentanaLogin.WINDOW_WIDTH, VentanaLogin.WINDOW_HEIGTH);
    public PanelGestionReserva(){
        this.init();
    }

    public void init(){
        this.setBackground(new Color(30,46,64));
        this.setVisible(true);
        this.setPreferredSize(dimension);
        this.setLayout(null);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRoundRect(100,100,800,500,50,50);


    }
}
