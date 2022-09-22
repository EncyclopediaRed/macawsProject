package macawsProject;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Flight Class
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Sep 22, 2022
 *
 */
public class Flight {

    // Create the instance variables for a flight.
    private String route;
    private LocalDate date;
    private Time time;
    private int flightNum;
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
    public Flight(String r, LocalDate d, Time t, int f_id, Pilot p) {

        // Assign each instance variable for a flight.
        r = route;
        d = date;
        t = time;
        f_id = flightNum;
        flightNum = num;
        num += 10;
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
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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