package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false; //boolean to check if the game is running - make sure the game isn't running instantly
    private int score = 0; //score of the game

    private int totalBricks = 21; //total number of bricks

    private Timer timer; //timer object
    private int delay = 8; //delay of the timer

    private int playerX = 310; //x position of the slider

    //Ball info
    private int ballPosX = 120; //x position of the ball
    private int ballPosY = 350; //y position of the ball
    private int ballXDir = -1; //x direction of the ball
    private int ballYDir = -2; //y direction of the ball
    
    public Gameplay() {
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	timer = new Timer(delay, this); //with a delay variable, and a context of this
    	timer.start();
    }
    
    public void paint (Graphics g) {
    	//background
    	g.setColor(Color.BLACK);
    	g.fillRect(1,  1, 692, 592);
    	
    	//borders
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //the paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8); //uses a player's x position 
        
        //the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballPosX, ballPosY, 20, 20); //uses the ballPos variables 
        
        g.dispose();
    }
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(play) { //if playing
			
			//to be able to calculate the intersect between the ball and the paddle
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYDir = -ballYDir;
			}
			
			ballPosX += ballXDir;
			ballPosY += ballYDir;
			
			//if statements to bounce at an edge
			if(ballPosX < 0) {
				ballXDir = -ballXDir; //left border bounce
			}
			if(ballPosY < 0) {
				ballYDir = -ballYDir; //top bounce
			}
			if(ballPosX > 670) {
				ballXDir = -ballXDir; //right border bounce
			}
		}
		
		
		repaint(); //to repaint the entire scene - enable movement
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) { //check to see if it goes outside the panel or not
				playerX = 600;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX >= 10) { //check to see if it goes outside the panel or not
				playerX = 10;
			} else {
				moveLeft();
			}
		}
	}
	
	public void moveRight() {
		play = true; //Currently playing
		playerX += 20; //Move 20 pixels to the right
	}
	public void moveLeft() {
		play = true; //Currently playing
		playerX += 20; //Move 20 pixels to the left
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
