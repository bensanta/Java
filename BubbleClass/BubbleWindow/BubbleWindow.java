/**
 * Once you run this program, if you click inside the black window,
 * a random sized and coloured bubble will appear. The bubbles will bounce off the edge of the
 * screen, and they will travel at a random speed. Once the program reaches 10 bubbles,
 * everytime you click over the limit of 10, it will remove one from the screen.
 * 
 * @author edited by Leah Wolf
 * @author edited by Benjamin Santa
 * @author original by Matt Memmo
 * @version 20220420
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.random.*;

public class BubbleWindow extends JFrame{
    
    // The BubbleWindow Constructor - creates the frame and adds the graphics panel to it.
    public BubbleWindow() {

        setTitle("Look at the Bubbles!");
        setSize(600, 400);
        // the event that triggers the end of the program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        setPreferredSize(getSize()); 
        add(new BubblePanel(getSize())); // Placing the JPanel into the JFrame
        pack();
        setVisible(true);
    }



    // The main method - start the show!
    public static void main(String... argv) {
        new BubbleWindow();
    }

    public static class BubblePanel extends JPanel  implements Runnable, MouseListener {

        private Thread animator;
        
        /**
         * An ArrayList to keep track of all of the bubbles you make.
         */
        private ArrayList<Bubble> bubs;

        public BubblePanel(Dimension dimension) {
            bubs = new ArrayList<>();

            //Set up the JPanel - no need to mess with the next 8 lines
            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            
            if (animator == null) {
                animator = new Thread(this);
                animator.start();
            }
            setDoubleBuffered(true);
        }

        public void mousePressed(MouseEvent e) {

            //Getting location
            int x = e.getX();
            int y = e.getY();

            /**
             * Based on the location of the mouse press (x,y), make a new bubble and
             * add it to the ArrayList.
             */
            bubs.add(new Bubble (x, y));

        }

        @Override
        public void paintComponent(Graphics g) {
            //to draw things to the screen
            Dimension d = getSize();

            //create a background
            g.setColor(Color.black);
            g.fillRect(0, 0, d.width, d.height);


            
                for(int i = 0; i < bubs.size(); i++){
                    Color randomColor = new Color(bubs.get(i).redColour(), bubs.get(i).greenColour(), bubs.get(i).blueColour());
                    //Sets the color of the bubble to be random
                    g.setColor(randomColor);
                    //Draws the bubble
                    g.fillOval(bubs.get(i).getXLocation(), bubs.get(i).getYLocation(), bubs.get(i).getSize(), bubs.get(i).getSize());
                    bubs.get(i).move();
                    //accessing the item in the array.commanding it to move depending on the method given
                }
                
                //Additional Feature - Once there are 10 bubbles in the ArrayList, it will remove a random bubble
                if(bubs.size() > 10){
                    int random = (int)(Math.random() * bubs.size());
                    bubs.remove(random);
                }
        }
        

        public void mouseReleased(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) { }

        public void mouseExited(MouseEvent e) { }

        public void mouseClicked(MouseEvent e) { 
        }

        public void run() {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            int animationDelay = 40;
            long time = System.currentTimeMillis();
            while (true) {
                repaint();
                try {
                    time += animationDelay;
                    Thread.sleep(Math.max(0,time - System.currentTimeMillis()));
                }
                catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}