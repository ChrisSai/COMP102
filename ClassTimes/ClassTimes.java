// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a ${course} assignment.
// You may not distribute it in any other way without permission.
/* Code for ${course} - 2017T2
* Name:
* Username:
* ID:
*/
import ecs100.*;
import java.util.*;
import java.io.*;

public class ClassTimes {


    /** Constructor: set up interface */
    public ClassTimes(){

           // Methods for the core
           UI.addButton("Print All", this::printAll);
           UI.addButton("Course", this::doPrintCourse );
           UI.addButton("Room", this::doPrintRoom );

           //Methods for the completion
           UI.addButton("Rooms On Day", this::doPrintInRoomsOnDay );
           UI.addButton("Room Booking File", this::doBookingsFileForRoom );

           //Methods for the challenge
           UI.addButton("Mean Class Length", this::doMeanClassLength );
           UI.addButton("Potential Disruptions", this::doPotentialDisruptions );

           UI.setDivider(1.0);
           UI.addButton("Quit", UI::quit);
    }

    /** Core 1
        * Reads the class timetable file, printing out each line to the UI.
        * This method is very straightforward, and there are very similar
        * examples in the lecture notes.
        */

    public void printAll() {
           try {
                  Scanner scan = new Scanner(new File("classdata.txt"));
                  while(scan.hasNext()){
                      String line = scan.nextLine();
                      UI.println(line);
                    }
           }

           catch(IOException e) {UI.printf("File Failure %s \n", e);}
           UI.println("=========================");
    }


    /** Core 2
        * Reads the class timetable file, printing out (to the UI window)
        * the class type, day, start time, end time, and room
        * for each class with the target course.
        * Prints a message if there are no classes for the course.
        */

    public void doPrintCourse(){
           String course = UI.askString("Enter course code (eg ACCY111):").toUpperCase();
           UI.clearText();
           this.printCourse(course);
    }

    public void printCourse(String targetCourse){
           UI.println("\nClasses for course " + targetCourse);
           UI.println("=========================");
           try{
             Scanner scan = new Scanner(new File("classdata.txt"));
             while(scan.hasNext()){
                 String line = scan.nextLine();
                 String code = scan.next();
                 if(code.equals(targetCourse)){
                     UI.println(code+"   "+line);
                }
            }
        }
           catch (IOException e){UI.printf("File Failure %s \n", e);}
       

           UI.println("=========================");
    }

    /** Core 3
        * Prints out the name of the target room, and underlines it.
        * Then reads the class timetable file, printing out (to the UI window)
        *  the course code, class type, day, start time, end time
        *  for each class in the target room.
        * It will be best to read the six tokens on each line individually.
        */
    public void doPrintRoom() {
           String room = UI.askString("Enter room code (eg AM102):").toUpperCase();
           UI.clearText();
           this.printRoom(room);
    }

    public void printRoom(String targetRoom) {
           UI.println("Classes in " + targetRoom);
           UI.println("=======================");
           try {
                  Scanner scan = new Scanner(new File("classdata.txt"));
                  while(scan.hasNext()){
                      String line = scan.nextLine();
                      int i = 0;
                      if(line.contains(targetRoom)){
                        UI.println(line);
                    }
                }
            }
           catch(IOException e) {UI.printf("File Failure %s \n", e);}

           UI.println("=========================");
    }


    /** Completion 1
        * Prints a title containing its arguments, and then
        * Reads the class timetable file, printing out (to the UI window)
        * the course code, class type, day, start and end time
        * for each class that is in targetRoom1 or targetRoom2 and is on targetDay
        * It will be best to read the six tokens on each line individually.
        */
    public void doPrintInRoomsOnDay(){
           String room1 = UI.askString("Enter first room code (eg AM102):").toUpperCase();
           String room2 = UI.askString("Enter second room code (eg AM104):").toUpperCase();
           String day = this.askDay();
           UI.clearText();
           this.printInRoomsOnDay(room1, room2, day);
    }

    public void printInRoomsOnDay(String targetRoom1, String targetRoom2, String targetDay){
           UI.printf("Classes in %s or %s on %s%n", targetRoom1, targetRoom2, targetDay);
           UI.println("==========================================");
           try {
                  Scanner scan = new Scanner(new File("classdata.txt"));
                  while(scan.hasNext()){
                      String line = scan.nextLine();
                      Scanner room1 = new Scanner(line);
                      Scanner room2 = new Scanner(line);
                      Scanner day = new Scanner(line);
                    
                      
                }
            }
            
           catch(IOException e) {UI.printf("File Failure %s \n", e);}

           UI.println("=========================");
    }


    /** Completion 2
        * Writes a new file listing all the class bookings that are in a given room.
        *  The name of the new file should be the room, followed by "_Bookings.txt"
        *  The first line of the file should specify what room the bookings are for:
        *  "Bookings for room <room name>"
        *  
        *  Each class booking should be formatted in three lines, with a blank line after.
        *  Course: <Course Code>
        *  Time: <Day> <Start Time>-<End Time>
        *  Session: <Type>
        *
        *  For example, if the targetRoom is VZ515, then the start of the file would be as follows
        * 
        *  Bookings for room VZ515
        *  ----------------------------------
        *  Course: ACCY111
        *  Time: Tue 1000-1050
        *  Session: Tutorial
        *  
        *  Course: ACCY130
        *  Time: Thu 1310-1400
        *  Session: Tutorial
        *  
        *  Course: ACCY130
        *  Time: Tue 1310-1400
        *  Session: Tutorial
        *  
        */
    public void doBookingsFileForRoom() {
           String room = UI.askString("Enter room code (eg AM102):").toUpperCase();;
           UI.clearText();
           this.bookingsFileForRoom(room);
    }

    public void bookingsFileForRoom(String targetRoom){
           UI.println("Generating room booking file for " + targetRoom);
           /*# YOUR CODE HERE */

           UI.println("Printed to "+targetRoom+"_Bookings.txt");
           UI.println("=========================");
    }



    /** Challenge
        * Computes and returns the average (mean) duration in minutes of all classes scheduled
        * in a specified room.
        * Hint: be careful with the times
        * Hint: if there are no classes in the room, do not cause an error.
        */
    public void doMeanClassLength() {
           String room = UI.askString("Enter room code (eg AM102):").toUpperCase();
           UI.clearText();
           double mean = this.meanClassLength(room);
           if (mean == 0) {
                  UI.printf("There were no classes in  %s%n", room);
           }
           else {
                  UI.printf("Average duration in %s = %4.2f mins%n",
                         room,  mean);
           }
           UI.println("=========================");
    }

    public double meanClassLength(String targetRoom){
           /*# YOUR CODE HERE */

           return 0.0; // to make it compile
    }

    /** Challenge
        * Lists all the courses (just the course code) that have classes in a given building
        * on a given day such that any part of the class is between the given times.
        * Each course involved should only be listed once, even if it has several classes
        * in the building in the time period.  Note, the data file is ordered by the course code.
        * Note that this is similar, but not the same as, one of the completion questions.
        */
    public void doPotentialDisruptions(){
           UI.clearText();
           String building = UI.askString("Enter a building code(eg AM):").toUpperCase();
           String day = this.askDay();
           int start = UI.askInt("What is the start time?");
           int end = UI.askInt("What is the end time?");
           this.potentialDisruptions(building, day, start, end);
    }

    public void potentialDisruptions(String building, String targetDay, int targetStart, int targetEnd){
           UI.printf("\nClasses in %s on %s between %d and %d%n",
                  building, targetDay, targetStart, targetEnd);
           UI.println("=================================");
           /*# YOUR CODE HERE */

           UI.println("=========================");
    }

    /** Asks the user for a Day and returns as a capitalised three letter string */
    public String askDay(){
           String day;
           while (true) {
                  day = UI.askString("Enter first 3 letters of day (eg Mon):");
                  if (day.length()>=3){ break; }
                  UI.println("You must enter at least three letters of the day.");
           }
           return day.substring(0,1).toUpperCase() + day.substring(1,3).toLowerCase();
    }


}
