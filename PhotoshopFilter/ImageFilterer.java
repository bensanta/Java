        
/**
 * Image Filterer to have functionality to 
 *  invert an image,
 *  mirror an image left to right, 
 *  mirror an image diagonally,
 *  create an image from edges detected in the original.
 *
 * @author edited by Leah Wolf
 * @author Finalized by Benjamin Santa
 * @author original by Matt Memmo
 * @version 20220228
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.color.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class ImageFilterer {
      
    private Pixel[][] imgPixels;
    private Pixel[][] newPixels;
    private int width = 0;
    private int height = 0;

    public static String newImgName = "z";

    /**
     * PRE: origFile is a String filename of a picture file (gif, jpg, or png)
     * POST: imgPixels stores the corresponding components for each pixel in the image.
     *      imgPixels   -> 2D array of each pixel in the image  
     */
    public void splitRGBs(String origFile) {
        BufferedImage img = null;

        File myFile = new File(origFile);   
        try{
            img = ImageIO.read(myFile);

            if(img != null){
               width = img.getWidth();
               height = img.getHeight();
            }
            System.out.println("Width " + width + " " +  height);
            
            imgPixels = new Pixel[height][width];
            newPixels = new Pixel[height][width];
            
            //Read the pixels of the image into a 2D array of Pixel objects.
            for(int row = 0; row < height; row++){
                for (int col = 0; col < width; col++){ 
                    Pixel p = new Pixel(img.getRGB(col, row));
                    imgPixels[row][col] = p;
                }
            }    
        } catch(Exception e){
            System.out.println("Exception caught: " + e);
        }
    }

    /**
     * Invert the image's pixels!
     */
    public void invertImage(){
        BufferedImage img = null;
        //make some changes to the pixels
        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                //CODE GOES HERE
                //Calculate the Pixel Value
                int pixelValue = (imgPixels[row][col].getAlpha() << 24) //ALPHA component occupies the bit position
                                                                        //from index 24 to 31, so we will left shift
                                                                        //the alpha value by 24 positions
                | (imgPixels[row][col].getRed() << 16)  //RED component occupies the bit position
                                                        //from index 16 to 23, so we will left shift
                                                        //the alpha value by 16 positions
                | (imgPixels[row][col].getGreen() << 8) //GREEN component occupies the bit position
                                                        //from index 8 to 15, so we will left shift
                                                        //the alpha value by 8 positions
                | (imgPixels[row][col].getBlue());  //BLUE component doesn't need to shift

                //Calculate the inverted pixel value
                int invPixelValue = 255 - pixelValue;

                //Create new pixel object with inverted pixel value
                newPixels[row][col] = new Pixel(invPixelValue);
            }
        } 

    }
    
    /**
     * Mirrors the left side of the image onto the right.
     */
    public void leftRightMirror(){
        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                newPixels[row][width - col - 1] = imgPixels[row][col];
            }
        }
        
    }
    
    /**
     * Mirrors a square part of the picture from bottom left to top right around a mirror placed on the 
     * diagonal line. This will copy the triangular area to the left and below the diagonal line. 
     * This is like folding a square piece of paper from the bottom left to the top right, 
     * painting just the bottom left triangle and then (while the paint is still wet) folding the paper up 
     * to the top right again. The paint would be copied from the bottom left to the top right
     * 
     */
    public void diagMirror(){
        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                newPixels[height - row - 1][width - col - 1] = imgPixels[row][col];
            }
        }
        
    }
    
    /**
     * Compare the color at the current pixel with the pixel in the next column to the right. 
     * 
     * If the colors differ by more than some specified amount, this indicates that an edge has been 
     * detected and the current pixel color should be set to black. Otherwise, the current pixel is not 
     * part of an edge and its color should be set to white. 
     * 
     * You can use the Pixel class' colorDistance method to calculate the difference between two colors. 
     * 
     * Compare the current pixel with the one below and sets the current pixel color to black when the 
     * color distance is greater than the specified edge distance.
     */
    public void edges(){
        int edgeDist = 9;  //You can change this value to determine the best value.
        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                if(row != height - 1){
                    double colorDistance = Pixel.colorDistance(imgPixels[row][col], imgPixels[row +1 ][col]);
                    if (colorDistance > edgeDist){
                        newPixels[row][col] = new Pixel(0);
                    }
                    else{
                        newPixels[row][col] = imgPixels[row][col];
                    }
                } else {
                    newPixels[row][col] = imgPixels[row][col];
                }
            }
        }
              
    }
    
    
    /**
     * From the newPixels array, create a new image.
     */
    public void createEditedImage(String fileName){

        //create new image using new values
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){                
                Pixel p = newPixels[row][col];              
                img.setRGB(col, row, p.backToInt());        
            }
        }
        File f = new File(fileName + ".jpg");
        try{
            ImageIO.write(img, "JPEG", f);
            newImgName = fileName + ".jpg";             
        } catch(Exception e){ 
            System.out.println("Exception caught: " + e);
            System.out.println("Image could not be saved.");

        }   
    }
}