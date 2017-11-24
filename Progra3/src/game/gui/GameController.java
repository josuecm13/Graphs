/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.logic.Estructuras.Grafo;
import game.logic.exceptions.AlreadyInsertedException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author gaboq
 */
public class GameController extends JComponent implements MouseListener{
    
    private Image image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;
    ArrayList<NodoGUI> nodos;
    Grafo grafo;
    
    public GameController(ArrayList<NodoGUI> g, Grafo graf, Image img, Graphics2D gra) {
        nodos = g;
        grafo  = graf;
        image = img;
        g2 = gra;
    }
    
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        for (NodoGUI n : nodos) {
            g2.fill(n.getCircle());
            g2.drawString(n.getNombre(), n._x, n._y);
        }

        g.drawImage(image, 0, 0, null);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX();
        oldY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        NodoGUI temp = origen();
        if (temp != null &&  temp.contains(oldX, oldY)  ) {
            System.out.println(oldX + " " + oldY);
            NodoGUI dest = null;
            for (NodoGUI n : nodos) {
                if (n.contains(e.getX(), e.getY())) {
                    dest = n;
                }
            }
            if (dest != null && dest != temp) {

                grafo.imprimir();

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    private NodoGUI origen() {
        for (NodoGUI n : nodos) {
            if (n.contains(oldX, oldY)) {
                return n;
            }
        }
        return null;
    }
    
}
