package game.gui;

import game.logic.Estructuras.Grafo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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
import javax.swing.JTextField;

/**
 *
 * @author gaboq
 */
public class SwingPaint {
    
    JButton clearBtn, configBtn, tiendaBtn;
    DrawArea drawArea;
    Circulo drawCirculo;
    public Grafo grafo;
    JTextField _peso;
    ArrayList<NodoGUI> nodos;
    JPanel controls;
    GameController game;

  ActionListener actionListener = new ActionListener() {
 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.removeMouseListener(drawArea);
                _peso.setEnabled(false);
                configBtn.setEnabled(true);
                tiendaBtn.setEnabled(true);
                drawArea.addMouseListener(game);
            } else if (e.getSource() == configBtn) {
                new Configuraciones(grafo).setVisible(true);
                grafo.imprimir();
            } else if (e.getSource() == tiendaBtn) {
                new Tienda().setVisible(true);
            }
        }
    };

  public void show(ArrayList<NodoGUI> g, Grafo graf) {
    // create main frame
    grafo = graf;
    nodos = g;
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
    
    
    _peso = new JTextField(15);
    _peso.setBackground(Color.decode("#CC0000"));
    Font font = new Font("Courier", Font.BOLD,25);
    _peso.setFont(font);
    _peso.setForeground (Color.white);
    
    // create draw area
    drawArea = new DrawArea(g, grafo, _peso);
    game = new GameController(g, grafo, drawArea.getImg(), drawArea.getGraphic());
    
    // add to content pane
    content.add(drawArea, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    controls = new JPanel();
 
    clearBtn = new JButton("Jugar");
    clearBtn.addActionListener(actionListener);
    configBtn = new JButton("Configuraciones");
    configBtn.addActionListener(actionListener);
    tiendaBtn = new JButton("Tienda");
    tiendaBtn.addActionListener(actionListener);
    
    
    // add to panel
    controls.add(_peso);
    controls.add(clearBtn);
    controls.add(tiendaBtn);
    controls.add(configBtn);
    configBtn.setEnabled(false);
    tiendaBtn.setEnabled(false);
    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(1400, 720);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
 
    }
    
    
}
