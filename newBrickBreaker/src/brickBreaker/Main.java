package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		
		//create a new JFrame
        new JFrame();

        //Adding the panel into the JFrame
        Gameplay gamePlay = new Gameplay(); //Creating an object of this class
        frame.add(gamePlay); //adding the panel into the JFrame

        
        //set the bounds of the JFrame
        frame.setBounds(10, 10, 700, 600);
        
        //set the title of the JFrame
        frame.setTitle("Brick Breaker");
        
        //Makes the JFrame a locked size
        
        frame.setResizable(false); 
        
        //set the JFrame to be visible
        frame.setVisible(true);
        
        //set the JFrame to exit the program on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
