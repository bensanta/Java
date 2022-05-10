
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

//imports
import javax.swing.JFrame;
import javax.swing.JPanel; //To use JPanel
//To use user input, we need to import the following:
import javax.awt.event.ActionListener;

import java.util.Timer; //To use Timer

import javax.awt.event.ActionEvent;
import javax.awt.event.KeyEvent;
import javax.awt.event.KeyListener;

//main class for brick breaker game
public class Main
{
    //main method
    public static void main(String[] args)
    {
        //create a new JFrame
        new JFrame();

        //Adding the panel into the JFrame
        Gameplay gamePlay = new Gameplay(); //Creating an object of this class
        frame.add(gamePlay); //adding the panel into the JFrame

        
        //set the size of the JFrame
        frame.setSize(800, 600);
        
        //set the title of the JFrame
        frame.setTitle("Brick Breaker");
        
        //set the JFrame to be visible
        frame.setVisible(true);
        
        //set the JFrame to exit the program on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
