package main.ui;
import main.client.Client;

import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class VentanaCuestionario extends JFrame implements ActionListener {

    JLabel label;
    JLabel label1;
    JRadioButton radioButton[] = new JRadioButton[5]; // tipo de boton, usuario puede ver si esta pulsado o no
    JLabel labelTexto[] = new JLabel[5];
    JButton btnSiguiente;
    ButtonGroup bg; // hace que si se pulsa un boton los demás estén bloqueados
    int cuenta;
    int vecesA, vecesB, vecesC, vecesD;
    String usuario;
    String perfil; //= "Aventurero"; //String perfil; Dejarlo asi cuando Jaime termine la funcionalidad de asignar el perfil

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
        completarCuenta();
        exit();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    public void preguntas() {
        radioButton[4].setSelected(true);
        //Ocio nocturno -> juerguista, Ocio cultural -> aristoteles, Aventuras -> indiana Jones, Gastronomia -> El Comidista, Deportes -> Rafa Nadal, Espiritual -> chill
        if (cuenta == 0) {
            label.setText("PREGUNTA 1: ¿Qué sueles hacer antes y después de salir del trabajo/clase?");
            labelTexto[0].setText("Me voy a casa esperando a que sea viernes y no hago nada.");//juerguista y aventurero
            labelTexto[1].setText("Vuelvo a casa lo antes posible para alejarme del estres del curro.");//chill
            labelTexto[2].setText("Compro el café cerca e intento comer/cenar en algún bar de la zona.");//comidista
            labelTexto[3].setText("Voy al gimnasio para despejarme después de un día intenso.");//rafa nadal
        }
        if (cuenta == 1) {
            label.setText("PREGUNTA 2: Erasmus, vienen tus amigos a tu ciudad, ¿qué haces con ellos?");
            labelTexto[0].setText("Me los llevo a la discoteca más grande de la ciudad.");//juerguista y aventurero
            labelTexto[1].setText("Les digo de ir a los mejores museos y conocer cultura.");//aristoteles
            labelTexto[2].setText("Aprovecho para hacer una comida todos juntos.");//comidista
            labelTexto[3].setText("Jugamos un partido de selecciones contra italianos.");//rafa nadal
        }
        if (cuenta == 2) {
            label.setText("PREGUNTA 3: ¿Qué te habría gustado hacer el dia que cumpliste 18?");
            labelTexto[0].setText("Salir de fiesta con los amigos que hayan cumplido 18.");//juerguista
            labelTexto[1].setText("Hacer el examen práctico de conducir y poder hacer viajes con amigos.");//aventurero
            labelTexto[2].setText("Invitaría a mis amigos a cenar para enseñarles mi restaurante favorito.");//comidista
            labelTexto[3].setText("Organizar un torneo de padel por parejas con todos mis amigos.");//rafa nadal
        }
        if (cuenta == 3) {
            label.setText("PREGUNTA 4: Cuando acabas los examens del 2ºCuatri, ¿qué sueles hacer?");
            labelTexto[0].setText("Me voy de fiesta con mis compañeros de clase al sitio de moda.");//juerguista y aventurero
            labelTexto[1].setText("Intento volver a la cama para dormir una super siesta y liberar tensiones.");//chill
            labelTexto[2].setText("Ceno en el sitio más lujuso que conozco porque me lo merezco.");//comidista
            labelTexto[3].setText("Trato de recuperar la forma física que he perdido en examenes yendo al gym.");//rafa nadal
        }
        if (cuenta == 4) {
            label.setText("PREGUNTA 5: Te regalan un viaje a Nueva York, ¿qué es lo primero que haces al llegar?");
            labelTexto[0].setText("Salir a alguna discoteca en la azotea de un rascacielos.");//juerguista y aventurero
            labelTexto[1].setText("Visito el museo de historia natural.");//aristoteles
            labelTexto[2].setText("Voy al restaurante con mas estrellas michelin de la ciudad.");//comidista
            labelTexto[3].setText("Voy a ver un partido de baloncesto de los famososo New York Knicks.");//rafa nadal
        }
        if (cuenta == 5) {
            label.setText("PREGUNTA 6: ¿Qué querías ser de pequeño?");
            labelTexto[0].setText("Quería ser el domador de leones del circo y poder trabajar en un zoo.");//juerguista y aventurero
            labelTexto[1].setText("Ayudar a los demás en una ONG por el mundo.");//chill
            labelTexto[2].setText("Chef.");//comidista
            labelTexto[3].setText("Deportista profesional.");//rafa nadal
        }
        if (cuenta == 6) {
            label.setText("PREGUNTA 7: ¿Cuál de los siguientes planes elegirías para pasar un dia festivo?");
            labelTexto[0].setText("Pasar el día en el Parque de atracciones.");//aventurero
            labelTexto[1].setText("Hora Santa con el grupo cristiano HAKUNA.");//chill
            labelTexto[2].setText("Ir de tapas por Malasaña.");//comidista
            labelTexto[3].setText("Pasear en bicicleta por El Retiro.");//rafa nadal
        }
        if (cuenta == 7) {
            label.setText("PREGUNTA 8: ¿Cuál es el rasgo de tu personalidad del que te sientes más orgulloso?");
            labelTexto[0].setText("El entusiasta, busco pasármelo bien en grupo y ser impetuoso en todo momento.");//juerguista y aventurero
            labelTexto[1].setText("El sociable, soy atento e intento ser buen compañero.");//chill
            labelTexto[2].setText("El integrado, me gusta disfrutal de las pequeñas cosas y trato de controlar mi imagen.");//comidista
            labelTexto[3].setText("El inquieto, activo y animado.");//rafa nadal
        }
        if (cuenta == 8) {
            label.setText("PREGUNTA 9: ¿Qué te gustaría hacer si te jubilaras mañana? (te sobra el dinero)");
            labelTexto[0].setText("");//juerguista y aventurero
            labelTexto[1].setText("");//chill
            labelTexto[2].setText("");//comidista
            labelTexto[3].setText("");//rafa nadal
        }
        if (cuenta == 9) {
            label.setText("PREGUNTA 10: ¿Cuál de estas culturas admiras más?");
            labelTexto[0].setText("");//juerguista y aventurero
            labelTexto[1].setText("");//chill
            labelTexto[2].setText("");//comidista
            labelTexto[3].setText("");//rafa nadal
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
                if(vecesA == 9)
                    perfil = "Ocio Cultural;Aventuras;Gastronomia";
                if(vecesA == 8)
                    perfil = "Ocio Cultural;Aventuras;Espiritual";
                if(vecesA == 7)
                    perfil = "Aventuras;Ocio Cultural;Gastronomia";
                if(vecesA == 6)
                    perfil = "Aventuras;Gastronomia;Espiritual";
                if(vecesA == 5)
                    perfil = "Ocio Cultural;Deportes;Gastronomia";
                if(vecesA == 4)
                    perfil = "Gastronomia;Espiritual;Aventuras";
                if(vecesA == 3)
                    perfil = "Gastronomia;Ocio Nocturno;Deportes";
                if(vecesA == 2)
                    perfil = "Aventuras;Ocio Cultural;Deportes";
                if(vecesA == 1)
                    perfil = "Ocio Nocturno;Espiritual;Gastronomia";
                if(vecesA == 0)
                    perfil = "Aventuras;Espiritual;Ocio Cultural";
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
        new VentanaActividades();
    }


}


