/**
 * JFrame (graphics window) built, then adds a BoardFace to it.
 * 
 * TASKS: 
 *    1. Inspect and understand the code within the paint method 
 *       (beginning on line  51).
 *    2. Using your knowledge of shapes, colors, and the coordinate plane,
 *       draw your own picture. 
 *       Remember the links to the documentation on the assignment page.
 *
 * @author Leah Wolf - Adapted from Matt Memmo
 * @version Summer 2020
 */

import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class DrawingFrame extends JFrame {
//#To make everything show up
    //Create & set up the window (JFrame)
    public DrawingFrame(){
        add(new BoardFace());  //Add the drawing Panel onto the Frame
        setTitle("Drawing Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {  //Run the program
        new DrawingFrame();
    }
    
    private class BoardFace extends JPanel {
    
        private Dimension d;
        public final int BOARD_WIDTH = 500;
        public final int BOARD_HEIGHT = 500;
        private int x = 0;
     
        public BoardFace() {
            setFocusable(true);
            //Panel is same size as Frame
            d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);  
        }
    
        public void paint(Graphics g){
            super.paint(g);
        //#Imp stuff begins here!!!
        //Fill the screen w/ a blue rect
            g.setColor(Color.blue);
            g.fillRect(0, 0, d.width, d.height);
        //Sun
            g.setColor(Color.orange);
            g.fillOval(250, 60, 100, 100);
        //Clouds
            g.setColor(Color.LIGHT_GRAY);
            
            int xCloud = 200, yCloud = 25;

            while(xCloud < 351){
                g.fillOval(xCloud, yCloud, 125, 75);
                xCloud += 35;
                yCloud += 10;
            }

        //Grass
            g.setColor(Color.green);
            g.fillRect(0, 300, 500, 200);
        //Tree Base
            g.setColor(Color.black);
            g.fillRect(60, 175, 50, 125);
        //Tree Leaves
            g.setColor(Color.green);
            g.fillOval(30, 125, 125, 120);
        //Bottom left Text
            g.setColor(Color.BLACK);;
            g.setFont(new Font("Dialog", Font.PLAIN, 20));
            g.drawString("The Coolest Tree Ever", 20,  420);
        }
    }
}