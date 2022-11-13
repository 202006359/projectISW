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
    public static final int WINDOW_HEIGTH = 700;
    public static final int WINDOW_WIDTH = 1000;

    public VentanaLogin() {
        super("SMART PLAN");

        PanelLogin pnlLogin = new PanelLogin();
        this.add(pnlLogin);


        JLabel lblIn = new JLabel("INICIO SESIÓN");
        lblIn.setBounds(340,220,500,50);
        lblIn.setFont(new Font("Serif", Font.PLAIN, 40));
        lblIn.setForeground(new Color(30,46,64));
        pnlLogin.add(lblIn);


        JLabel lblUsr = new JLabel("Nombre de usuario");
        lblUsr.setBounds(180,260,160,30);
        lblUsr.setFont(new Font("Serif", Font.PLAIN, 15));
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(180,290,390,30);
        txtUsr.setText("admin@gmail.com"); //Valor por defecto para pruebas

        JLabel lblPassword= new JLabel("Contraseña");
        lblPassword.setBounds(180,320,160,30);
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(180,350,390,30);
        txtPassword.setText("admin1");//Valor por defecto para pruebas

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(180,400,390,40);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.BLACK);


        JButton btnSignin = new JButton("Registrarse");
        btnSignin.setBounds(180,460,390,40);
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
