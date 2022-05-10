

/**
 * Write a description of class Gameplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

//imports
import javax.swing.JPanel; //To use JPanel

//To use user input, we need to import the following:
import javax.awt.event.ActionListener;
import javax.awt.event.ActionEvent;
import javax.awt.event.KeyEvent;
import javax.awt.event.KeyListener;

import java.util.Timer; //To use Timer
import java.awt.Color;
import java.awt.Graphics; //to use Graphics



public class Gameplay extends JPanel implements KeyListener, ActionListener
{
    private boolean play = false; //boolean to check if the game is running - make sure the game isn't running instantly
    private int score = 0; //score of the game

    private int totalBricks = 21; //total number of bricks

    private Timer timer; //timer object
    private int delay = 8; //delay of the timer

    private int playerX = 310; //x position of the slider

    private int ballposX = 120; //x position of the ball

    private int ballposY = 350; //y position of the ball

    private int ballXdir = -1; //x direction of the ball

    private int ballYdir = -2; //y direction of the ball

    private MapGenerator map; //map generator object

    public Gameplay() {
        map = new MapGenerator(3, 7); //create a new map generator object

        addKeyListener(this); //add key listener to the panel
        setFocusable(true); //set the panel to be focusable
        setFocusTraversalKeysEnabled(false); //set the panel to not be focusable

        //create a new timer object
        timer = new Timer(delay, this);
        timer.start(); //start the timer
    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);

        //import 
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //the paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8); //uses a player's x position 
        
    }

    //Adding the methods needed for ActionListener and KeyListener
    @Override //From the ActionListener
    public void keyPressed(KeyEvent e) { }
    @Override //From the KeyListener
    public void keyReleased(KeyEvent e) { }
    @Override //From the KeyListener
    public void keyTyped(KeyEvent e) { }
    @Override //From the KeyListener
    public void actionPerformed(ActionEvent e) { }



}
