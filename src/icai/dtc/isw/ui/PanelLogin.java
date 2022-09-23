package icai.dtc.isw.ui;

import java.awt.*;
import javax.swing.*;

public class PanelLogin extends JPanel{

    private Dimension dimension = new Dimension(500,350);
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
