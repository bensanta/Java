/**
 * Starts the Weather App - adds a WeatherPanel to the JFrame
 * 
 * TASK 1 is detailed below
 *
 * @author edited by Leah Wolf
 * @author original by Matt Memmo
 * @version February 2020
 */

import javax.swing.JFrame;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.io.IOException; 
import org.json.JSONException;
 
public class StartWeatherApp extends JFrame {
    
    //Create the window (frame), name it with the appropriate city.
    //Add the graphics panel to the frame.
    public StartWeatherApp(WeatherJSONParser p){
        WeatherPanel wp = new WeatherPanel(p);
        add(wp);
        setTitle("Weather in " + wp.city);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        
        /**
         * TASK 1:
         *  Register an API key and put it in quotes below
         *  Trace the use of the API through to the constructor of the WeatherJSONParser
         *  and into the HTTP request to OpenWeatherMap API
         */                 
        
        //Task 1: DONE

        String API = "60f83e663ab5943b5c0a2061c042bf47";   //your API Key goes here
        
        //Ask the user for their ZIP code
        Scanner in = new Scanner (System.in);
        System.out.println("Enter your zip code: "); 
        String zip = in.next();
        
        //System.out.println("Would you like to use Celsius or Fahrenheit? (c/f)");
        //String cOrF = in.next();
        //!Add a check to make sure the prefferred temperature is being used

        WeatherJSONParser parser = new WeatherJSONParser(API, zip);
        boolean gotInfo = false;
        
        try{
            //Check  that both requests are successful.
            if (parser.buildForecastInfo() && parser.buildWeatherInfo()){
                gotInfo = true;
            }
            
        }
        catch (MalformedURLException e){
            System.out.println("Bad URL: " + e.getMessage());
            return;
        }
        catch (IOException e){
            System.out.println("Something wrong with I/O: " + e.getMessage());
            return;
        }
        catch(JSONException e){
            System.out.println("JSON goofed: " + e.getMessage());
            return;
        }
        
        //If we have all the info, may open the app.
        if (gotInfo){
            new StartWeatherApp(parser);
        }
        else{
            System.out.println("Something went wrong with the request. Please run again.");
        }
    }
}
