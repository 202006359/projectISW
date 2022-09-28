package icai.dtc.isw.ui;

import icai.dtc.isw.client.Client;
import icai.dtc.isw.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VentanaSignin extends JFrame {
    public static void main(String[] args) {
        new VentanaSignin();
    }

    private String usuario;
    private String contrasena;
    public VentanaSignin(){
        super("SMART PLAN");
        PanelSignin pnlSignin = new PanelSignin();
        this.add(pnlSignin);

        JLabel lblUsr = new JLabel("Dirección de correo electrónico");
        lblUsr.setBounds(60,60,300,30);
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(60,90,390,30);
        txtUsr.setText("ivan@gmail.com");

        JLabel lblPassword= new JLabel("Introduzca una contraseña alfanumérica");
        lblPassword.setBounds(60,120,300,30);
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(60,150,390,30);

        JLabel lblPasswordConfirmation= new JLabel("Confirme la contraseña");
        lblPasswordConfirmation.setBounds(60,180,300,30);
        JPasswordField txtPasswordConfirmation = new JPasswordField(20);
        txtPasswordConfirmation.setBounds(60,210,390,30);

        JButton btnSignin = new JButton("Crear Cuenta");
        btnSignin.setBounds(60,270,390,40);
        btnSignin.setForeground(Color.WHITE);
        btnSignin.setBackground(Color.BLACK);

        pnlSignin.add(lblUsr);
        pnlSignin.add(txtUsr);
        pnlSignin.add(lblPassword);
        pnlSignin.add(txtPassword);
        pnlSignin.add(lblPasswordConfirmation);
        pnlSignin.add(txtPasswordConfirmation);
        pnlSignin.add(btnSignin);

        btnSignin.addActionListener(e->{
            usuario = txtUsr.getText();
            contrasena = String.valueOf(txtPassword.getPassword());

            if(contrasena.equals(String.valueOf(txtPasswordConfirmation.getPassword())) && checkContrasena(contrasena)){
                crearCuenta();
                JOptionPane.showMessageDialog(VentanaSignin.this,"Registro completado");
                //Me iria al cuestionario de Jaime

            }else if(!checkContrasena(contrasena)){
                JOptionPane.showMessageDialog(VentanaSignin.this,"La contraseña no es alfanumérica");
                txtPassword.setText("");
                txtPasswordConfirmation.setText("");
            }else{
                JOptionPane.showMessageDialog(VentanaSignin.this,"Las contraseñas no coinciden");
                txtPassword.setText("");
                txtPasswordConfirmation.setText("");
            }
        });



        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void crearCuenta() {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        //String context="/getPassword";
        String context="/createAccount";
        session.put("usuario",usuario);
        session.put("contrasena", contrasena);
        cliente.sentMessage(context,session);

    }

    public boolean checkContrasena(String contrasena){ //Me comprueba que la contraseña sea alfanumerica de 8 letras y 1 digito
        String n = ".*[0-9].*";
        String a = ".*[A-Za-z].*";
        return contrasena.matches(n) && contrasena.matches(a);
        //matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$") Para que la primera sea mayuscula
    }



}
