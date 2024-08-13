package pingPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel implements ActionListener {

    barra barraIzq = new barra("izq");
    barra barraDer = new barra("der");
    pelota pelota;
    public int puntosJugador1;
    public int puntosJugador2;
    private Timer timer;

    public Juego() {
        pelota = new pelota(barraIzq, barraDer); // Inicializa la pelota con las barras

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                barraIzq.keyPressed(e);
                barraDer.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
        
        // Configura el temporizador para actualizar la pantalla
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon cancha = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
        g.drawImage(cancha.getImage(), 0, 0, getWidth(), getHeight(), this);

        Font puntaje = new Font("Arial", Font.BOLD, 25);
        g.setFont(puntaje);
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + pelota.getPuntos1() + " a " + pelota.getPuntos2(), 300, 25);
        
        barraIzq.paint(g);
        barraDer.paint(g);
        pelota.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pelota.mover(); // Mueve la pelota
        repaint(); // Actualiza la pantalla
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ping Pong");
        Juego game = new Juego();
        frame.add(game);
        frame.setSize(800, 520);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        // Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/pelota.ico"));
        // frame.setIconImage(icono);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
