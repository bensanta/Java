/**************************************************************************
 * Author: Benjamin Santa 
 * Date: 27 January, 2022
 * Course: GOA CS2: Java
 * 
 *
 * The program generates a random number from 1 to 10. If the user 
 * guesses the random number, the program tells the user they won; if
 * they don't guess it, the program gives the user another 2 attempts, 
 * 3 attempts in total. At the end of the program the user has the 
 * option to play again.
 *************************************************************************/


import java.util.*;

public class GuessingGame {
    public static void main(String[] args){
        //Using Random
        Random rand = new Random(); //instance of Random Class 
        int min = 1;
        int max = 4; //Range for random values between 0-10
        int randNum = (int)Math.floor(Math.random()*(max-min+1)+min);
        
        
        //Number of times the user has attempted to guess
        int attempts = 0;
        
        //After 3 incorrect attempts, the program stops
        while (attempts < 3){
            //Asks user for an integer input
            Scanner sc = new Scanner(System.in);
            System.out.println("Guess a number: ");
            int userNum = sc.nextInt();
            //Requires user to guess between 1 and 10,
            //otherwise the program stops
            if (userNum > 10){
                System.out.println("Please guess a number between 1 and 10");;
                attempts = 3;
            }
            
            if (randNum == userNum){
                //if correct, the program ends
                System.out.println("Yes, You got it!");
                attempts = 3;
            }
            else{
                System.out.println("Nope, That's not it!");
                //adds one to the amount of attempts
                attempts += 1;
            }

            //Play again...
            if (attempts == 3){
                Scanner play = new Scanner(System.in);
                System.out.println("Would you like to play again? (y/n) ");
                String playAgain = play.nextLine();
                if (playAgain.equals("y")){
                    attempts = 0;
                }
            }
        }
    }
}
