
package game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author gaboq
 */
public class Circulo extends JComponent {
    
    private Image image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;
    ArrayList<NodoGUI> nodos;

    public Circulo() {
        nodos = new ArrayList<>();
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            // save coord x,y when mouse is pressed
            oldX = e.getX();
            oldY = e.getY();
            if (g2 != null) {                                            
                Ellipse2D circle = new Ellipse2D.Double(oldX, oldY, 100, 100);
                NodoGUI nodo = new NodoGUI(circle);
                nodos.add(nodo);
                g2.fill(circle);
                // refresh draw area to repaint
                repaint();
                // store current coords x,y as olds x,y
                oldX = currentX;
                oldY = currentY;
            }
          }
        });

      addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
          // coord x,y when drag mouse
          currentX = e.getX();
          currentY = e.getY();
        }
      });
    }
    
    public ArrayList<NodoGUI> getGUI() {
        return nodos;
    }

    protected void paintComponent(Graphics g) {
      if (image == null) {
        image = createImage(getSize().width, getSize().height);
        g2 = (Graphics2D) image.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clear();
      }
      g.drawImage(image, 0, 0, null);
    }

    // now we create exposed methods
    public void clear() {
      g2.setPaint(Color.white);
      // draw white on entire draw area to clear
      g2.fillRect(0, 0, getSize().width, getSize().height);
      g2.setPaint(Color.black);
      repaint();
      nodos.clear();
    }
    
}
