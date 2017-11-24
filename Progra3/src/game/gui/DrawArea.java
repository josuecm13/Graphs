/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.logic.Estructuras.Grafo;
import game.logic.exceptions.AlreadyInsertedException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author gaboq
 */
public class DrawArea extends JComponent implements MouseListener {

    private Image image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;
    ArrayList<NodoGUI> nodos;
    private int X, Y;
    Grafo grafo;
    JTextField peso;

    public DrawArea(ArrayList<NodoGUI> nodes, Grafo grf, JTextField _peso) {
        grafo = grf;
        nodos = nodes;
        peso = _peso;
        addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            clear();
        }

        for (NodoGUI n : nodos) {
            g2.fill(n.getCircle());
            g2.drawString(n.getNombre(), n._x, n._y);
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
            currentX = e.getX();
            currentY = e.getY();
            NodoGUI dest = null;
            for (NodoGUI n : nodos) {
                if (n.contains(e.getX(), e.getY())) {
                    dest = n;
                }
            }
            if (dest != null && dest != temp) {
                System.out.println(e.getX() + " " + e.getY());
                if (g2 != null) {
                    
                    String valor = peso.getText();
                    try {
                        if ("".equals(valor)) {
                            return;
                        }
                        grafo.insertarArista(temp.getNombre(), dest.getNombre(), Integer.parseInt(valor));
                    } catch (AlreadyInsertedException u) {
                        JOptionPane.showMessageDialog(null,"Aristas","Arista previamente insertado",JOptionPane.ERROR_MESSAGE);
                        System.out.println("Arista previamente insertado");
                        return;
                    }
                    
                    // draw line if g2 context not null
                    g2.drawLine(oldX, oldY, e.getX(), e.getY());
                    // refresh draw area to repaint
                    repaint();
                    // store current coords x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;
                }
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
