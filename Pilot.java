package macawsProject;

/**
 * Pilot Class
 * 
 * Creates a pilot class to detail what makes up a pilot: Pilot Name Pilot Number Current Location?
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */
public class Pilot {

    // Create the instance variables for a pilot.
    private String pilotName;
    protected int pilotNum;

    // Create a static variables for unique pilot ID.
    public static int num = 10;

    // Create a no argument constructor that generates a pilot ID.
    public Pilot() {

        // Assign the static variable for a pilot ID.
        pilotNum = num;
        num += 10;
    } // End of no argument constructor.

    // Create a full argument constructor
    public Pilot(String pName) {

        // Assign each instance variable for a pilot.
        pilotName = pName;
        pilotNum = num;
        num += 10;
    } // End of full argument constructor.

    // Create a toString method for displaying pilot information.
    public String toString() {

        // Create the pilot information to be displayed.
        return "Pilot Name: " + pilotName + "\nPilot ID Number: " + pilotNum;
    } // End of method toString.

    // Create the getters and setters for each instance variable.
    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public int getPilotNum() {
        return pilotNum;
    }

    public void setPilotNum(int pilotNum) {
        this.pilotNum = pilotNum;
    }

} // End of Class Pilot.