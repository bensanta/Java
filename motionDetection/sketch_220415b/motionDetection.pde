//Imported the "sound" library, to be able to play sound
import processing.sound.*;

//Imported the "video" library, to be able to Capture video
import processing.video.*;  

Capture cam;  //2 Captures so you can compare images 
Capture cam2;

int count;
PImage firstImg;
PImage secondImg;

SoundFile file;

void setup() {
  frameRate(20);   //Looking at 20 frames per second. 
  size(600, 300);  //Window is large enough for 2 side-by-side images
   String[] cameras = Capture.list();
   
   //this loads the file based on the file name
   file = new SoundFile(this,"ding.aiff");
   file.amp(.75); //Sets the sound of the file to 75%

  //If a camera isn't available, then give an error msg
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
    // Create 2 separate places to store video frames
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

    float threshold = 150;  //Threshold of what counts as a motion, and what doesn't
                           
    float changeOfRGB = 0.0;
    // no need to look at all pixels
    // approx 1/5th of them will be enough to detect a change
    // This loop will look at every 5th pixel in the images.
    for (int i = 0; i < firstImg.pixels.length; i+=5) {

      //Get the red component of each corresponding pixel in the two images
      float r = red(firstImg.pixels[i]);
      float r2 = red(secondImg.pixels[i]);
      float rChange = r2 - r;
      
      //Get the green component of each corresponding pixel in the two images
      float g = green(firstImg.pixels[i]);
      float g2 = green(secondImg.pixels[i]);
      float gChange = g2 - g;
      
      //Get the blue component of each corresponding pixel in the two images
      float b = blue(firstImg.pixels[i]);
      float b2 = blue(secondImg.pixels[i]);
      float bChange = b2 - b;
      
      //Adding together the difference between the RGB values, to later check for motion
      changeOfRGB = rChange + gChange + bChange;
      
  
    }
    println(changeOfRGB); //Prints the current changeOfRGB Value
    
    if (changeOfRGB >= threshold || changeOfRGB <= -threshold){
      //resets the two frames
      firstImg = null;
      secondImg = null;
      
      count = 0;
      println("There has been movement detected");
      file.play(); //plays the soundfile
    }
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
