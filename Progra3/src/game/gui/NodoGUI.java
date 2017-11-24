/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

/**
 *
 * @author gaboq
 */
public class NodoGUI {
    
    //private Nodo _nodo;
    private int _x, _y;
    private Ellipse2D circle;
    
    public NodoGUI(Ellipse2D img) {
        circle = img;
        _x = (int) circle.getX();
        _y = (int) circle.getY();
    }
    
    /*
    public Nodo getNodo() {
        return _nodo;
    }
    */
    
    public boolean contains(int x, int y) {
        return (x > _x && x < (_x + getWidth()) &&
                y > _y && y < (_y + getHeight()));
    }
    
    public int getWidth() {
        return (int) circle.getWidth();
    }

    public int getHeight() {
        return (int) circle.getHeight();
    }
    
    public Ellipse2D getCircle() {
        return circle;
    }
    
}
