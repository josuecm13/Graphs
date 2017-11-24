package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import static java.awt.SystemColor.window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author gaboq
 */
public class SwingPaint {
    
  JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
  DrawArea drawArea;
  Circulo drawCirculo;
  
  ActionListener actionListener = new ActionListener() {
 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == blackBtn) {
                drawArea.black();
            } else if (e.getSource() == blueBtn) {
                drawArea.blue();
            } else if (e.getSource() == greenBtn) {
                drawArea.green();
            } else if (e.getSource() == redBtn) {
                drawArea.red();
            }
        }
    };
  
  ActionListener circleListener = new ActionListener() {
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == magentaBtn) {
          drawArea.magenta();
        }
    }
  };
 
  public void show(ArrayList<NodoGUI> g) {
    // create main frame
    JFrame frame = new JFrame("Aristas");
    frame.setResizable(false);
    frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, "Desea cerrar la aplicacion?",
                        "Cerrar programa", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    
    Container content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
    drawArea = new DrawArea(g);
  
    // add to content pane
    content.add(drawArea, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    blackBtn = new JButton("Black");
    blackBtn.addActionListener(actionListener);
    blueBtn = new JButton("Blue");
    blueBtn.addActionListener(actionListener);
    greenBtn = new JButton("Green");
    greenBtn.addActionListener(actionListener);
    redBtn = new JButton("Red");
    redBtn.addActionListener(actionListener);
    magentaBtn = new JButton("Circle");
    magentaBtn.addActionListener(circleListener);
 
    // add to panel
    controls.add(greenBtn);
    controls.add(blueBtn);
    controls.add(blackBtn);
    controls.add(redBtn);
    controls.add(magentaBtn);
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
