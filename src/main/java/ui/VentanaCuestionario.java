package ui;
import client.Client;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
/**
 * Clase para visualizar en pantalla la ventana del Cuestionario
 */

/**
 * Clase para visualizar en pantalla la ventana del Cuestionario incial
 */
public class VentanaCuestionario extends JFrame implements ActionListener {

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5]; // tipo de boton circular, usuario puede ver si esta pulsado o no
    JLabel labelTexto[] = new JLabel[5]; //etiqueta para las preguntas y respuestas
    JButton btnSiguiente;
    ButtonGroup bg; // hace que si se pulsa un boton los demás estén bloqueados
    int cuenta;
    int vecesA, vecesB, vecesC, vecesD; //contador de veces de cada pregunta
    String usuario;
    String perfil; //Variable de salida para asociar el perfil a 3 categorias de la base de datos

    public static void main(String[] args) {
        new VentanaCuestionario(" ");
    }

    public VentanaCuestionario(String usuario) {
        super("SMART PLAN");
        this.usuario = usuario;

        PanelInicio pnlCuestionario = new PanelInicio();
        this.add(pnlCuestionario);


        JLabel lblCuestionario = new JLabel("CUESTIONARIO");
        lblCuestionario.setBounds(340,220,500,50);
        lblCuestionario.setFont(new Font("Serif", Font.PLAIN, 40));
        lblCuestionario.setForeground(new Color(30,46,64));


        label = new JLabel();
        bg = new ButtonGroup();


        for (int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            labelTexto[i] = new JLabel();
            pnlCuestionario.add(labelTexto[i]);
            pnlCuestionario.add(radioButton[i]);
            bg.add(radioButton[i]);
        }

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);

        radioButton[0].addActionListener(this);
        btnSiguiente.setBounds(200, 510, 600, 40);
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSiguiente.setBackground(new Color(30,46,64));

        pnlCuestionario.add(lblCuestionario);
        pnlCuestionario.add(label);
        pnlCuestionario.add(btnSiguiente);

        preguntas();


        this.pack();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }

    public void preguntas() {
        radioButton[4].setSelected(true);
        if (cuenta == 0) {
            label.setText("<html> PREGUNTA 1 <br> <br> ¿Qué sueles hacer antes y después de salir del trabajo/clase?</html>");
            labelTexto[0].setText("Me voy a casa esperando a que sea viernes y no hago nada.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Vuelvo a casa lo antes posible para alejarme del estres del curro.");//Espiritual
            labelTexto[2].setText("Compro el café cerca e intento comer/cenar en algún bar de la zona.");//Gastronomia
            labelTexto[3].setText("Voy al gimnasio para despejarme después de un día intenso.");//Deportes
        }
        if (cuenta == 1) {
            label.setText("<html> PREGUNTA 2 <br> <br> Erasmus, vienen tus amigos a tu ciudad, ¿qué haces con ellos?</html>");
            labelTexto[0].setText("Me los llevo a la discoteca más grande de la ciudad.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Les digo de ir a los mejores museos y conocer cultura.");//Ocio Cultural
            labelTexto[2].setText("Aprovecho para hacer una comida todos juntos.");//Gastronomia
            labelTexto[3].setText("Jugamos un partido de selecciones contra italianos.");//Deportes
        }
        if (cuenta == 2) {
            label.setText("<html> PREGUNTA 3 <br> <br> ¿Qué te habría gustado hacer el dia que cumpliste 18?</html>");
            labelTexto[0].setText("Salir de fiesta con los amigos que hayan cumplido 18.");//Ocio Nocturno
            labelTexto[1].setText("Hacer el examen práctico de conducir y poder hacer viajes con amigos.");//Aventuras
            labelTexto[2].setText("Invitaría a mis amigos a cenar para enseñarles mi restaurante favorito.");//Gastronomia
            labelTexto[3].setText("Organizar un torneo de padel por parejas con todos mis amigos.");//Deportes
        }
        if (cuenta == 3) {
            label.setText("<html> PREGUNTA 4 <br> <br> Cuando acabas los examens del 2ºCuatri, ¿qué sueles hacer?");
            labelTexto[0].setText("Me voy de fiesta con mis compañeros de clase al sitio de moda.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Intento volver a la cama para dormir una super siesta y liberar tensiones.");//Espiritual
            labelTexto[2].setText("Ceno en el sitio más lujuso que conozco porque me lo merezco.");//Gastronomia
            labelTexto[3].setText("Trato de recuperar la forma física que he perdido en examenes yendo al gym.");//Deportes
        }
        if (cuenta == 4) {
            label.setText("<html>PREGUNTA 5 <br> <br> Te regalan un viaje a Nueva York, ¿qué es lo primero que haces al llegar?</html>");
            labelTexto[0].setText("Salir a alguna discoteca en la azotea de un rascacielos.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Visito el museo de historia natural.");//Ocio Cultural
            labelTexto[2].setText("Voy al restaurante con mas estrellas michelin de la ciudad.");//Gastronomia
            labelTexto[3].setText("Voy a ver un partido de baloncesto de los famososo New York Knicks.");//Deportes
        }
        if (cuenta == 5) {
            label.setText("<html> PREGUNTA 6 <br> <br> ¿Qué querías ser de pequeño?</html>");
            labelTexto[0].setText("Quería ser el domador de leones del circo y poder trabajar en un zoo.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Ayudar a los demás en una ONG por el mundo.");//Espiritual
            labelTexto[2].setText("Chef.");//Gastronomia
            labelTexto[3].setText("Deportista profesional.");//Deportes
        }
        if (cuenta == 6) {
            label.setText("<html> PREGUNTA 7 <br> <br> ¿Cuál de los siguientes planes elegirías para pasar un dia festivo?");
            labelTexto[0].setText("Pasar el día en el Parque de atracciones.");//Aventuras
            labelTexto[1].setText("Hora Santa con el grupo cristiano HAKUNA.");//Espiritual
            labelTexto[2].setText("Ir de tapas por Malasaña.");//Gastronomia
            labelTexto[3].setText("Pasear en bicicleta por El Retiro.");//Deportes
        }
        if (cuenta == 7) {
            label.setText("<html> PREGUNTA 8 <br> <br> ¿Cuál es el rasgo de tu personalidad del que te sientes más orgulloso?</html>");
            labelTexto[0].setText("El entusiasta, busco pasármelo bien en grupo y ser impetuoso en todo momento.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("El sociable, soy atento e intento ser buen compañero.");//Espiritual
            labelTexto[2].setText("El integrado, me gusta disfrutal de las pequeñas cosas y trato de controlar mi imagen.");//Gastronomia
            labelTexto[3].setText("El inquieto, activo y animado.");//Deportes
        }
        if (cuenta == 8) {
            label.setText("<html> PREGUNTA 9 <br> <br> ¿Qué te gustaría hacer si te jubilaras mañana? (te sobra el dinero)</html>");
            labelTexto[0].setText("Viajar por todo el mundo.");//jOcio Nocturno y Aventuras
            labelTexto[1].setText("Dedicarme a los demás, ya que tengo mucho tiempo libre.");//Espiritual
            labelTexto[2].setText("Me apuntaría a mogollón de cursos de cocina y aprender de ese mundillo.");//Gastronomia
            labelTexto[3].setText("Trataria de seguir haciendo ejercicio para mantenerme en forma y envejecer mejor.");//Deportes
        }
        if (cuenta == 9) {
            label.setText("<html> PREGUNTA 10 <br> <br> ¿Cuál de estas culturas admiras más?</html>");
            labelTexto[0].setText("Estadounidense.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Japonesa.");//Espiritual
            labelTexto[2].setText("Española.");//Gastronomia
            labelTexto[3].setText("Africana.");//Deportes
        }

        label.setBounds(200, 260, 600, 60);
        for (int i = 0, j = 0; i <= 90; i += 30, j++){
            radioButton[j].setBounds(200, 340 + i, 20, 20);
            radioButton[j].setBackground(Color.white);
            labelTexto[j].setBounds(230,340 + i, 650, 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == radioButton[0]) {
            vecesA++;
        }
        if (e.getSource() == radioButton[1]) {
            vecesB++;
        }
        if (e.getSource() == radioButton[2]) {
            vecesC++;
        }
        if (e.getSource() == radioButton[3]) {
            vecesD++;
        }
        if (e.getSource() == btnSiguiente) {
            cuenta++;
            preguntas();
            if (cuenta == 10) {
                btnSiguiente.setEnabled(false);
                switch(vecesA){
                    case 9:
                        perfil = "Ocio Cultural;Aventuras;Gastronomia";
                        break;
                    case 8:
                        perfil = "Ocio Cultural;Aventuras;Espiritual";
                        break;
                    case 7:
                        perfil = "Aventuras;Ocio Cultural;Gastronomia";
                        break;
                    case 6:
                        perfil = "Aventuras;Gastronomia;Espiritual";
                        break;
                    case 5:
                        perfil = "Ocio Cultural;Deportes;Gastronomia";
                        break;
                    case 4:
                        perfil = "Gastronomia;Espiritual;Aventuras";
                        break;
                    case 3:
                        perfil = "Gastronomia;Ocio Nocturno;Deportes";
                        break;
                    case 2:
                        perfil = "Aventuras;Ocio Cultural;Deportes";
                        break;
                    case 1:
                        perfil = "Ocio Nocturno;Espiritual;Gastronomia";
                        break;
                    default:
                        perfil = "Aventuras;Espiritual;Ocio Cultural";

                }
                completarCuenta();
                ventanaActivi(usuario);
            }
        }

    }

    public void completarCuenta() {
        Client cliente=new Client();
        HashMap<String,Object> session=new HashMap<>();
        String context="/completeAccount";
        session.put("usuario",usuario);
        session.put("contrasena",""); //No necesito la contraseña en esta versión
        session.put("perfil", perfil);
        cliente.sentMessage(context,session);

    }

    public void ventanaActivi(String usuario){ //Me manda a la ventana del cuestionario
        System.out.println("HE LLEGADO");
        this.exit();
        new VentanaActividades(usuario);
    }

    private void exit(){
        this.dispose();
        this.setVisible(false);

    }


}


