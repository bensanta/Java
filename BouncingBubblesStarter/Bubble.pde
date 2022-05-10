/**
 * 
 * Bubble class skeleton. You will need to add:
 *   - your own instance variables,
 *   - parameters and functionality to the Constructor
 *   - methods for updating the Bubble as needed
 */
 
import java.util.*;

Random f = new Random();

public class Bubble{
  //Instance variables:
  private int red;
  private int green;
  private int blue;
  
  private int size;
  
  private int xSpeed;
  private int ySpeed;

  private int xLocation;
  private int yLocation;
  
  //Constructors
  public Bubble(int startLocX, int startLocY){
    //Initialise instance variables
    //Creating random color values for the red, green, and blue components of the bubbles
    red = f.nextInt(255);
    green = f.nextInt(255);
    blue = f.nextInt(255);
    
    //Maximum size of 200
    size = f.nextInt(200);
    
    //Minimum bubble speed of 1, and maximum of 10
    int speedR = f.nextInt(10 - 1) + 1;
    xSpeed = speedR;
    ySpeed = speedR;
    
    xLocation = startLocX;
    yLocation = startLocY;
  }
  //Getters - tell the user information about the object
    public int getXLocation(){
        return xLocation;
    }

    public int redColour(){
        return red;
    }

    public int greenColour(){
        return green;
    }

    public int blueColour(){
        return blue;
    }

    public int getSize(){
        return size;
    }

    public int getYLocation(){
        return yLocation;
    }
    
    //Setters - update information about the object
    public void move(){
        //changing the x and ylocation using the "Speed" given in x and yspeed
        xLocation += xSpeed;
        yLocation += ySpeed;
        //negate if bounces off a wall
        if(xLocation >= 500 || xLocation <= 0){
            xSpeed = -xSpeed;
        }
        if(yLocation >= 500 || yLocation <= 0){
            ySpeed = -ySpeed;
        }
    }
}
