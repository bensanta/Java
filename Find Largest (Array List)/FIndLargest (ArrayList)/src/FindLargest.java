import java.util.*;

/**
 * 
 * The program uses for-loops to iterate through the "temps" list
 * and "months" list, to find the month with the highest temperature value in Degrees Celsius
 * and print them out.
 * 
 * Edits Suggested: Can easily be modified ot use user defined parameters
 * or even user input for the temperatures
 *
 * @author Benjamin Santa
 * @version 20220310
 */

public class FindLargest {
    

    public static int FindLargest() {
        //Declaring the ArrayList called "Months"
        
        
        //Created a new ArrayList called "months" to store the names of the months
        List months = new ArrayList();
        //Added each month
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");

        //Created a new ArrayList called "temps" to store the temperature values
        List temps = new ArrayList();

        //Added the temps
        temps.add(7);
        temps.add(12);
        temps.add(21);
        temps.add(26);
        temps.add(29);
        temps.add(32);
        temps.add(35);
        temps.add(31);
        temps.add(24);
        temps.add(20);
        temps.add(13);
        temps.add(4);

        //Declared and initialized a variable to store the
        //index of the highest temp in the array
        int highestTempLocation = 0;
        //Declared and initialized a variable to store the
        //Highest temperature value from the temps array
        int highestTemp = (int) temps.get(highestTempLocation);
        
        // Iterates over the array
        for (int i = 0; i < temps.size(); i++){
            //Checks to see if the current temp value is higher than the current highest
            if ((int) temps.get(i) > highestTemp){
                highestTemp = (int) temps.get(i);
                //sets the new index and value, if a new high is found
                highestTempLocation = i;
            }
        }
        //Declaring and initialized a variable to store the month
        //corresponding to the highest temp
        String highestTempMonth = (String) months.get(highestTempLocation);

        //Prints out the answer
        System.out.println("The highest temperature throughout the year is " + highestTemp + "degrees Celsius, and it is in " + highestTempMonth);

        return 0;
    }

    public static void main(String[] args) {
        FindLargest();
    }
}
