/**************************************************************************
 * Author: Benjamin Santa 
 * Date: 1 February, 2022
 * Course: GOA CS2: Java
 * 
 * The program asks the user for a temperature, and asks if they want to
 * convert it to Celsius of Fahrenheit. After being converted,
 * it prints out the converted value.
 *************************************************************************/

 // Import Scanner to allow user input
import java.util.Scanner;

public class TemperatureConverter {

    // Decleration of method titled "convertCtoF"
    // Converts Celsius to Fahrenheit
    public static double convertCtoF(double temp) { // "temp" parameter is user defined using the scanner
        System.out.println(1.8 * temp + 32); // Formula to convert from Celsius to Fahrenheit
        return 0; // returns 0 to show the method has reached it's end
        
    }

    public static double convertFtoC(double temp) {
        System.out.println(((temp - 32) / 1.8)); // Formula to convert Fahrenheit to Celsius
        return 0;
        
    }
    public static void main(String args[]){

        // Asks user the temperature
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a temperature: ");
        int temp = sc.nextInt();
        
        // Asks user measurement type (F or C)
        Scanner measureType = new Scanner(System.in);
        System.out.println("Are you using Fahrenheit or Celsius? (f/c) ");
        String type = measureType.nextLine();
        // If Fahrenheit use the FtoC conversion method
        if (type.equals("f")){
            convertFtoC(temp);
        }
        // If Celsius use the CtoF conversion method
        else if (type.equals("c")){
            convertCtoF(temp);
        }
        // If anything else other then "f" or "c" - gives
        // instructions on how to use the program
        else {
            System.out.println("Please choose either 'f' or 'c'");
        }
        
    }
}
