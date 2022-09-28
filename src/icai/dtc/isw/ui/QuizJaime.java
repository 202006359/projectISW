package icai.dtc.isw.ui;
import java.awt.event.*;
import javax.swing.*;

public class QuizJaime extends JFrame implements ActionListener {

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5]; //tipo de boton, usuario puede ver si esta pulsado o no
    JButton btnSiguiente;
    ButtonGroup bg; //hace que si se pulsa un boton los demás estén bloqueados
    int cuenta;
    int vecesA;
    int vecesB;
    int vecesC;
    int vecesD;


    public QuizJaime(){
        super("SMART PLAN");
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for(int i = 0; i < 5; i++){
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            bg.add(radioButton[i]);
        }

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);
        add(btnSiguiente);
        radioButton[0].addActionListener(this);

        label.setBounds(30, 40, 450, 20);
        radioButton[0].setBounds(50, 80, 450, 20);
        radioButton[1].setBounds(50, 110, 200, 20);
        radioButton[2].setBounds(50, 140, 200, 20);
        radioButton[3].setBounds(50, 170, 200, 20);
        btnSiguiente.setBounds(100, 240, 100, 30);
        preguntas();
        personaje();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    public void preguntas(){
        radioButton[4].setSelected(true);
        if(cuenta == 0){
            label.setText("PREGUNTA 1: Como seria para ti el plan de cita ideal?");
            radioButton[0].setText("Ir al cine");
            radioButton[1].setText("Comer en el parque");
            radioButton[2].setText("Ver al Valencia");
            radioButton[3].setText("Ir a la warner");
        }
        if (cuenta == 1){
            label.setText("PREGUNTA 2: Cual es tu dia favorito de la semana?");
            radioButton[0].setText("Viernes");
            radioButton[1].setText("Sabado");
            radioButton[2].setText("Domingo");
            radioButton[3].setText("Cualquier dia de entre semana");
        }
        if (cuenta == 2){
            label.setText("PREGUNTA 3: Que plan de comida te renta mas?");
            radioButton[0].setText("Comida");
            radioButton[1].setText("Desayuno");
            radioButton[2].setText("Cena");
            radioButton[3].setText("Merienda");
        }
        if (cuenta == 3){
            label.setText("PREGUNTA 4: Eres mas de playa o montaña?");
            radioButton[0].setText("Playa");
            radioButton[1].setText("Montaña");
            radioButton[2].setText("Ninguna");
            radioButton[3].setText("Las dos");
        }
        if (cuenta == 4){
            label.setText("PREGUNTA 5: Cuando ves una peli, que tematica te mola mas?");
            radioButton[0].setText("Suspense");
            radioButton[1].setText("Comedia");
            radioButton[2].setText("Terror");
            radioButton[3].setText("Accion");
        }
        if (cuenta == 5){
            label.setText("PREGUNTA 6: ");
            radioButton[0].setText("");
            radioButton[1].setText("");
            radioButton[2].setText("");
            radioButton[3].setText("");
        }
        if (cuenta == 6){
            label.setText("PREGUNTA 7: ");
            radioButton[0].setText("");
            radioButton[1].setText("");
            radioButton[2].setText("");
            radioButton[3].setText("");
        }
        if (cuenta == 7){
            label.setText("PREGUNTA 8: ");
            radioButton[0].setText("");
            radioButton[1].setText("");
            radioButton[2].setText("");
            radioButton[3].setText("");
        }
        if (cuenta == 8){
            label.setText("PREGUNTA 9: ");
            radioButton[0].setText("");
            radioButton[1].setText("");
            radioButton[2].setText("");
            radioButton[3].setText("");
        }

        label.setBounds(30,40,450,20);
        for(int i = 0, j = 0; i <= 90; i += 30, j++)
            radioButton[j].setBounds(50,80 + i, 200, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub

        if(e.getSource() == btnSiguiente){
            cuenta++;
            preguntas();
            if(cuenta == 9){
                btnSiguiente.setEnabled(false);
            }
        }
        //pequeño error a la hora de clicar porque solo guarda la primera pulsacion
        if(e.getSource() == radioButton[0]){
            vecesA++;
        }
        if(e.getSource() == radioButton[1]){
            vecesB++;
        }
        if(e.getSource() == radioButton[2]){
            vecesC++;
        }
        if(e.getSource() == radioButton[3]){
            vecesD++;
        }
    }

    public void personaje(){
        if(vecesA>= 1)//logica que queramos para desarrollar numero y tipo de personajes
            System.out.println("Atrevida");
        if(vecesB == 4)
            System.out.println("LOCO");
        if(vecesC == 5)
            System.out.println("Atilano");
        if(vecesD == 2)
            System.out.println("JAIME");
    }

    /*public static void main(String s[]){
        new QuizJaime();
    }*/
}

