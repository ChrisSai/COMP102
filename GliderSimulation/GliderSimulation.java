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

/** Runs a simulation of two gliders.
 *  Creates two new Glider objects,
 * Repeatedly makes the gliders move.
 * If a glider hits the ceiling, it changes the glider speed to be slow
 * If a glider reaches the right wall, it makes a new glider in its place.
 */

public class GliderSimulation{

    public static final int RIGHT_WALL = 600; // the right edge where the gliders disappear
    public static final int CEILING = 350;    // the ceiling where the gliders should slow down
    public static final double FLOOR = 400;   // position of the floor
    public static final double MAX_HEIGHT = CEILING-12;   // maximum height of the gliders

    public GliderSimulation(){
           UI.addButton("Start", this::throwGliders);
           UI.addSlider("Delay", 10, 80, 40, this::setDelay);
           UI.addButton("Quit", UI::quit);
           UI.setDivider(0.0); // hide the text area
           this.drawRoom();    // draw the room.
    }

    private int delay = 40;

    public void throwGliders(){
           UI.setImmediateRepaint(false);  // makes animation smoother, but needs repaintGraphics().
           
           while (true) {
                  Glider gliderA = this.makeNewGlider();
                  Glider gliderB = this.makeNewGlider();
                  gliderA.draw();
                  gliderB.draw();
             //loop for as long as at least one glider is flying.
                  while ( gliderA.getHeight()> 0 || gliderB.getHeight()>0 ){
                         // make each glider move
                         gliderA.move();
                         gliderB.move();

                         // redraw the gliders                   
                         this.clearRoom();   
                         gliderA.draw();
                         gliderB.draw();
                         this.drawRoom();
                         UI.repaintGraphics();

                         UI.sleep(delay);
                         // change the speed if the glider gets too high
                         if (gliderA.getHeight()>= MAX_HEIGHT ){ 
                                gliderA.setSpeed(Math.random()*4);
                         }
                         if (gliderB.getHeight() >= MAX_HEIGHT ){
                                gliderB.setSpeed(Math.random()*4);
                         }
                         // Make the gliders disappear if past right edge 
                         if (gliderA.getX()>=RIGHT_WALL ){ 
                                gliderA = this.makeNewGlider();  
                         }
                         if (gliderB.getX() >= RIGHT_WALL ){
                                gliderB = this.makeNewGlider();
                         }
                  }
                  UI.sleep(1000);  //both gliders on floor. Wait 1 second before restarting.

           }
    }

    /** Helper method that makes a new Glider with random initial values */
    public Glider makeNewGlider(){
           double initHeight = 50 + Math.random()*(CEILING-100); // random height
           double xSpeed = Math.random()*12;                 // random step size between 1 and 12.
           return new Glider(initHeight, xSpeed);
    }   

    public void clearRoom(){
           UI.eraseRect(0, FLOOR-CEILING-1, RIGHT_WALL-1, CEILING-2);
    }

    public void drawRoom(){
           UI.setColor(Color.black);
           UI.fillRect(0, FLOOR, RIGHT_WALL+50, 20);    // the ground
           UI.setColor(Color.lightGray);
           UI.fillRect(RIGHT_WALL, FLOOR-CEILING, 50, CEILING);    // the wall
           UI.setColor(Color.lightGray);
           UI.fillRect(0, FLOOR-CEILING-12, RIGHT_WALL+50, 12); // the ceiling
    }

    public void setDelay(double v){this.delay = (int)v;}

    // Main
    /** Create a new Simulation object */
    public static void main(String[] arguments){
           new GliderSimulation();
    }   

}
