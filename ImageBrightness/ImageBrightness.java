/**
 * A class used to calculate the average luminance of an image input to the program
 * by iterating through each pixel in the image, calculating its luminance, and using
 * the results to calculate the average luminance in the image.
 *
 * @author edited by Leah Wolf
 * @author edited by Benjamin Santa
 * @author original by Matt Memmo
 * @version 20220225
 */
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
     
public class ImageBrightness{
     
    public static void lum(File imageFile) {
        BufferedImage img = null;
        
        try{
            img = ImageIO.read(imageFile);
            
            // declared and initialized the variables to be used later
            int width = 0;
            int height = 0;

            //if a file is chosen, get the width and height
            if (img != null){
                width = img.getWidth();
                height = img.getHeight();
            }
            
            double cummulativeLuminance = 0.0; // declared and initialized the variable to be used later

            //Nested for-loops to go through each pixel
            for (int col = 1; col < width; col++){
                for (int row = 1; row < height; row++){
                    Pixel p = new Pixel(img.getRGB(col, row));  //Pixel at a specific place in the image
                    //gets the red, green, and blue value of the pixels
                    double red = p.getRed();
                    double green = p.getGreen();
                    double blue = p.getBlue();
                    //Relative Luminance Formula Source - https://www.w3.org/WAI/GL/wiki/Relative_luminance
                    cummulativeLuminance = cummulativeLuminance + 0.2126 * red + 0.7152 * green + 0.0722 * blue;
                }
            }
            //getting the avg by dividing the cummulativeLuminance value with the number of pixels
            double averageLuminance = cummulativeLuminance / (width * height);
            
            //prints out the averageLuminance
            System.out.println("Average Luminance of chosen image: " + averageLuminance);
            

            
        }
        catch (IOException e){
            System.out.println(e + "\nChoose a file in your directory! " +
                "Don't try to create a new one...");
        }
    }
}