/**
 * WeatherPanel is the engine of the Weather App.
 * It creates and displays all of the graphical components of the Weather app.
 * 
 * TASKS 2-4 and the optional task 5 are detailed below.
 *
 * @author edited by Leah Wolf
 * @author original by Matt Memmo
 * @version February 2020
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

import java.util.*;
import java.io.*;

public class WeatherPanel extends JPanel implements Runnable, MouseListener {
    
    /**
     *  NOTE:
     *  Keep track of these instance variable names -
     *  They are used throughout the program
     */                 
    
    private double kTemp = 0.0; //temperature in Kelvin
    private String desc = "";
    protected String city = "";
    private boolean celcius;

    private ArrayList<DayForecast> forecast;

    //Other instance variables for creating the graphics
    public final int BOARD_WIDTH = 500;
    public final int BOARD_HEIGHT = 500;
    private BufferedImage img;
    private String message = "";
    
    private Thread animator;

    //Set up all the content for the Panel
    public WeatherPanel(WeatherJSONParser parser) {
        addMouseListener(this);
        setFocusable(true);
        
        celcius = true; 
        forecast = parser.getForecast();  //Gets 5-day forecast
        parser.printWeatherForecast();    //Prints it to the Terminal
        message = "5 Day Forecast";
        
        city = parser.getCity();          //Gets all the data for the current
        desc = parser.getDescription();   //weather in the requested location
        kTemp = parser.getTempKelvin();
        img = getIcon(desc);
        
        if (animator == null) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);
    }
    
    /**
     * TASK 2
     * Write two methods to convert a parameter in degrees Kelvin to 
     * 1. degrees Celcius
     * 2. degrees Fahrenheit
     * return the converted temperature from both methods.
     * Use these methods to assist with Tasks 3 & 4
     */

    
    
    /**
     * All the drawing happens in paint - like in your Creative Drawing project
     * Recall that you can use the documentation to look up different methods
     * https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
     */
    public void paint(Graphics g) {
        
        super.paint(g);
        
        //Fill the screen 
        g.setColor(Color.white);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        
        
        /**
         * TASK 3:
         * Display the current state of the weather. All the values needed are stored in
         * instance variables of this class: city, desc, kTemp
         *  - The city
         *  - The current temperature (in the unit specified by the user - use the methods
         *    you wrote above)
         *  - The icon associated with the current weather description 
         *      (use the getIcon method provided above)
         */
        
        //Draw the appropriate weather icon
        //Here's an example of how to do it: 
        //params: image, x, y, width, height, null
        //g.drawImage(getIcon(desc),0, 0, 100, 100, null);
        
        /**
         * TASK 4: 
         * Display the 5 Day Forecast.
         * Iterate over the forecast ArrayList and for each day, display 
         *  - the appropriate weather icon (use the getIcon method provided below)
         *  - minTemp, maxTemp (in the unit specified by the user)
         *  - and date
         * Use the methods in the DayForecast class to acquire the information you need.
         */
        

        
        
        
        
        /**
         * That's all! Feel free to pop down to the event methods below if you
         * would like to add interaction to your Weather App
         */
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * TASK 5 (Optional):
     * If you want to allow the user to click and change something, use the x and y values 
     * of the mouse press here to choose whether to toggle a boolean variable if the 
     * mouse press is in the button. In the paint method above, choose what to do based on 
     * the value of the boolean variable. 
     * There is a variable all ready for you to use, called celcius - currently true. 
     */
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //Only do something if the User clicks the C or F buttons
        if (x >= BOARD_WIDTH - 40 && x <=BOARD_WIDTH - 20 && y >= 5 && y <= 25){
            celcius = true;
        }
        else if (x >= BOARD_WIDTH - 20 && x <=BOARD_WIDTH && y >= 5 && y <= 25){
            celcius = false;
        }
    }
    
    public void mouseReleased(MouseEvent e) { }
    
    public void mouseEntered(MouseEvent e) { }
    
    public void mouseExited(MouseEvent e) { }
    
    public void mouseClicked(MouseEvent e) { }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
    
    //Based on a description, procure the corresponding icon
    //If the image doesn't exist, returns the Clouds icon.
    private BufferedImage getIcon(String description){
        //Ensure we have an image for the current description
        boolean found = false;
        String[] possible = {"Rain", "Snow", "Clear", "Sunny", "Clouds"};
        for (String d : possible){
            if (d.equals(description)){
                found = true;
                break;
            }
        }
        
        String icon = "wicons/Clouds.png";  //Default for not having an image
        if (found){
            icon = "wicons/" + description + ".png"; //Found the image
        }
        
        BufferedImage image = null;
        try{
            image = ImageIO.read(this.getClass().getResource(icon));
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return image; 
    }

    public void run() {
    
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        int animationDelay = 50;  
        long time = System.currentTimeMillis();
        
        while (true) {
            // spriteManager.update();
            repaint(); //Calls the paint method above (along with some other stuff)
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0,time - System.currentTimeMillis()));
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }  
}
