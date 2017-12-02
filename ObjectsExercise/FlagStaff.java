// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a ${course} assignment.
// You may not distribute it in any other way without permission.
/* Code for ${course} - 2017T2
* Name:
* Username:
* ID:
*/
import ecs100.*;
import java.awt.Color;

/** A FlagStaff object represents a flag on a flagstaff.
 *   The flagstaff has a pole that is FLAGSTAFF_HEIGHT units high.
 *   It has a flag whose position can be anywhere from the bottom of the pole to the top

 *   The constructor specifies the position of the base of the flagstaff.

 *   The bottom left corner of the flag is initially at the bottom of the pole.
 *   The flag is FLAG_SIZE units high.
 *   The flag can be raised or lowered.
 *
 *   The flag can be as simple as a blue square, but you can draw a more fancy flag if you want
 *
 *   The class has three methods:
 *        - raise(double dist), which changes the flag position on the staff but does not let it go over the top of the flagstaff
 *        - lower(double dist), which changes the flag position on the staff but does not let it go below the bottom of the flagstaff.
 *                 Both methods change the field, but do not redraw the flag.
 *        - draw() which draws the flagstaff [UI.drawLine(....)] and the flag [UI.fillRect(....)]
 */
public class FlagStaff{

    public static final double FLAGSTAFF_HEIGHT = 300;  // 
    public static final double FLAG_SIZE = 30;  // 

    // fields,
    // position of the base of the flag staff
    // position of the flag (eg, the position of the bottom of the flag)
    /*# YOUR CODE HERE */

    /** Constructor:
        * Parameters: the position of the base of the flag staff
        * Initialises the fields, but does not draw the flag.
        */
    /*# YOUR CODE HERE */

    /** draw() method:
        * Draws the flagstaff and the flag. Can be very simple*/
    /*# YOUR CODE HERE */

    /** raise method:
        * Parameter:  amount to raise the flag
        * Changes the field, but does not let it go over the top of the flagstaff.
        * Does not redraw the flag.
        */
    /*# YOUR CODE HERE */

    /** lower method:
        * Parameter:  amount to lower the flag
        * Changes the field, but does not let it go below the bottom of the flagstaff.
        * Does not redraw the flag.
        */
    /*# YOUR CODE HERE */
    
}
