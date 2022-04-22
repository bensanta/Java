/**
 * 
 * Bubble class skeleton. You will need to add:
 *   - your own instance variables,
 *   - parameters and functionality to the Constructor
 *   - methods for updating the Bubble as needed
 */
 
import java.util.*;

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
  
  
  
  // Your constructor here...
  // Add whatever parameters you need
  public Bubble(int startLocX, int startLocY){
    //Initialise instance variables
    int colourR = (int) random(255);
    red = colourR;
    green = colourR;
    blue = colourR;
    
    size = (int) random(200);
    
    int speedR = (int) random(1, 10);
    xSpeed = speedR;
    ySpeed = speedR;
    
    xLocation = startLocX;
    yLocation = startLocY;
  }
  
  //Your methods here:
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
