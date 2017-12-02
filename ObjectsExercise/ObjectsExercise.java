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

/** Exercise for defining objects.
 *  This program contains methods for testing PartyBalloon, and FlagStaff objects
 *  IT IS ALL WRITTEN FOR YOU, but you need to read it to see what the
 *  PartyBalloon and FlagStaff classes should do
 */

public class ObjectsExercise{

    public static final double GROUND = 400;

    /** Constructor to set up the user interface */
    public ObjectsExercise(){
           UI.initialise();
           UI.addButton("Clear", UI::clearPanes );
           UI.addButton("PartyBalloons", this::doPartyBalloon );
          // UI.addButton("FlagStaff", this::doFlagStaff );
           UI.addButton("Quit", UI::quit );
    }	

    /** Makes a pair of PartyBalloon objects and makes them fly into the sky */
    public void doPartyBalloon(){
           UI.clearPanes();
           this.drawGround();

           int count = 0;
           // make two random colours
           Color colr1 = Color.getHSBColor((float)Math.random(),1,1);
           Color colr2 = Color.getHSBColor((float)Math.random(),1,1);

           // choose two random x positions
           double x1 = Math.random()*500;
           double x2 = Math.random()*500;

           // make and draw two PartyBalloon objects.
           PartyBalloon myBalloon = new PartyBalloon(x1, 370, colr1);
           PartyBalloon yourBalloon = new PartyBalloon(x2, 370, colr2);
           int steps = 0;
           
           myBalloon.draw();
           yourBalloon.draw();

           // repeatedly move and redraw the balloons.
          
           while (steps < 18){
                  UI.sleep(200);

                  //move the balloons
                  myBalloon.riseLeft();
                  yourBalloon.riseRight();

                  //redraw the ground and the balloons
                  UI.clearGraphics();
                  this.drawGround();
                  myBalloon.draw();
                  yourBalloon.draw();

                  steps = steps + 1;
           }
    }

    /** 
        * Makes two FlagStaff objects and makes the flags go up and down.
        */
    /*public void doFlagStaff(){
           UI.clearPanes();
           FlagStaff flag1 = new FlagStaff(110, GROUND);  
           FlagStaff flag2 = new FlagStaff(380, GROUND);

           this.drawGround();
           flag1.draw();
           flag2.draw();
           UI.sleep(500);

           int steps = 0;
           UI.println("Make flags go up");
           while (steps < 8){
                  // make flags go up by a random amount 
                  flag1.raise(Math.random()*100);
                  flag2.raise(Math.random()*100);

                  UI.clearGraphics();
                  this.drawGround();
                  flag1.draw();
                  flag2.draw();

                  UI.sleep(500);
                  steps++;   //(shorthand for steps = steps + 1;
           }
           UI.println("Make flags go down");
           while (steps> 0){
                  // make flags go down by a random fraction
                  flag1.lower(Math.random()*100);
                  flag2.lower(Math.random()*100);

                  UI.clearGraphics();
                  this.drawGround();
                  flag1.draw();
                  flag2.draw();

                  UI.sleep(500);
                  steps--;   //(shorthand for steps = steps - 1;
           }

    }

    /** Draw the ground */
    public void drawGround(){
           UI.setColor(new Color(120, 80, 0));   // dark brown,
           UI.fillRect(0,GROUND,600,10);  
    }

    /** Calls the constructor */
    public static void main(String[] args){
           new ObjectsExercise();
    }

}
