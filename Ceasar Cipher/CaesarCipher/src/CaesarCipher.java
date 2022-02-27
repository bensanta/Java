/**
 * @author: Benjamin Santa
 * @version: 20220220
 * @description: This Caeasar Cipher program takes a string input to then
 * convert to an encryoted string. The encryption uses a shift method, where 
 * a user defined value is entered, and then used when shifting the alphabet.
 * For example if the shift is 4 - "a" will become "e", "b" will become "f", and so on.
 * If a value of 0 is entered into the shift, then the encryption will not run, and will
 * output an error.
 */

import java.util.*;
    
public class CaesarCipher {
      

    
    public static void main (String[] args){
        
        Scanner in = new Scanner(System.in);

        //Text to be encrypted - with User Input
        System.out.println("What phrase would you like to encrypt? (You can use numbers and letters) ");
        String userText = in.nextLine();
        
        
        //How much to shift - with User Input
        System.out.println("What is your cypher value?: ");
        int cypherValue = in.nextInt();
        
        //Only runs the cypher part of the program if it makes sense to run it; 
        //if the cypherValue if above 0
        if (cypherValue > 0){
            String alphabetNum = "abcdefghijklmnopqrstuvwxyz0123456789"; //26 letters + 10 digits
            char[] charAlphabetNum = alphabetNum.toCharArray();

            //Defined an array called encryptedOutput to store the encrypted value of the input
            char[] encryptedOutput = {};
        
            //Converted the user's input to a char array to avoid type conversion issues
            char[] charUserText = userText.toCharArray();

            //Used a loop to go through each value of the user defined input in the char array
            for (int i = 0; i < charUserText.length; i++){
                //Used the function "indexOf" to identify the user input's [i] char,
                //to see where it lies in the alphabetNum string
                int indexOfUserText = (alphabetNum).indexOf(charUserText[i]);
                int indexOfAlphabetNum = (indexOfUserText + cypherValue) % 36; //used modulo to avoid an outOfBounds error
                //Used Arrays.copyOf to expand the length of the "encryptedOutput"
                //array to be able to add the shifted values
                encryptedOutput = Arrays.copyOf(encryptedOutput, encryptedOutput.length + 1);
                //Added each value
                encryptedOutput[encryptedOutput.length - 1] = charAlphabetNum[indexOfAlphabetNum];
                //Printed the final encrypted output
                System.out.print(encryptedOutput[i]);
            }
        }
        else{
            //Tells the user to shift a value above 0 if they want to run the program
            System.out.println("Please use common sense... Use a value above 0 - Thank you :)");
        }
    }
}