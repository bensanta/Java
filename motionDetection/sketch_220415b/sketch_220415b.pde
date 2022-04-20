
//Don't forget to import the library from the "Sketch" dropdown
import processing.video.*;  

Capture cam;  //2 Captures so you can compare images 
Capture cam2;

int count;
PImage firstImg;
PImage secondImg;

void setup() {
  frameRate(20);   //Looking at 20 frames per second. 
  size(600, 300);  //Window is large enough for 2 side-by-side images
   String[] cameras = Capture.list();

  if (cameras == null) {
    println("Failed to retrieve the list of available cameras, will try the default...");
   
  } else if (cameras.length == 0) {
    println("There are no cameras available for capture.");
    exit();
  } else {
    println("Available cameras:");
    printArray(cameras);
  
    // The camera can be initialized directly using an element
    // from the array returned by list():
    //Create 2 separate places to store video frames
    cam = new Capture(this, 320, 240,  cameras[0], 30);
    cam.start();
  
    cam2 = new Capture(this, 320, 240,  cameras[0], 30);
    cam2.start();
  }
  //Initialize all other global variables.
  count = 0;
  firstImg = null;
  secondImg = null;
}

void draw() {

  loadVideos();
  
  //Check that we have images for both frames
  if (firstImg != null && secondImg != null) {
    image(firstImg, 0, 0, 200, 200);
    image(secondImg, 300, 0, 200, 200);
    loadPixels();
    firstImg.loadPixels();
    secondImg.loadPixels();

    float change = 0;
    float threshold = 100;  //YOU WILL NEED TO CHANGE THIS NUMBER
                            //VALUE IS LIKELY MUCH HIGHER

    // no need to look at all pixels
    // approx 1/5th of them will be enough to detect a change
    // This loop will look at every 5th pixel in the images.
    for (int i = 0; i < firstImg.pixels.length; i+=5) {

      //Get the red component of each corresponding pixel in the two images
      float r = red(firstImg.pixels[i]);
      float r2 = red(secondImg.pixels[i]);
     
      /**
        TASK 1
        Compare r and r2 - Do something with the difference!
        You'll want to do something similar with the blue & green components, too.
        Can you store the total change in color components from firstImg to secondImg?
      */

  
    }
    println(change);
    
    /** 
      TASK 2
      once change is greater than threshold, this means
      motion has been detected, you will want to:
         1) set firstImg and secondImg equal to null 
         2) set count = 0
         3) print that there has been movement to the console
         4) play a sound
    */

    updatePixels();
  }
}

//Load videos each time we need to refresh the lefthand one with a new original image.
void loadVideos(){
  
  //If we need to load the lefthand video
  if (count < 2 && cam.available()) {
    cam.read();
    firstImg = cam;
    count++;
  } 
  else {
    //If we need to load the righthand video
    if (cam2.available()) {
      cam2.read();
      secondImg = cam2;
    }
  }
}

//Pseudocode
/*
*
*
*
*/
