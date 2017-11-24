/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.logic.Estructuras.Grafo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author gaboq
 */
public class DrawCircle {
    
    protected JFrame instance;
    public Grafo grafo = new Grafo();
    
    JButton clearBtn, nextBtn, blueBtn, greenBtn, redBtn, magentaBtn;
    Circulo drawCirculo;
    JTextField nameField;
    
    ActionListener actionListener = new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
              if (e.getSource() == clearBtn) {
                  drawCirculo.clear();
              } else if (e.getSource() == nextBtn) {
                  new SwingPaint().show(drawCirculo.getGUI(), grafo);
                  instance.dispose();
              } 
          }
      };
 
  public static void main(String[] args) {
    new DrawCircle().show();
    
  }
 
  public void show() {
    // create main frame
    JFrame frame = new JFrame("Nodos");
    instance = frame;
    
    Container content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
    
    nameField = new JTextField(15);
    nameField.setBackground(Color.decode("#CC0000"));
    Font font = new Font("Courier", Font.BOLD,25);
    nameField.setFont(font);
    nameField.setForeground (Color.white);
    
    drawCirculo = new Circulo(grafo, nameField);
 
    // add to content pane
    content.add(drawCirculo, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 
    clearBtn = new JButton("Borrar");
    clearBtn.addActionListener(actionListener);
    
    nextBtn = new JButton("Siguente");
    nextBtn.addActionListener(actionListener);
    
    controls.add(nameField);
    controls.add(nextBtn);
    controls.add(clearBtn);
 
    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(1400, 720);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
 
  }
    
}
