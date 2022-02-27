/**
 * A Matrix is created with random colours in the middle and a red border
 * 2 red diagonal lines are also created, intersecting at the middle
 *
 * @author Benjamin Santa
 * @version 20220211
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
 
public class ColoredBoxes extends JFrame {
 
    public static void main(String[] args) {
        new ColoredBoxes();
    }
 
    public ColoredBoxes() {
        setTitle("Boxes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new BoxesPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public static class BoxesPanel extends JPanel {
     
        static final int ROWS = 25;
        static final int COLS = 25;
        static final int BOX_SIZE = 25;
        
        /**
        * 2D array is created here
        */
        Color[][] myColors = new Color[ROWS][COLS];
        
        public BoxesPanel() {
         
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    int c1 = (int) (Math.random() * 255);
                    int c2 = (int) (Math.random() * 255);
                    int c3 = (int) (Math.random() * 255);
                    //random colours
                    myColors[row][col] = new Color(c1, c2, c3);

                    //Creating the border & First diagonal
                    for (int i = 0; i < COLS; i++){
                        //Border Creation
                        myColors[0][i] = new Color(255, 0, 0);
                        myColors[i][0] = new Color(255, 0, 0);
                        myColors[i][24] = new Color(255, 0, 0);
                        myColors[24][i] = new Color(255, 0, 0);
                        //Diagonal Creation - Top Left to bottom right
                        myColors[i][i] = new Color(255, 0, 0);
                    }

                    //Adds value for y coords - for second diagonal line
                    int y = 0;
                    //Loop to create second diagonal line - Top RIght to bottom left
                    for (int x = COLS - 1; x > 0; x--){
                        myColors[x][y] = new Color(255, 0, 0);
                    //Adds 1 to the y coordinate to create the inverse diagonal line, as only the x value has to decrease
                        y++;
                    }

                    

                }
            }
        }
     
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(COLS * BOX_SIZE, ROWS * BOX_SIZE);
        }
     
        @Override
        protected void paintComponent(Graphics g) {
            
            // DO NOT EDIT THIS METHOD

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
             
            int xOffset = (getWidth() - (COLS * BOX_SIZE)) / 2;
            int yOffset = (getHeight() - (ROWS * BOX_SIZE)) / 2;
             
            // DO NOT EDIT THIS METHOD

            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    g2d.setColor(myColors[row][col]);
                    g2d.fillRect(xOffset + (col * BOX_SIZE), 
                                    yOffset + (row * BOX_SIZE), BOX_SIZE, BOX_SIZE);
                }
            }
            // DO NOT EDIT THIS METHOD
            g2d.dispose();
        }    
    }
}