int c, x, y;
boolean facingRight;

void setup() {
  size(500, 500);
  noStroke();
}


void draw(){
 frameRate(12);
 background(255);        //white background
  fill(c);                //ready to draw with the color c
  rect(x, y, 100, 100);   //draw the rectangle based on the x & y vlues
  
  //Move the rectangle for next time
  if (facingRight){
    x++;
    if (x + 100 > width){  //Has reached the right edge
      facingRight = false;
    }
  }
  else{
    x--;
    if (x < 0){   //Has reached the left edge
      facingRight = true;
    }
  }
}

void mousePressed(){
//rect(mouseX, mouseY, 100, 100);
x = mouseX;
y = mouseY;
}

void mouseDragged(){
  c += 10; //Adds 10 t the colour
}

void mouseReleased(){
  c = 0; //resets the colour back to 0
}

void keyPressed(){
  if ((keyPressed == true) && (key == CODED) && (keyCode == RIGHT)){
        facingRight = true;
    }
  else if ((keyPressed == true) && (key == CODED) && (keyCode == LEFT)){
    facingRight = false;
  }

}
