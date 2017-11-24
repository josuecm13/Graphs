/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.logic.Estructuras.Grafo;
import game.logic.Estructuras.Nodo;
import game.logic.exceptions.AlreadyInsertedException;
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
    
    private Nodo _nodo;
    public int _x, _y;
    private Ellipse2D circle;
    private String nombre;
    
    public NodoGUI(Ellipse2D img, String nom, Grafo grafo) {
        circle = img;
        _x = (int) circle.getX();
        _y = (int) circle.getY();
        nombre = nom;
        
        try {
            grafo.insertarVertice(nom);
            _nodo = grafo.buscarNodo(nom);
        } catch (AlreadyInsertedException e) {
            System.out.println("Nodo previamente insertado");
        }
    }
    
    public String getNombre() {
        
        return nombre;
    }
    
    public Nodo getNodo() {
        
        return _nodo;
    }
    
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
