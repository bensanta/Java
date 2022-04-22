/**
 * Bouncing Bubbles Starter Code
 * Contains the base code needed to draw graphical components in a Processing Project
 * 
 * You will need to add code in the following functions:
 *  - draw
 *  - mousePressed
 *
 * You will need to fill in the code and functionality in its entirety in the Bubble class.
 * 
 * @author Leah Wolf
 * @version June 2020
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

  /**
   * Here's where you should draw all the bubbles
   */
   color c;
  //color c = color(random(255),random(255),random(255));
  //int r =  randomSeed(255);
  //int g = (int) random(255);
  //int b = (int) random(255);
  //color c = color(r,g,b);
  //fill(c);
  
   //fill(random(255),random(255),(random(255));
   
   
   for(int i = 0; i < bubs.size(); i++){
     //Sets the bubble to a random color
     
     c = color(bubs.get(i).redColour(), bubs.get(i).greenColour(), bubs.get(i).blueColour());
     fill(c);
     //c = color(bubs.get(i).redColour(), bubs.get(i).greenColour(), bubs.get(i).blueColour());
     
     
     //Creates the bubble
     noStroke(); //Removes the defaut stroke from the bubbles
     circle(bubs.get(i).getXLocation(), bubs.get(i).getYLocation(), bubs.get(i).getSize());
     
     //Move
     bubs.get(i).move();
   }
                
    //Additional Feature - Once there are 10 bubbles in the ArrayList, it will remove a random bubble
    if(bubs.size() > 10){
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
