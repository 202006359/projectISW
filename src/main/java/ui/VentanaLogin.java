package ui;

import client.Client;
import com.sendemail.SendEmailThroughGmail;
import dao.ActivityDAO;
import domain.Customer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
/**
 * Clase para visualizar en pantalla la ventana de Inicio de Sesión
 */
public class VentanaLogin extends JFrame {
    public static void main(String[] args) {
        new VentanaLogin();
    }
    private String usuario;
    private String contrasena;
    public static final int WINDOW_HEIGTH = 700;
    public static final int WINDOW_WIDTH = 1000;

    public VentanaLogin() {
        super("SMART PLAN");


        PanelInicio pnlLogin = new PanelInicio();
        this.add(pnlLogin);



        JLabel lblIn = new JLabel("INICIO SESIÓN");
        lblIn.setBounds(340,220,500,50);
        lblIn.setFont(new Font("Serif", Font.PLAIN, 40));
        lblIn.setForeground(new Color(30,46,64));
        pnlLogin.add(lblIn);


        JLabel lblUsr = new JLabel("Nombre de usuario");
        lblUsr.setBounds(200,290,300,30);
        lblUsr.setFont(new Font("Serif", Font.BOLD, 16));
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(200,320,600,30);
        txtUsr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtUsr.setText("Inserte el correo electrónico");

        JLabel lblPassword= new JLabel("Contraseña");
        lblPassword.setBounds(200,360,300,30);
        lblPassword.setFont(new Font("Serif", Font.BOLD, 16));
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(200,390,600,30);
        txtPassword.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtPassword.setText("Inserte la contraseña");//Valor por defecto para pruebas
        txtPassword.setEchoChar((char)0);;

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(200,445,600,40);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 16));
        btnLogin.setBackground(new Color(30,46,64));


        JButton btnSignin = new JButton("¿Eres nuevo? Únete a la mejor plataforma de planes");
        btnSignin.setBounds(200,490,600,40);
        btnSignin.setForeground(Color.GRAY);
        btnSignin.setBackground(Color.white);
        btnSignin.setFont(new Font("Serif", Font.BOLD, 16));
        btnSignin.setBorder(null);


        pnlLogin.add(lblUsr);
        pnlLogin.add(txtUsr);
        pnlLogin.add(lblPassword);
        pnlLogin.add(txtPassword);
        pnlLogin.add(btnLogin);
        pnlLogin.add(btnSignin);

        txtUsr.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){ //Para ocultar el texto al pinchar en el JTextField
                if(txtUsr.getText().equals("Inserte el correo electrónico"))
                    txtUsr.setText("");

            }
        });
        txtUsr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(txtUsr.getText().equals("Inserte el correo electrónico"))
                    txtUsr.setText("");
            }
        });
        txtPassword.addMouseListener(new MouseAdapter() { //Para ocultar el texto al pinchar en el JTextField
            @Override
            public void mouseClicked(MouseEvent e) {

                if(String.valueOf(txtPassword.getPassword()).equals("Inserte la contraseña")){
                    txtPassword.setText("");
                    txtPassword.setEchoChar('*');
                }
            }
        });


        btnLogin.addActionListener(e ->{
            usuario = txtUsr.getText();
            contrasena = String.valueOf(txtPassword.getPassword());
            if(!isUserValido()){
                JOptionPane.showMessageDialog(VentanaLogin.this,"El usuario introducido no existe");
                txtUsr.setText("");
                txtPassword.setText("");
            }

            else if(contrasena.equals(recuperarContrasena())){ //Verifico que la contraseña introducida coincida con la de la base de datos
                abrirPrincipal(); //Me voy a la ventana principal
            }else{
                JOptionPane.showMessageDialog(VentanaLogin.this,"Contrasena incorrecta");
                txtPassword.setText("");
            }
        });

        btnSignin.addActionListener(e->{
            this.registrarse(); //Si no tengo cuenta, me voy a la ventana de Sign In
        });


        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public void registrarse() { //Me voy a la ventana de Registro
        this.exit();
        new VentanaSignin();
    }

    private void exit(){
        this.dispose();
        this.setVisible(false);
    }


    public String recuperarContrasena() { //Metodo utilizado para obtener de la base de datos la contrasena guardada del usuario
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getPassword";
        session.put("usuario",usuario);
        session=cliente.sentMessage(context,session);
        Customer cu=(Customer)session.get("Customer");
        return cu.getContrasena();
    }
    public boolean isUserValido() { //Me devuelve true si el usuario existe en la base de datos
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/checkUser";
        session.put("usuario",usuario);
        session=cliente.sentMessage(context,session);
        return (boolean) session.get("Customer");
    }


    public String recuperarCustomer() { //Metodo utilizado para obtener de la base de datos la contrasena guardada del usuario
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getPassword";
        session.put("usuario",usuario);
        session=cliente.sentMessage(context,session);
        Customer cu=(Customer)session.get("Customer");
        return cu.getContrasena();
    }
    public void abrirPrincipal() //ME voy a la ventana principal de planes
    {
        this.exit();
        new VentanaActividades(usuario);
    }

}
