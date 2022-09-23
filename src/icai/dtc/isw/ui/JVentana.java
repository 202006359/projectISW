package icai.dtc.isw.ui;

import icai.dtc.isw.client.Client;
import icai.dtc.isw.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class JVentana extends JFrame {
    public static void main(String[] args) {
        new JVentana();
    }
    private String usuario;
    private String contrasena;
    public JVentana() {
        super("SMART PLAN");

        PanelLogin pnlLogin = new PanelLogin();
        this.add(pnlLogin);


        JLabel lblUsr = new JLabel("Usuario");
        lblUsr.setBounds(60,60,160,30);
        JTextField txtUsr = new JTextField(50);
        txtUsr.setBounds(60,90,390,30);

        JLabel lblPassword= new JLabel("Contraseña");
        lblPassword.setBounds(60,120,160,30);
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setBounds(60,150,390,30);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(60,200,390,40);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.BLACK);


        pnlLogin.add(lblUsr);
        pnlLogin.add(txtUsr);
        pnlLogin.add(lblPassword);
        pnlLogin.add(txtPassword);
        pnlLogin.add(btnLogin);

        btnLogin.addActionListener(e ->{
            usuario = txtUsr.getText();
            contrasena = String.valueOf(txtPassword.getPassword());

        });

        /*


        JButton btnInformacion = new JButton("Recibir información");
        JTextField txtId = new JTextField();
        txtId.setBounds(new Rectangle(250,150,250,150));
        txtId.setHorizontalAlignment(JTextField.LEFT);
        pnlCentro.add(lblUsr);
        pnlCentro.add(txtId);
        pnlCentro.add(btnInformacion);
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.	X_AXIS));
        this.add(pnlCentro, BorderLayout.CENTER);

        //El Sur lo hago para recoger el resultado
        JPanel pnlSur = new JPanel();
        JLabel lblResultado = new JLabel("El resultado obtenido es: ", SwingConstants.CENTER);
        JTextField txtResultado = new JTextField();
        txtResultado.setBounds(new Rectangle(250,150,250,150));
        txtResultado.setEditable(false);
        txtResultado.setHorizontalAlignment(JTextField.LEFT);
        pnlSur.add(lblResultado);
        pnlSur.add(txtResultado);
        //Añado el listener al botón
        btnInformacion.addActionListener(actionEvent -> {
            id=Integer.parseInt(txtId.getText());
            txtResultado.setText(recuperarInformacion());
        });
        pnlSur.setLayout(new BoxLayout(pnlSur, BoxLayout.X_AXIS));
        this.add(pnlSur,BorderLayout.SOUTH);

        this.setSize(550,120);

         */
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String recuperarInformacion() {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/getCustomer";
        //session.put("id",id);
        session=cliente.sentMessage(context,session);
        Customer cu=(Customer)session.get("Customer");
        return cu.getName();
    }
}
