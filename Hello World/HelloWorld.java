/**************************************************************************
 *  Author: Benjamin Santa 
 *  Date: 14 January, 2022
 *  Course: GOA CS2: Java
 *  
 *  Prints "Hello, World". By tradition, this is everyone's first program.
 *
 *  These 14 lines of text are comments. They are not part of the code;
 *  they are here as a note to the reader of the program. 
 *      The first 3 lines tell the reader who, when, and why
 *      The next line describes the big picture purpose of the program. 
 *  
 *  We will always include such lines in our programs
 *************************************************************************/

public class HelloWorld {
    
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World!");
        int myVar = 0;
        while (myVar < 10) {
            myVar = myVar + 4;
            
        }
        System.out.println(myVar);
        int m = 0;
        m = m + (10/3);
        if (m == 3){
            m = m + 4;
        }
        else{
            m = 5;
        }
        System.out.println(m);
    }
}