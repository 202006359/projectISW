package ui;
import client.Client;

import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

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
    String perfil = ""; //Variable de salida para asociar el perfil a 3 categorias de la base de datos

    public VentanaCuestionario(String usuario) {
        super("SMART PLAN");
        this.usuario = usuario;
        label = new JLabel();
        add(label);

        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            labelTexto[i] = new JLabel();
            add(labelTexto[i]);
            add(radioButton[i]);
            bg.add(radioButton[i]);
        }

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);
        add(btnSiguiente);
        radioButton[0].addActionListener(this);

        label.setBounds(30, 40, 650, 20);
        radioButton[0].setBounds(50, 80, 0, 0);
        radioButton[1].setBounds(50, 110, 0, 0);
        radioButton[2].setBounds(50, 140, 0, 0);
        radioButton[3].setBounds(50, 170, 0, 0);
        labelTexto[0].setBounds(80, 80, 600, 20);
        labelTexto[1].setBounds(80, 110, 600, 20);
        labelTexto[2].setBounds(80, 140, 600, 20);
        labelTexto[3].setBounds(80, 170, 600, 20);
        btnSiguiente.setBounds(100, 240, 200, 30);
        preguntas();
        exit();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    //Metodo mostrar preguntas con sus respuestas y botones para respuesta y boton siguiente
    public void preguntas() {
        radioButton[4].setSelected(true);
        if (cuenta == 0) {
            label.setText("PREGUNTA 1: ¿Qué sueles hacer antes y después de salir del trabajo/clase?");
            labelTexto[0].setText("Me voy a casa esperando a que sea viernes y no hago nada.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Vuelvo a casa lo antes posible para alejarme del estres del curro.");//Espiritual
            labelTexto[2].setText("Compro el café cerca e intento comer/cenar en algún bar de la zona.");//Gastronomia
            labelTexto[3].setText("Voy al gimnasio para despejarme después de un día intenso.");//Deportes
        }
        if (cuenta == 1) {
            label.setText("PREGUNTA 2: Erasmus, vienen tus amigos a tu ciudad, ¿qué haces con ellos?");
            labelTexto[0].setText("Me los llevo a la discoteca más grande de la ciudad.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Les digo de ir a los mejores museos y conocer cultura.");//Ocio Cultural
            labelTexto[2].setText("Aprovecho para hacer una comida todos juntos.");//Gastronomia
            labelTexto[3].setText("Jugamos un partido de selecciones contra italianos.");//Deportes
        }
        if (cuenta == 2) {
            label.setText("PREGUNTA 3: ¿Qué te habría gustado hacer el dia que cumpliste 18?");
            labelTexto[0].setText("Salir de fiesta con los amigos que hayan cumplido 18.");//Ocio Nocturno
            labelTexto[1].setText("Hacer el examen práctico de conducir y poder hacer viajes con amigos.");//Aventuras
            labelTexto[2].setText("Invitaría a mis amigos a cenar para enseñarles mi restaurante favorito.");//Gastronomia
            labelTexto[3].setText("Organizar un torneo de padel por parejas con todos mis amigos.");//Deportes
        }
        if (cuenta == 3) {
            label.setText("PREGUNTA 4: Cuando acabas los examens del 2ºCuatri, ¿qué sueles hacer?");
            labelTexto[0].setText("Me voy de fiesta con mis compañeros de clase al sitio de moda.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Intento volver a la cama para dormir una super siesta y liberar tensiones.");//Espiritual
            labelTexto[2].setText("Ceno en el sitio más lujuso que conozco porque me lo merezco.");//Gastronomia
            labelTexto[3].setText("Trato de recuperar la forma física que he perdido en examenes yendo al gym.");//Deportes
        }
        if (cuenta == 4) {
            label.setText("PREGUNTA 5: Te regalan un viaje a Nueva York, ¿qué es lo primero que haces al llegar?");
            labelTexto[0].setText("Salir a alguna discoteca en la azotea de un rascacielos.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Visito el museo de historia natural.");//Ocio Cultural
            labelTexto[2].setText("Voy al restaurante con mas estrellas michelin de la ciudad.");//Gastronomia
            labelTexto[3].setText("Voy a ver un partido de baloncesto de los famososo New York Knicks.");//Deportes
        }
        if (cuenta == 5) {
            label.setText("PREGUNTA 6: ¿Qué querías ser de pequeño?");
            labelTexto[0].setText("Quería ser el domador de leones del circo y poder trabajar en un zoo.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Ayudar a los demás en una ONG por el mundo.");//Espiritual
            labelTexto[2].setText("Chef.");//Gastronomia
            labelTexto[3].setText("Deportista profesional.");//Deportes
        }
        if (cuenta == 6) {
            label.setText("PREGUNTA 7: ¿Cuál de los siguientes planes elegirías para pasar un dia festivo?");
            labelTexto[0].setText("Pasar el día en el Parque de atracciones.");//Aventuras
            labelTexto[1].setText("Hora Santa con el grupo cristiano HAKUNA.");//Espiritual
            labelTexto[2].setText("Ir de tapas por Malasaña.");//Gastronomia
            labelTexto[3].setText("Pasear en bicicleta por El Retiro.");//Deportes
        }
        if (cuenta == 7) {
            label.setText("PREGUNTA 8: ¿Cuál es el rasgo de tu personalidad del que te sientes más orgulloso?");
            labelTexto[0].setText("El entusiasta, busco pasármelo bien en grupo y ser impetuoso en todo momento.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("El sociable, soy atento e intento ser buen compañero.");//Espiritual
            labelTexto[2].setText("El integrado, me gusta disfrutal de las pequeñas cosas y trato de controlar mi imagen.");//Gastronomia
            labelTexto[3].setText("El inquieto, activo y animado.");//Deportes
        }
        if (cuenta == 8) {
            label.setText("PREGUNTA 9: ¿Qué te gustaría hacer si te jubilaras mañana? (te sobra el dinero)");
            labelTexto[0].setText("Viajar por todo el mundo.");//jOcio Nocturno y Aventuras
            labelTexto[1].setText("Dedicarme a los demás, ya que tengo mucho tiempo libre.");//Espiritual
            labelTexto[2].setText("Me apuntaría a mogollón de cursos de cocina y aprender de ese mundillo.");//Gastronomia
            labelTexto[3].setText("Trataria de seguir haciendo ejercicio para mantenerme en forma y envejecer mejor.");//Deportes
        }
        if (cuenta == 9) {
            label.setText("PREGUNTA 10: ¿Cuál de estas culturas admiras más?");
            labelTexto[0].setText("Estadounidense.");//Ocio Nocturno y Aventuras
            labelTexto[1].setText("Japonesa.");//Espiritual
            labelTexto[2].setText("Española.");//Gastronomia
            labelTexto[3].setText("Africana.");//Deportes
        }

        label.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++){
            radioButton[j].setBounds(50, 80 + i, 20, 20);
            labelTexto[j].setBounds(80,80 + i, 650, 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == radioButton[0]) {
            vecesA++; //cuenta numero de veces opcion A
        }
        if (e.getSource() == radioButton[1]) {
            vecesB++; //cuenta numero de veces opcion B
        }
        if (e.getSource() == radioButton[2]) {
            vecesC++; //cuenta numero de veces opcion C
        }
        if (e.getSource() == radioButton[3]) {
            vecesD++; //cuenta numero de veces opcion D
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

    private void exit(){
        this.dispose();
        this.setVisible(false);
    }

}


