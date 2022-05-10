/**
 * Write a description of class wordleGame here.
 *
 * @author (your name)
 * 
 * @version (a version number or a date)
 */

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class wordleGame {

    /**
     * Constructor for objects of class wordleGame
     */
    public WordleGame()
    {
        class WordPanel extends JPanel{
            
            JLabel[] wordColumns = new JLabel[5]; // 5 letter words
            public WordPanel() {
                this.setLayout(new GridLayout (1, 5)); //1 row, 5 columns
                Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY); //create a Lgray border
                for(int i = 0; i < 5; i++) {
                    wordColumns[i] = new JLabel();
                    wordColumns[i].setOpaque(true); //only "true" will show background color
                    wordColumns[i].setBorder(blackBorder);
                    this.add(wordColumns[i]);
                }
            }
            public void setPanelText(String charValue, int position, Color color) {
                //set the text of the JLabel, according to the char value and position
                this.wordColumns[position].setText(charValue); 
                this.wordColumns[position].setBackground(color); //set the color of the JLabel to the color parameter

            }
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
