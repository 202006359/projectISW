package main.ui;

import main.client.Client;
import main.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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
        txtUsr.setText("ivan@gmail.com");

        JLabel lblPassword= new JLabel("Contraseña");
        lblPassword.setBounds(60,120,160,30);
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(60,150,390,30);

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

            if(contrasena.equals(recuperarContrasena())){
                JOptionPane.showMessageDialog(VentanaLogin.this,"Contrasena correcta");
                //Me iria a la pagina que esta haciendo Bea
                abrirPrincipal();

            }else{
                JOptionPane.showMessageDialog(VentanaLogin.this,"Contrasena incorrecta");
                txtPassword.setText("");
            }


        });

        btnSignin.addActionListener(e->{
            this.registrarse();
        });


        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void registrarse(){
        this.exit();
        new VentanaSignin();
    }

    /*public void test(){
        this.exit();
        new QuizJaime();
    }*/

    private void exit(){
        this.dispose();
        this.setVisible(false);
    }


    public String recuperarContrasena() {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getPassword";
        session.put("usuario",usuario);
        session=cliente.sentMessage(context,session);
        Customer cu=(Customer)session.get("Customer");
        return cu.getContrasena();
    }


    public void abrirPrincipal()
    {
        this.exit();
        new PanelActividades();
    }

}
