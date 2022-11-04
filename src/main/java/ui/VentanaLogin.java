package ui;

import client.Client;
import domain.Customer;

import javax.swing.*;
import java.awt.*;
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
    public static final int WINDOW_HEIGTH = 350;
    public static final int WINDOW_WIDTH = 500;

    public VentanaLogin() {
        super("SMART PLAN");

        PanelLogin pnlLogin = new PanelLogin();
        this.add(pnlLogin);


        JLabel lblUsr = new JLabel("Usuario");
        lblUsr.setBounds(60,60,160,30);
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(60,90,390,30);
        txtUsr.setText("admin@gmail.com"); //Valor por defecto para pruebas

        JLabel lblPassword= new JLabel("Contraseña");
        lblPassword.setBounds(60,120,160,30);
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(60,150,390,30);
        txtPassword.setText("admin1");//Valor por defecto para pruebas

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(60,200,390,40);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.BLACK);


        JButton btnSignin = new JButton("Registrarse");
        btnSignin.setBounds(60,260,390,40);
        btnSignin.setForeground(Color.WHITE);
        btnSignin.setBackground(Color.GRAY);




        pnlLogin.add(lblUsr);
        pnlLogin.add(txtUsr);
        pnlLogin.add(lblPassword);
        pnlLogin.add(txtPassword);
        pnlLogin.add(btnLogin);
        pnlLogin.add(btnSignin);

        btnLogin.addActionListener(e ->{
            usuario = txtUsr.getText();
            contrasena = String.valueOf(txtPassword.getPassword());

            if(contrasena.equals(recuperarContrasena())){ //Verifico que la contraseña introducida coincida con la de la base de datos
                JOptionPane.showMessageDialog(VentanaLogin.this,"Contrasena correcta");
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


    public void abrirPrincipal() //ME voy a la ventana principal de planes
    {
        this.exit();
        new VentanaActividades(usuario);
    }

}
