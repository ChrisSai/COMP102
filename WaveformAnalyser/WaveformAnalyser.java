// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2017T1
 * Name:谭宇轩 王雅玲
 * Username:1624092225/1624092230
 * ID:
 */

import ecs100.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * This is related to your program from assignment 3 which analysed temperature levels
 * However, instead of reading data from the user, it reads the data from
 * a file into an ArrayList.
 * It can also cope with much larger sets of numbers.
 * The numbers are guaranteed to be integers but the values can be
 *   negative and the signal swings above and below zero.
 *
 * The methods you are to complete all focus on the ArrayList of data.
 * CORE
 *  doRead:              reads numbers into an ArrayList.
 *  doDisplay:           displays the waveform.
 *  doReportDistortion:  prints out the fraction of time the signal is distorted.
 *  doSpread:            displays the spread with two horizontal lines and returns its value.
 * COMPLETION
 *  doDisplayDistortion: shows in red the distorted part of the signal.
 *  doHighlightPeaks:    plots the peaks with small green circles.
 *  doNormalise:         normalises all the values down so there is no distortion.
 * CHALLENGE
 *  doEnvelope:          displays the upper envelope.
 *  doSave:              saves the current waveform values into a file.
 *  select and edit:     let the user select a regions of the waveform with the mouse
 *                       to remove them.  Add a save button to save the edited values.
 */

public class WaveformAnalyser{
    // Fields 
    private ArrayList<Double> waveform;   // the field to hold the ArrayList of values

    // Constant: the threshold for the distortionLevel and showDistortion methods
    public static final double THRESHOLD = 200;

    // Constants: the dimensions of the graph for the displayWaveform method
    public static final int GRAPH_LEFT = 10;
    public static final int ZERO_LINE = 300;

    // Constant fields holding the size of the circles for the highlightPeaks method
    public static final int SIZE_CIRCLE = 10;
    
    private ArrayList<Integer> peaks = new ArrayList<>();

    /** Constructor:
     * Set up the ten buttons and mouselistener
     */
    public WaveformAnalyser(){
        //core
        UI.addButton("Read Data", this::doRead);
        UI.addButton("Display Waveform", this::doDisplay);
        UI.addButton("Report Distortion", this::doReportDistortion);
        UI.addButton("Spread", this::doSpread);
        //completion
        UI.addButton("Display Distortion", this::doDisplayDistortion);
        UI.addButton("Peaks", this::doHighlightPeaks);
        UI.addButton("Normalise", this::doNormalise);
        //challenge
        UI.addButton("Envelope", this::doEnvelope);
        UI.addButton("Save", this::doSave);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::doMouse);   // only for challenge
    }

    /**
     * [CORE]
     * Clears the panes, 
     * Creates an ArrayList stored in a field, then
     * Asks user for a waveform file (eg waveform1.txt) 
     * Reads data from the file into the ArrayList
     */
    public void doRead(){
        UI.clearPanes();
        String fname = UIFileChooser.open();
        this.waveform = new ArrayList<Double>();
        try {
            Scanner scanner = new Scanner(new File(fname));

            while (scanner.hasNextDouble()) {
                this.waveform.add(scanner.nextDouble());
            }

        } catch (IOException e) {
            UI.println("ERROR:" + e);
    }
         UI.println("Read " + this.waveform.size() + " data points from " + fname);
         UI.drawLine(10,10,200,200);
}

    /**
     * [CORE]
     * Displays the waveform as a line graph,
     * The n'th value in waveform is displayed at
     *    x-position is GRAPH_LEFT + n
     *    y-position is ZERO_LINE - the value
     * Plots a line graph of all the points with a blue line between
     *  each pair of adjacent points
     * Draw the horizontal line representing the value zero.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * Don't worry if the lines go off the window
     */
    public void doDisplay(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw x axis (showing where the value 0 will be)
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size()*2, ZERO_LINE);
        int n = 0;
        int np = 0;
        // plot points: blue line between each pair of values
       for(double num:this.waveform){
        UI.setColor(Color.blue);
        UI.drawLine(GRAPH_LEFT+np,ZERO_LINE-this.waveform.get(n),GRAPH_LEFT+np+1,ZERO_LINE-this.waveform.get(n+1));
        np=np+1;
        n++;
      }
        }

     

    /**
     * [CORE]
     * Computes and prints out the fraction of time the signal is distorted. 
     * This fraction of time is defined as the number of distorted values, divided by the number of values. 
     * A distorted value is defined as one whose absolute
     * value is greater than the value of THRESHOLD 
     * [Hint] You may find Math.abs() useful for this method - it computes the absolute value
     */
    public void doReportDistortion() {
        if (this.waveform == null){ //there is no data to analyse
            UI.println("No signal to analyse and report on");
            return;
        }
        double times = 0;
        for (double num:this.waveform){
           if(Math.abs(num)>THRESHOLD) {
            times++;
          }
        }
        double fraction = (times/waveform.size());
         UI.println("fraction of time is "+fraction);
        }
    

    

    /**
     * [CORE]
     * The spread is the difference between the maximum and minimum values of the waveform.
     * Finds the maximum and minimum values, then
     * Displays the spread by drawing two horizontal lines on top of the waveform: 
     *   one green line for the maximum value, and
     *   one red line for the minimum value.
     */
    public void doSpread() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        
        double max = 0;
        double min = 0;
        for(double num : this.waveform){
         if(num>max){max = num;}
         if(num<min){min = num;}
        }
        
        UI.setColor(Color.green);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE - max, GRAPH_LEFT + this.waveform.size(), ZERO_LINE - max);
        UI.setColor(Color.red);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE - min, GRAPH_LEFT + this.waveform.size(), ZERO_LINE - min);

    }

    /**
     * [COMPLETION]  [Fancy version of doDisplay]
     * Display the waveform as a line graph. 
     * Draw a line between each pair of adjacent points
     *   * If neither of the points is distorted, the line is BLUE
     *   * If either of the two end points is distorted, the line is RED
     * Draw the horizontal lines representing the value zero and thresholds values.
     * Uses THRESHOLD to determine distorted values.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * [Hint] You may find Math.abs(int a) useful for this method.
     * You may assume that all the values are between -250 and +250.
     */
    public void doDisplayDistortion() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();
       
        // draw zero axis
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size() , ZERO_LINE); 

        // draw thresholds
        UI.setColor(Color.green);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE - THRESHOLD, GRAPH_LEFT + this.waveform.size(), ZERO_LINE - THRESHOLD);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE + THRESHOLD, GRAPH_LEFT + this.waveform.size(), ZERO_LINE + THRESHOLD);
        /*draw waves*/
        
        UI.setColor(Color.black);
        int n = 0;
        int np = 0;
        for (double num : this.waveform) {
            if ((Math.abs(this.waveform.get(n)) > THRESHOLD) || (Math.abs(this.waveform.get(n+1)) > THRESHOLD)) {//color will be read either current or last is bigger than THRESHOLD
                UI.setColor(Color.red);
            } else {
                UI.setColor(Color.blue);
            }
            UI.drawLine((GRAPH_LEFT + n), ZERO_LINE - this.waveform.get(n), (GRAPH_LEFT + np), ZERO_LINE - this.waveform.get(n+1));
            np++;
            n++;
        }

        
       
    }

    /**
     * [COMPLETION]
     * Plots the peaks with small green circles. 
     *    A peak is defined as a value that is greater or equals to both its
     *    neighbouring values.
     * Note the size of the circle is in the constant SIZE_CIRCLE
     * You may assume that peaks values differ from their neighbouring points.
     */
    public void doHighlightPeaks() {
        this.doDisplayDistortion(); //use doDisplay if doDisplayDistortion isn't complete
        
        int n = 0;
        this.peaks.clear();
        while (n < this.waveform.size() - 1) {
            if (n < 2) {
                n++;
                continue;
            }
            if ((this.waveform.get(n)) > this.waveform.get(n - 1)) {
                if (this.waveform.get(n) > this.waveform.get(n + 1)) {
                    peaks.add(n);
                }
            }
            n++;
        }

        //draw circles
        for (int num : peaks) {
            UI.setColor(Color.green);
            UI.drawOval(GRAPH_LEFT + num - SIZE_CIRCLE / 2, ZERO_LINE - this.waveform.get(num) - SIZE_CIRCLE / 2, SIZE_CIRCLE, SIZE_CIRCLE);
        }


    }

    /**
     * [COMPLETION]
     * Finds the largest value (positive or negative) in the waveform, and
     * "normalises" all the values - multiplies them by threshold/maximum - so
     * that the largest value is now equal to the distortion threshold.
     * Then redraws the waveform.
     */
    public void doNormalise() {
      
    }

    /**
     * [CHALLENGE]
     * Displays the upper envelope with GREEN lines connecting all the peaks.
     *    A peak is defined as a point that is greater or equal to *both* neighbouring points.
     */
    public void doEnvelope(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.doDisplay();  // display the waveform,

        /*# YOUR CODE HERE */
    }

    /**
     * [CHALLENGE]
     * Saves the current waveform values into a file
     */
    public void doSave(){
        /*# YOUR CODE HERE */

    }

    private int index1;
    /**
     * [CHALLENGE]
     * Lets user select a region of the waveform with the mouse
     * and deletes that section of the waveform.
     */
    public void doMouse(String action, double x, double y){
        /*# YOUR CODE HERE */

    }

    // Main
    public static void main(String[] arguments){
        new WaveformAnalyser();
    }   

}
