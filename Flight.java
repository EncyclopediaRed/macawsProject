package macawsProject;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Flight Class
 * 
 * Creates a flight class to detail what makes up a flight:
 *  Flight route designation
 *  Flight number
 *  Departure date
 *  Departure time
 *  Pilot
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Sep 22, 2022
 *
 */
import java.util.ArrayList;
public class Flight {

    // Create the instance variables for a flight.
    private String route;
    private String date;
    private String time;
    private int flightNum;
    private String [][] map;
    private double profit;
    Pilot p;
    

    // Create static a variable for a unique flight ID.
    public static int num = 500;

    // Create a no argument constructor that generates a unique flight ID.
    public Flight() {

        // Assign the static variable.
        flightNum = num;
        num += 10;
    } // End of no argument constructor.

    // Create a full argument constructor for a flight.
    public Flight(String r, String string, String string2, String [][] m, Pilot p) {

        // Assign each instance variable for a flight.
    	route = r;
        date = string;
        time = string2;
        flightNum = num;
        num += 10;
        map=m;
        this.p = p;
    } // End of full argument constructor.
    
 
    // Create a toString method for displaying flight information and pilot.
    public String toString() {

        // Create the flight information to be displayed.
        return "The details for this flight are as follows: "
            + "\nRoute: " + route
            + "\nDate: " + date
            + "\nTime: " + time
            + "\nFlight ID Number: " + flightNum
            + "\nPilot: " + p.toString();
    } // End of method toString.

    // Create the getters and setters for each instance variable.
    public int toMString() {
    	 for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						System.out.print("| " + map[i][j] + " |");
					}
					System.out.println("");
    	 }
		return -1;
    }
    
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

  

    public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public Pilot getP() {
        return p;
    }

    public void setP(Pilot p) {
        this.p = p;
    }

} // End of Class Flight.