/**
 * The program asks for a zip code and displays the weather for that zip code.
 * Once the user enters a zip code, an empty panel will show up.
 * The program will then ask the user if they would like the temperature in celsius or fahrenheit.
 * If the user enters c, the program will display the temperature in celsius.
 * If the user enters f, the program will display the temperature in fahrenheit.
 * If the user enters anything else, the program will display the temperature in celsius.
 * The user can also change what unit they would like to see the temperature in, by simply typing it into the command
 * line even while the program is running.
 * 
 * @author edited by Benjamin Santa
 * @author edited by Leah Wolf
 * @author original by Matt Memmo
 * @version 20220420
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.lang.Math.*; //to be able to round

import java.util.*;
import java.io.*;

public class WeatherPanel extends JPanel implements Runnable, MouseListener {
     
    
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

    public int kelvinToCelcius(double kelvin) {
        int rounded = (int) Math.round(kTemp - 273.15);
        return rounded; //returns the temperature in celsius
    }

    public int kelvinToFahrenheit(double kelvin) {
        int rounded = (int) Math.round(1.8 * (kTemp - 273.15) + 32);
        return rounded; //returns the temperature in fahrenheit
    }
    
    public void paint(Graphics g) {
        
        super.paint(g);
        
        //Fill the screen - Background
        g.setColor(Color.white);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        
        //Creates the title of the panel - top left
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("5 Day Forecast", 15, 50);
        
        //Draws the labels for min and max on the left side of the screen
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Min:", 0, BOARD_HEIGHT-275);
        g.drawString("Max", 0, BOARD_HEIGHT-275 + 25);
        //Draws out the next 5 days of the forecast

        //Ask the user if they want to use celsius or fahrenheit using a scanner
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to use celsius or fahrenheit? (c/f) ");
        String userInput = input.nextLine();
        if (userInput.equals("C") || userInput.equals("c")) {
            celcius = true;
        } else if (userInput.equals("F") || userInput.equals("f")) {
            celcius = false;
        } else {
            celcius = true;
        }
        
        //draws the first image status - Bottom half
        g.drawImage(getIcon(desc), BOARD_WIDTH-150, BOARD_HEIGHT-200, 100, 100, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 45));
        g.drawString(city, 50, BOARD_HEIGHT-150);


        if (celcius == true){
            //Current temperature in celsius
            g.drawString((kelvinToCelcius(kTemp) + "°C"), 50, BOARD_HEIGHT-100);

            for (int i = 0; i < 5; i++){

                //Technically useless but it's better for organisation
                //Variables for the min and max temperatures converted to celcius
                double minTemp = forecast.get(i).getMinTemp() - 273.15;
                double maxTemp = forecast.get(i).getMaxTemp() - 273.15;

                //Draws the date for each day
                g.setFont(new Font("Arial", Font.PLAIN, 12));
                g.drawString(forecast.get(i).getDate(), BOARD_WIDTH-(i*100)-65, BOARD_HEIGHT-350);

                //Draws the forecasted icon for each day
                g.drawImage(getIcon(forecast.get(i).getDescription()), BOARD_WIDTH-(i*100)-50, BOARD_HEIGHT-350, 50, 50, null);

                //Draws the min and max temperatures for each day
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.drawString(Math.round(minTemp) + "°C", BOARD_WIDTH-(i*100)-50, BOARD_HEIGHT-275);
                g.drawString(Math.round(maxTemp) + "°C", BOARD_WIDTH-(i*100)-50, BOARD_HEIGHT-275 + 25);
                }
            }
        else{
            //current temperature in fahrenheit
            g.drawString((kelvinToFahrenheit(kTemp) + "°F"), 50, BOARD_HEIGHT-100);

            for (int i = 0; i < 5; i++){
                //Technically useless but it's better for organisation
                //Variables for the min and max temperatures converted to celcius
                double minTemp = 1.8 * (forecast.get(i).getMinTemp() - 273.15) + 32;
                double maxTemp = forecast.get(i).getMaxTemp() - 273.15;

                //Draws the date for each day
                g.setFont(new Font("Arial", Font.PLAIN, 12));
                g.drawString(forecast.get(i).getDate(), BOARD_WIDTH-(i*100)-65, BOARD_HEIGHT-350);

                //Draws the forecasted icon for each day
                g.drawImage(getIcon(forecast.get(i).getDescription()), BOARD_WIDTH-(i*100)-50, BOARD_HEIGHT-350, 50, 50, null);

                //Draws the min and max temperatures for each day
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.drawString(Math.round(minTemp) + "°F", BOARD_WIDTH-(i*100)-50, BOARD_HEIGHT-275);
                g.drawString(Math.round(maxTemp) + "°F", BOARD_WIDTH-(i*100)-50, BOARD_HEIGHT-275 + 25);
                }
            }
        
            
            





        /**
         * That's all! Feel free to pop down to the event methods below if you
         * would like to add interaction to your Weather App
         */
        Toolkit.getDefaultToolkit().sync();
        g.dispose();}

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
