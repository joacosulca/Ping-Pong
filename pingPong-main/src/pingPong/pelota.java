package pingPong;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class pelota {

    private int x = 385;
    private int y = 228;
    private final int ACHO = 20, ALTO = 20;
    private final int altoEs = 450, anchoEs = 780 - 12;
    private int velX = 5; // Aumenta la velocidad en X
    private int velY = 5; // Aumenta la velocidad en Y
    public int puntaje1 = 0;
    public int puntaje2 = 0;
    private barra barraIzq;
    private barra barraDer;

    public pelota(barra barraIzq, barra barraDer) {
        this.barraIzq = barraIzq;
        this.barraDer = barraDer;
    }

    public void paint(Graphics g) {
        ImageIcon pelota = new ImageIcon(getClass().getResource("/imagenes/pelota.png"));
        g.drawImage(pelota.getImage(), x, y, ACHO, ALTO, null);
    }

    public int getPuntos1() {
        return puntaje1;
    }

    public int getPuntos2() {
        return puntaje2;
    }

    public void mover() {  
        x += velX;
        y += velY;

        // Si la pelota toca el borde derecho
        if (x > anchoEs) {
            x = 385;
            y = 228;
            velX = -velX;
            puntaje1++;
        }

        // Si la pelota toca el borde izquierdo
        if (x < 0) {
            x = 385;
            y = 228;
            velX = -velX;
            puntaje2++;
        }

        // Si la pelota toca el borde superior o inferior
        if (y > altoEs || y < 0) {
            velY = -velY;
        }

        // Comprobar colisiones con las barras
        Rectangle2D pelotaRect = new Rectangle2D.Double(x, y, ACHO, ALTO);
        
        // Colisión con la barra izquierda
        Rectangle2D barraIzqRect = barraIzq.getBoundsBarra();
        if (pelotaRect.intersects(barraIzqRect)) {
            // Ajusta la posición de la pelota para evitar que se quede pegada
            if (velX < 0) {
                x = (int) (barraIzqRect.getMaxX()); // Mueve la pelota al borde derecho de la barra
            }
            velX = -velX;
        }

        // Colisión con la barra derecha
        Rectangle2D barraDerRect = barraDer.getBoundsBarra();
        if (pelotaRect.intersects(barraDerRect)) {
            // Ajusta la posición de la pelota para evitar que se quede pegada
            if (velX > 0) {
                x = (int) (barraDerRect.getMinX() - ACHO); // Mueve la pelota al borde izquierdo de la barra
            }
            velX = -velX;
        }
    }
}
