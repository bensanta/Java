/**
 * Given multiple datapoints for descriptions of the weather and forecasted temperature,
 * stores information about the most likely description of the weather and forecasted 
 * temperature for a given date.
 *
 * @author Leah Wolf
 * @version February 2020
 */

import java.util.ArrayList;

public class DayForecast {
    
    private static String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                                      "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String date;
    private ArrayList<String> descriptions;
    private ArrayList<Double> temps;
    private double minTemp;
    private double maxTemp;
  
    private int numDataPoints;
    
    //Create a new DayForecast object based on a date.
    public DayForecast(String date){
        this.date = date;
        descriptions = new ArrayList<>();
        temps = new ArrayList<>();
    }
    
    //From all of the descriptions throughout the forecast of the day, 
    //returns the most commonly occurring one.
    public String getDescription(){
        String mostOften = "";
        ArrayList<String> unique = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();
        
        //Create an arraylist of the unique descriptions for the day
        //Count all the occurrences of each unique description throughout the day
        for (String d: descriptions){
            if (!unique.contains(d)){
                unique.add(d);
                counts.add(1);
            }
            else{
                int i = unique.indexOf(d);
                counts.set(i, counts.get(i) + 1);
            }
        }
        //Determine the description that occurs most frequently throughout the day.
        int most = 0;
        for (int i = 0; i < unique.size(); i++){
            if (counts.get(i) > most){
                most = counts.get(i);
                mostOften = unique.get(i);
            }
        }
        return mostOften;
    }
    
    //Returns the minimum forecasted temperature for the day
    public double getMinTemp(){
        return minTemp;
    }
    
    //Returns the maximum forecasted temperature for the day
    public double getMaxTemp(){
        return maxTemp;
    }
    
    //Returns the date for the day in readable format Month date, year
    //Example: 2020-02-17 --> Feb 17, 2020
    public String getFormattedDate(){
        String year = date.substring(0,4);
        Integer m = Integer.parseInt(date.substring(5, 7));
        String month = MONTHS[m-1];
        String day = date.substring(8, 10);
        return month + " " + day + ", " + year;
    }
    
    //Returns the date in the original format yyyy-mm-dd
    //Example: 2020-02-17
    public String getDate(){
        return date;
    }
    
    public String toString(){
        
        return getDate() + " - " + getDescription() + ", MIN: " + minTemp + ", MAX: " + maxTemp; 
    }
    
    //Adds a description datapoint for the day
    public void addDescription(String d){
        descriptions.add(d);
    }
    
    //Adds a temperature datapoint for the day
    //Adjusts minTemp and maxTemp appropriately.
    public void addTemp(Double t){
        temps.add(t);
        if (temps.size() == 1){
            minTemp = t;
            maxTemp = t;
        }
        else{
            setMinMax(t);
        }
    }
    
    //Based on temperature t, determines whether there is a new min or max for the day
    private void setMinMax(double t){
        if (t < (minTemp)){
            minTemp = t;
        }
        if (t > maxTemp){
            maxTemp = t;
        }
    }
}
