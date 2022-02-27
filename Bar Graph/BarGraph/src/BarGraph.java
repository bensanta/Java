/**
 * Starter code for creating a Bar Graph representing the total rainfall per month
 * in two cities.
 * 
 * TO DO:
 *  Use the arrays below and the knowledge you learned about graphics to draw a bar 
 *  graph that represents the rainfall last year in Seattle and Washington D.C. by month.
 *  
 * Optional Challenge Tasks:
 *  - Create an array to hold the months of the year and provide them as labels for your graph
 *  - Create an xy axis with labels and tick-marks for the values.
 *
 * @author Benjamin Santa
 * @version 20220211
 */

import javax.swing.*;
import java.awt.*;

public class BarGraph extends JFrame{
    private JFrame frame;

    public BarGraph() {
        setTitle("Bar Graph");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(getSize());
        add(new DrawBars(getSize()));
        pack();
        setVisible(true);
    }

    public static void main(String... argv) {
        new BarGraph();
    }

    public static class DrawBars extends JPanel {
        /*
         * Declare Instance Variables Here
         */
        private int x = 20;
        private int y = 250;
        /*
         * Rainfall sources: 
         * Washington DC: https://www.weather.gov/media/lwx/climate/dcaprecip.pdf
         * Seattle: https://www.seattleweatherblog.com/rain-stats/rainfall-2020/
         */
        private double[] dc = 
            {2.79, 3.21, 2.31, 6.30, 2.49, 3.51, 6.51, 8.73, 5.53, 4.86, 6.14, 4.96};
        private double[] seattle = 
            {9.23, 3.63, 3.72, 2.71, 1.94, 1.57, .70, .88, 1.50, 3.48, 6.57, 5.35};        
        

        public DrawBars(Dimension dimension) {
            setSize(dimension);
            setPreferredSize(dimension);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;  //g2 is the graphics object that we need to use
                                            //to draw things to the screen
            //create a background
            g2.setColor(Color.white);
            Dimension d = getSize();
            g2.fillRect(0, 0, d.width, d.height);

            Color navy = new Color(4, 30, 66); 
            Color iceBlue = new Color(153, 217, 217);
            
            /**
             * TO DO:
             * Build your bar graph here!
             * purple and green colors are provided above for your use.
             * 
             * Challenge Task: Create an array with the corresponding months and 
             *                 provide labels for your graph.
             */

             
             //int dcx  = 50;
             //Loop creating the bars for DC data
                x = 0;
             for (int i = 0; i < dc.length; i++){
                g.setColor(navy);
                //g.fillRect(x, y, width, height);
                g.fillRect(x, 500 - (int) dc[i] * 25, 30, (int) (dc[i] * 25));
                x += 65;
                
             }

             //Loop creating the bars for Seattle data
                x = 30;
             for (int i = 0; i < seattle.length; i++){
                g.setColor(iceBlue);
                g.fillRect(x, 500 - (int) seattle[i] * 25, 30, (int) seattle[i] * 25);
                x += 65;
             }
             //Rectangle for base of graph
             g.setColor(Color.lightGray);
             g.fillRect(0, 500, 1000, 500);
             //display text
            g2.setColor(navy);
            g2.setFont (new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Washington DC", 10, 30);//text to display, x and y coordinates
            g2.setColor(iceBlue);
            g2.drawString("Seattle", 200, 30);
        }
    }
}

