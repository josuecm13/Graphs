/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author gaboq
 */
public class DrawCircle {
    
    protected JFrame instance;
    
    JButton clearBtn, nextBtn, blueBtn, greenBtn, redBtn, magentaBtn;
    Circulo drawCirculo;

    ActionListener actionListener = new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
              if (e.getSource() == clearBtn) {
                  drawCirculo.clear();
              } else if (e.getSource() == nextBtn) {
                  new SwingPaint().show(drawCirculo.getGUI());
                  instance.dispose();
              } 
              /*else if (e.getSource() == blueBtn) {
                  drawCirculo.blue();
              } else if (e.getSource() == greenBtn) {
                  drawCirculo.green();
              } else if (e.getSource() == redBtn) {
                  drawCirculo.red();
              }
            */
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
    drawCirculo = new Circulo();
  
    // add to content pane
    content.add(drawCirculo, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 
    clearBtn = new JButton("Borrar");
    clearBtn.addActionListener(actionListener);
    
    nextBtn = new JButton("Siguente");
    nextBtn.addActionListener(actionListener);
    /*
    blueBtn = new JButton("Blue");
    blueBtn.addActionListener(actionListener);
    greenBtn = new JButton("Green");
    greenBtn.addActionListener(actionListener);
    redBtn = new JButton("Red");
    redBtn.addActionListener(actionListener);
    magentaBtn = new JButton("Circle");
    magentaBtn.addActionListener(actionListener);
    
 
    // add to panel
    controls.add(greenBtn);
    controls.add(blueBtn);
    
    controls.add(redBtn);
    controls.add(magentaBtn);
*/
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
