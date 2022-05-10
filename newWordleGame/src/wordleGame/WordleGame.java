 

/*
 * This program is an attempt at modified version of Wordle. Wordle is
 * a game where you have 6 attempts to guess a 5 letter word, this
 * version is a bit different. This version is set up in a way, where there is room
 * for improvement. Currently, you have to memorize which letters are in the word, and
 * which are not, due to only 1 word being displayed at a time. While not perfect, I actually
 * enjoy playing it as a memory game.
 * 
 * Important notice, for this to work!!!
 * 
 * On around line 204, there is a path within the quotation marks, once you download
 * this zip, you will have to unzip it, and please locate the wordList.txt file.
 * Once you've located it, please copy it's path, on WINDOWS you do
 * this by opening the file browser and then clicking on the top bar, with the directory
 * labels and such. Once you've clicked it, and selected the path, make sure
 * it says "wordList.txt" at the end, and copy & paste it into the path location. If
 * it doesn't say "wordList.txt" at the end, please just add it, using the same format as
 * the rest of the String. For MAC (and probably LINUX) users, please follow
 * these directions: Once you've decompressed it, and located it, then secondary
 * click. A menu will pop-up, with a clickable option that says "Get info", a module
 * will pop up. On this module, there will be a path, next to the "where" subtitle.
 * Copy that path, and paste it in to where it was previously discussed.
 * 
 * Thank you, hope you are able to make some improvements, enjoy!
 * 
 * @author Benjamin Santa
 * @version 20220429
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class WordleGame implements ActionListener{
    
    class WordPanel extends JPanel { //Defining an inner-class to represent the word chars
        
        JLabel [] wordColumns = new JLabel[5]; //5-letter words
        
        public WordPanel() { //Default constructor
            this.setLayout(new GridLayout(1, 5)); //new GridLayout, 1 row, 5 columns
            Border blackBorder = BorderFactory.createLineBorder(Color.gray);
            
            for(int i = 0; i < 5; i++) { //for loop used to initialize each individual label within the array
                wordColumns[i] = new JLabel();
                wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
                wordColumns[i].setOpaque(true); //Set the opacity, in order for the background color to be enabled
                wordColumns[i].setBorder(blackBorder);
                this.add(wordColumns[i]); //label boxes will be added every single row
                
                
            }
            
        }
        
        public void clearWordPanel(){ //Clearing the word panel / UI
            for (int i = 0; i < 5; i ++) {
                wordColumns[i].setText(""); //Sets the text field empty
            }
        }
        public void setPanelText(String charValue, int position, Color color) { //Method to be able to set a value inside this panel
            this.wordColumns[position].setText(charValue); //Assign the char value
            this.wordColumns[position].setBackground(color); //Assign the bg color
            
        }
    }
    
    class UserPanel extends JPanel{ //used to accept user input
        private JTextField userInput; //to be able to take word input
        private JButton submitButton; //to submit the word to the program
        
        
        public UserPanel() {
            //Grid for the user input, and the submit button
            this.setLayout(new GridLayout (1, 2)); //1 row, 2 elements / columns
            userInput = new JTextField();
            this.add(userInput);
            submitButton = new JButton("Submit");
            this.add(submitButton);
        }
        //Getters for userInput and submitButton
        public JTextField getUserInput() {
            return userInput;
        }
        public JButton getSubmitButton() {
            return submitButton;
        }
    }
    
    private JFrame gameFrame; //The frame which holds the entire UI
    private WordPanel[] wordPanelArray = new WordPanel[6]; //User can enter 6 words
    private UserPanel userPanel; //member variable
    private String wordleString; //for the computer to generate
    private int count = 0; //indicate the position in the wordPanelArray - keeps track of the active lane
    
    WordleGame(){ //create the default constructor
        gameFrame = new JFrame("Wordle"); //Title of the frame
        gameFrame.setSize(300, 300); //width, height of the game UT
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setLayout(new GridLayout (7, 1)); //7 rows, each with 1 element
        gameFrame.setVisible(true);
        //gameFrame.setResizable(true);
        gameFrame.setLocationRelativeTo(null); //Relative to the center 
        
        for (int i = 0; i < 6; i ++) { //initialize the wordPanelArray
            wordPanelArray[i] = new WordPanel();
            gameFrame.add(wordPanelArray[i]);
        }
        
        userPanel = new UserPanel(); //initializing the userPanel using a default output
        userPanel.getSubmitButton().addActionListener(this);
        gameFrame.add(userPanel);
        gameFrame.revalidate(); //re-initialise all components, fix the issue where the input wouldn't show
        
        wordleString = getUserString();
        System.out.println("Word for the day: " + wordleString);
        
    }
    
    
    public static void main(String[] args) {
        new WordleGame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        String userWord = this.userPanel.getUserInput().getText(); //Getting the user word from the panel
        
        //splitting the userWord into individual letters
        if(userWord.length() > 4) {
            if(isWordleWordEqualTo(userWord.trim().toUpperCase())) {
                clearAllPanels();
                JOptionPane.showMessageDialog(null,  "You win", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        if(count > 5) {
            JOptionPane.showMessageDialog(null,  "You lost... Try again tomorrow", "Whoops", JOptionPane.INFORMATION_MESSAGE);
            gameFrame.dispose();
            return;
        }
        count++;
    }
    
    private void clearAllPanels() {
        for (int i = 0; i <= count; i++) { //Count is already keeping track - only need to iterate over the arrays which have words
            wordPanelArray[i].clearWordPanel();
        }
    }
        
    private boolean isWordleWordEqualTo(String userWord) {
        List<String> wordleWordsList = Arrays.asList(wordleString.split("")); //using "Arrays" to be able to use the "contains" method
        String[] userWordsArray = userWord.split("");
        List<Boolean> wordMatchList = new ArrayList<>();
        
        for(int i = 0; i < 6; i++) {
            // if the generated word contains the first letter in the user word, then checks and changes the colour
            if(wordleWordsList.contains(userWordsArray[i])) { 
                if(wordleWordsList.get(i).equals(userWordsArray[i])) {
                    getActivePanel().setPanelText(userWordsArray[i], i, Color.green);
                    wordMatchList.add(true);
                } else {
                    getActivePanel().setPanelText(userWordsArray[i], i, Color.yellow);
                    wordMatchList.add(false);
                }
            } else {
                getActivePanel().setPanelText(userWordsArray[i], i, Color.gray);
                wordMatchList.add(false);
            }
        }
        return !wordleWordsList.contains(true);
    }

    
    public WordPanel getActivePanel() {
        return this.wordPanelArray[count];
    }
    public String getUserString() {
        
        //  !!! Change the path in here, to the path of your wordList... follow the directions in the intro!!!
        Path path = Paths.get("/Users/sbeni/Documents/GitHub/Java/newWordleGame/src/wordleGame/wordList.txt");
        
        
        System.out.println(path.toAbsolutePath());
        
        List <String> wordlist = new ArrayList<>(); //WordList into an ArrayList
        try {
            wordlist = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Random random = new Random();
        int wordFromList = (int) Math.floor(Math.random()*(500-1+1)+1);
        return wordlist.get(wordFromList).trim().toUpperCase(); //Uppercases & gets rid of spaces, which come after the word
        
    }
    
}
