// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a ${course} assignment.
// You may not distribute it in any other way without permission.
/* Code for ${course} - 2017T2
 * Name:谭宇轩Chris、王雅玲Chloe
* ID:1624092225/1624092230
*/
import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Renders plain ppm images onto the graphics panel
 *  ppm images are the simplest possible colour image format.
 */

public class ImageRenderer{
    public static final int TOP = 20;   // top edge of the image
    public static final int LEFT = 20;  // left edge of the image
    public static final int PIXEL_SIZE = 2;  

    // Constructor
    public ImageRenderer() {
           UI.initialise();
           UI.addButton("Clear", UI::clearGraphics );
           UI.addButton("Render (core)", this::doRenderImageCore );
           UI.addButton("Render (compl)", this::doRenderAnimatedImage );
           UI.addButton("Quit", UI::quit );
           UI.setWindowSize(850, 700);
           UI.setDivider(0.0);
    }

    /** Core:
        * Renders a ppm image file.
        * Asks for the name of the file, then calls renderImageHelper.
        */
    public void doRenderImageCore(){
           String fileName = UIFileChooser.open("choose picture");
       try {
            Scanner scan = new Scanner(new File(fileName)); 
            this.renderImageHelper(scan);
      }
      catch (Exception e) {
            UI.println("File error:" + e); 
      }
    }
    /** Core:
        * Renders a ppm image file.
        * Renders the image at position (LEFT, TOP).
        * Each pixel of the image  is rendered by a square of size PIXEL_SIZE
        * Assumes that
        * - the colour depth is 255,
        * - there is just one image in the file (not "animated"), and
        * - there are no comments in the file.
        * The first four tokens are "P3", number of columns, number of rows, 255
        * The remaining tokens are the pixel values (red, green, blue for each pixel
        */
    public void renderImageHelper(Scanner sc){
      try{  
        String code = sc.nextLine(); 
        if(code.equals("P3")){
            int cols =sc.nextInt();
            int rows = sc.nextInt();
            int colorDepth = sc.nextInt();
            int y = TOP;
            while(y<rows*2+TOP){
                int x = LEFT;
                while(x<cols*2+LEFT){
                    int r = sc.nextInt();
                    int g = sc.nextInt();
                    int b = sc.nextInt();
                    Color dot = new Color(r,g,b);
                    UI.setColor(dot);
                    UI.fillRect(x,y,PIXEL_SIZE,PIXEL_SIZE);
                    x=x+PIXEL_SIZE;
                }
                y=y+PIXEL_SIZE;
            } 
        }
        else{UI.println("Error!");}
        }
      catch (Exception e) {UI.println("File error:" + e); } 
    }
    /** Completion
        * Renders a ppm image file which may be animated (multiple images in the file)
        * Asks for the name of the file, then renders the image at position (LEFT, TOP).
        * Each pixel of the image  is rendered by a square of size PIXEL_SIZE
        * Renders each image in the file in turn with 200 mSec delay.
        * Repeats the sequence 3 times.
        */
    public void doRenderAnimatedImage(){
           UI.clearGraphics();
           String fileName = UIFileChooser.open("choose picture");
           int count = 0;
           try{
              Scanner scan = new Scanner(new File(fileName));
              while(count<=2){
                  count++;
                while(scan.hasNext()){
                    renderImageHelper(scan);
                    UI.sleep(200);
                }
            }
        }
        
              catch(Exception e){UI.println("File failure: "+e);}
            }
        }



