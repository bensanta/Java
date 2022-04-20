import java.util.Random;

/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bubble
{
    // instance variables
    //colors
    private int red;
    private int green;
    private int blue;

    private int size;
    private int xSpeed;
    private int ySpeed;

    private int xLocation;
    private int yLocation;

    //Constructors
    //call this: Bubble b = new Bubble("Bpb", "Barker);
    public Bubble(int startLocX, int startLocY)
    {
        // initialise instance variables
        Random rand = new Random();
        int upperbound = 255;
        red = rand.nextInt(upperbound);
        green = rand.nextInt(upperbound);
        blue = rand.nextInt(upperbound);
        
        size = rand.nextInt(200);

        xSpeed = rand.nextInt(10);
        ySpeed = rand.nextInt(10);

        xLocation = startLocX;
        yLocation = startLocY;
    }
    
    //Methods
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
        if(xLocation >= 600 || xLocation <= 0){
            xSpeed = -xSpeed;
        }
        if(yLocation >= 400 || yLocation <= 0){
            ySpeed = -ySpeed;
        }
    }
}
