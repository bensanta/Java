/**
 * JSON Parser for the OpenWeatherMap API
 * Currently contains methods to 
 *  1. parse the 5-day, every 3 hours forecast based on a ZIP code into an ArrayList of 
 *     DayForecast objects.
 *      call: 
 *          ArrayList<DayForecast> getForecastInfo(String URL)
 *          
 *  2. parse the current weather info into its city, description, and temperature.
 *      call: 
 *          void buildWeatherInfo(String URL)
 *          String getDescription()
 *          double getTempKelvin()
 *          String getCity()
 *              
 *  Other public methods:
 *      printForecastInfo()
 *      
 * @author Leah Wolf
 * @version February 2020
 */

import java.util.*;
import java.io.*;
import java.net.*; 
import org.json.*;
import org.json.JSONArray.*;

public class WeatherJSONParser {

    //Vars to hold externally useful information
    private String description;
    private String city;
    private double kTemp;
    private ArrayList<DayForecast> weathers;
    
    //Internally useful information
    private JSONObject fullResponse;
    private String weatherUrl;
    private String forecastUrl;
    
    /**
     * Constructor for objects of class WeatherJSONParser
     * Accepts an API key and a ZIP code as parameters.
     * 
     * Initializes all instance variables
     */
    public WeatherJSONParser(String API, String zip) {
        fullResponse = null;
        API = "60f83e663ab5943b5c0a2061c042bf47";


        description = "";
        city = "";
        kTemp = 0;
        weathers = new ArrayList<>();
        
        weatherUrl = "http://api.openweathermap.org/data/2.5/weather?zip="+zip+",&appid=" + API;
        forecastUrl = "http://api.openweathermap.org/data/2.5/forecast?zip=" + zip + ",&appid="+API;
        
    }
    
    //Returns the city the weather is relating to
    public String getCity(){
        return city;
    }
    
    //Returns the current temperature in Kelvin
    public double getTempKelvin(){
        return kTemp;
    }
    
    //Returns the description of the current weather
    public String getDescription(){
        return description;
    }
    
    //Returns the 5 day forecast - if it's early enough in the day that today is there,
    //removes today from the AL before returning.
    public ArrayList<DayForecast> getForecast(){
        if (weathers.size() > 5){
            weathers.remove(0);
        }
        return weathers;
    }
    
    //Prints the 5 day forecast
    public void printWeatherForecast(){
        for (int i = 0; i < weathers.size(); i++){
            System.out.println(weathers.get(i));
        }
    }
    
    /**
     * ALL METHODS BELOW ARE JSON PARSING METHODS
     * Parsing - to take information in 1 form (usually a string) and convert it into a more
     * useful form - in this case, objects (DayForecast) and simpler variables (city, 
     * description, and kTemp)
     */
    
    //Get the current weather info for the reqested zip code
    //Returns whether or not it was successful
    public boolean buildWeatherInfo() throws 
        MalformedURLException, IOException, JSONException {
       
        if (!getJSON(weatherUrl)){
            return false;
        }
        
        city = fullResponse.getString("name");
        System.out.println("City - " + fullResponse.getString("name"));
        
        JSONArray arr = fullResponse.getJSONArray("weather");
        description = arr.optJSONObject(0).getString("main"); // current weather description
     
        JSONObject tempHolder = fullResponse.getJSONObject("main");
        System.out.println("Temp - " + tempHolder.getString("temp")); //current temperature
        kTemp = Double.parseDouble(tempHolder.getString("temp"));
    
        return true;
    }

    //Gets the 5 day forecast info for the requested zip code.
    //Returns whether or not it was successful
    public boolean buildForecastInfo() throws 
        MalformedURLException, IOException, JSONException {        

        if (!getJSON(forecastUrl)){
            return false;
        }
        
        //Finds the city corresponding to the ZIP code.
        city = fullResponse.getJSONObject("city").getString("name");
        System.out.println("City - "+ city); 
        
        //Build up arraylist of all the weather in the forecast - stored by day.
        JSONArray arr = fullResponse.getJSONArray("list");
        weathers = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++){
            JSONObject data = arr.getJSONObject(i);
            DayForecast w;
            String date = data.getString("dt_txt").substring(0,10);
            
            //If we're looking at a day that isn't already in the AL
            if (weathers.size() == 0 || !weathers.get(weathers.size()-1).getDate().equals(date)){
                w = new DayForecast(date);
                weathers.add(w);
            }
            else{
                w = weathers.get(weathers.size()-1);
            }
            //Add the description to the day's forecast
            JSONArray weather = data.getJSONArray("weather");
            String description = weather.getJSONObject(0).getString("main");
            w.addDescription(description);
            
            //Add the temperature to the day's forecast
            JSONObject temps = data.getJSONObject("main");
            double temp = Double.parseDouble(temps.getString("temp"));
            w.addTemp(temp);
        }
        return true;
    }
    
    /**
     * Gets the requested JSON by:
     *      1. Opening an HTTP connection, 
     *      2. Sending the GET request, and 
     *      3. Reading in the response as a new JSONobject.
     * Private helper method - called by buildWeatherInfo and getForecastInfo methods 
     * to acquire the requested information in JSON format.
     * 
     * NOTE: Can be called by additional methods - simple reads the JSON into the instance 
     * variable: fullResponse.
     * 
     * Returns whether or not it was successful
     */
    private boolean getJSON(String url) throws 
        MalformedURLException, IOException, JSONException {
        
        //Build and send HTTP GET request
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        System.out.println("\nSending 'GET' request to URL : " + url);

        //Get response - hopefully a 200 - OK response
        //If the response is in the 400s, that means there was an error somewhere.
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        if (responseCode == 200){
            //Read in the response, line by line - store in a JSON object
            String inputLine;
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {  //while there's another line to read
                response.append(inputLine);
            }
            in.close();
    
            fullResponse = new JSONObject(response.toString());        
            return true;
        }
        else{
            return false;
        }
    }
}
