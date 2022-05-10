/**
 * This program is the processed version of the Bouncing Bubbles program
 * Simply, to operate the program, press the start button, and then do click on the screen, 
 * a bubble will be created, with a random shape and size. THe bubbles will move, and bounce off the sides of the screen.
 * Once you create 10 bubbles, the program will remove a random one from the ArrayList
 * 
 * @author Benjamin Santa
 * @author Leah Wolf
 * @version 20220425
 */
import java.util.*;
ArrayList<Bubble> bubs;

void setup() {
  size(500, 500);
  background(0);
  bubs = new ArrayList<Bubble>();
}



void draw() {
  background(0);
   
   for(int i = 0; i < bubs.size(); i++){
     //Defines the bubble's random color
     color c = color(bubs.get(i).redColour(), bubs.get(i).greenColour(), bubs.get(i).blueColour());
     //Sets the bubbles color, using the above defined variable
     fill(c);
     
     noStroke(); //Removes the defaut stroke from the bubbles
     //Creates the bubble
     circle(bubs.get(i).getXLocation(), bubs.get(i).getYLocation(), bubs.get(i).getSize());
     
     //Move
     bubs.get(i).move();
     }

    int maxNumOfBubs = 10; //Max amount of bubs

    /**
    * Additional Feature - Once the # of bubbles reaches the maxNumOfBubs amount in the
    * ArrayList, it will remove a random bubble
    */
    if(bubs.size() > maxNumOfBubs){
      int random = (int)(Math.random() * bubs.size());
      bubs.remove(random);
     }
}


void mousePressed() {
  
  //Getting location
  int x = mouseX;
  int y = mouseY;
  
  /**
   * Based on the location of the mouse press (x,y), make a new bubble and
   * add it to the ArrayList.
   */
   bubs.add(new Bubble (x, y));
}
