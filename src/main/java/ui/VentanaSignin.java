package ui;

import client.Client;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
/**
 * Clase para visualizar en pantalla la ventana de Registrar Usuario
 */
public class VentanaSignin extends JFrame {
    public static void main(String[] args) {
        new VentanaSignin();
    }

    private String usuario;
    private String contrasena;
    public VentanaSignin(){
        super("SMART PLAN");
        PanelInicio pnlSignin = new PanelInicio();
        this.add(pnlSignin);

        JLabel lblIn = new JLabel("CREAR CUENTA");
        lblIn.setBounds(320,220,500,50);
        lblIn.setFont(new Font("Serif", Font.PLAIN, 40));
        lblIn.setForeground(new Color(30,46,64));
        pnlSignin.add(lblIn);

        JLabel lblUsr = new JLabel("Nombre de usuario");
        lblUsr.setBounds(200,290,300,30);
        lblUsr.setFont(new Font("Serif", Font.BOLD, 16));
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(200,320,600,30);
        txtUsr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtUsr.setText("Inserte un correo electrónico");

        JLabel lblPassword= new JLabel("Contraseña");
        lblPassword.setBounds(200,360,300,30);
        lblPassword.setFont(new Font("Serif", Font.BOLD, 16));
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(200,390,600,30);
        txtPassword.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtPassword.setText("Inserte una contraseña alfanumérica");
        txtPassword.setEchoChar((char)0);;

        JLabel lblPasswordConfirmation= new JLabel("Confirme la contraseña");
        lblPasswordConfirmation.setBounds(200,430,300,30);
        lblPasswordConfirmation.setFont(new Font("Serif", Font.BOLD, 16));
        JPasswordField txtPasswordConfirmation = new JPasswordField(20);
        txtPasswordConfirmation.setBounds(200,460,600,30);
        txtPasswordConfirmation.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        txtPasswordConfirmation.setText("Inserte la misma contraseña alfanumérica");
        txtPasswordConfirmation.setEchoChar((char)0);;

        JButton btnSignin = new JButton("Crear Cuenta");
        btnSignin.setBounds(200,510,600,40);
        btnSignin.setForeground(Color.WHITE);
        btnSignin.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSignin.setBackground(new Color(30,46,64));

        JButton btnVolver = new JButton("Volver a la ventana de inicio de sesión");
        btnVolver.setBounds(200,550,600,40);
        btnVolver.setForeground(Color.GRAY);
        btnVolver.setBackground(Color.white);
        btnVolver.setFont(new Font("Serif", Font.BOLD, 16));
        btnVolver.setBorder(null);

        pnlSignin.add(lblUsr);
        pnlSignin.add(txtUsr);
        pnlSignin.add(lblPassword);
        pnlSignin.add(txtPassword);
        pnlSignin.add(lblPasswordConfirmation);
        pnlSignin.add(txtPasswordConfirmation);
        pnlSignin.add(btnSignin);
        pnlSignin.add(btnVolver);

        txtUsr.addMouseListener(new MouseAdapter(){ //Para ocultar el texto al pinchar en el JTextField
            @Override
            public void mouseClicked(MouseEvent event){
                if(txtUsr.getText().equals("Inserte un correo electrónico"))
                    txtUsr.setText("");

            }
        });
        txtUsr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(txtUsr.getText().equals("Inserte un correo electrónico"))
                    txtUsr.setText("");
            }
        });
        txtPassword.addMouseListener(new MouseAdapter() { //Para ocultar el texto al pinchar en el JTextField
            @Override
            public void mouseClicked(MouseEvent e) {

                if(String.valueOf(txtPassword.getPassword()).equals("Inserte una contraseña alfanumérica")){
                    txtPassword.setText("");
                    txtPassword.setEchoChar('*');
                }
            }
        });
        txtPasswordConfirmation.addMouseListener(new MouseAdapter() { //Para ocultar el texto al pinchar en el JTextField
            @Override
            public void mouseClicked(MouseEvent e) {

                if(String.valueOf(txtPasswordConfirmation.getPassword()).equals("Inserte la misma contraseña alfanumérica")){
                    txtPasswordConfirmation.setText("");
                    txtPasswordConfirmation.setEchoChar('*');
                }
            }
        });

        btnSignin.addActionListener(e->{
            usuario = txtUsr.getText();
            contrasena = String.valueOf(txtPassword.getPassword());

            if(contrasena.equals(String.valueOf(txtPasswordConfirmation.getPassword())) && checkContrasena(contrasena)){ //Veo si la contraseña tiene el formato correcto
                crearCuenta();
                JOptionPane.showMessageDialog(VentanaSignin.this,"Registro completado");
                cuestionario(usuario); //Me manda a rellenar el cuestionario que usaremos para obtener las preferencias de los planes del usuario


            }else if(!checkContrasena(contrasena)){ //Verifico que la contraseña cumpla todos los requisitos
                JOptionPane.showMessageDialog(VentanaSignin.this,"La contraseña no es alfanumérica");
                txtPassword.setText("");
                txtPasswordConfirmation.setText("");
            }else{
                JOptionPane.showMessageDialog(VentanaSignin.this,"Las contraseñas no coinciden");
                txtPassword.setText("");
                txtPasswordConfirmation.setText("");
            }
        });

        btnVolver.addActionListener(e->{
            volverInicioSesion();
        });


        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void crearCuenta() { //Metodo para guardar en la base de datos mi usuario y contrasena
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

    public void cuestionario(String usuario){ //Me manda a la ventana del cuestionario
        this.exit();
        new VentanaCuestionario(usuario);
    }

    public void volverInicioSesion(){ //Me manda a la ventana del cuestionario
        this.exit();
        new VentanaLogin();
    }


    private void exit(){
        this.dispose();
        this.setVisible(false);
    }



}
