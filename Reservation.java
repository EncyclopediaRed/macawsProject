package macawsProject;

import java.util.ArrayList;
import java.text.NumberFormat;

/**
 * Reservation Class
 * 
 * Creates a reservation class to detail what makes up a reservation: customer flight number of
 * seats seat number cost confirmation number and a boolean to determine if the reservation is paid
 * for
 * 
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */
public class Reservation {

    // Create the instance variables for a reservation.
    private int numSeats;
    private ArrayList<String> seatNum;
    private double cost;
    private int resNum;
    Customer cust;
    Flight f;

    // Create a static variable for reservation ID.
    public static int num = 1000;

    // Create a no argument constructor that generates a reservation ID.
    public Reservation() {

        // Assign the static variable.
        resNum = num;
        num += 100;
    } // End of the no argument constructor.

    // Create a full argument constructor.
    public Reservation(int n_s, ArrayList<String> s_n, double c, Customer custt, Flight fi) {

        // Assign each instance variable for a reservation.
        numSeats = n_s; // Assign the number of seats.
        seatNum = s_n; // Assign the seat numbers.
        cost = c; // Assign the cost.
        cust = custt; // Assign the customer.
        f = fi; // Assign the flight.
        resNum = num; // Assign the reservation ID.
        num += 100; // Increment the reservation ID.
    }

    // for loading canceled reservations
    public Reservation(int n_s, ArrayList<String> s_n, double c, Customer custt, Flight fi, int r) {

        // Assign each instance variable for a reservation.
        numSeats = n_s; 
        seatNum = s_n;
        cost = c;
        cust = custt;
        f = fi; 
        resNum = r;
    }
    // Create a toString method for displaying reservation, customer, and flight information.

    // Create the getters and setters for each instance variable.
    public int getNumSeats() {
        return numSeats; 
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return cust.getfirstName() + " " + cust.getlastName() + " (" + cust.getEmail() + ")" 
        	+ " reserved " + numSeats + " seats specifically, " + seatNum + " costing: " 
        	+ nf.format(cost) + " having the Reservation ID Number: " + resNum + " on Flight Number: "
            + f.getFlightNum()
            + "\n----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------"
            + "\n";

    }

    public String toCString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return "The following reservation was canceled: " + cust.getfirstName() + " "
            + cust.getlastName() + " (" + cust.getEmail() + ")" + " reserved " + numSeats 
            + " seats specifically, " + seatNum + " costing: " + nf.format(cost) 
            + "\nHaving the Reservation ID Number: " + resNum + " on Flight Number: " + f.getFlightNum()
            + "\n---------------------------------------------------------------------------------------------------------------------------------------"
            + "\n";

    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public ArrayList<String> getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(ArrayList<String> seatNum) {
        this.seatNum = seatNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getResNum() {
        return resNum;
    }

    public void setResNum(int resNum) {
        this.resNum = resNum;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public Flight getF() {
        return f;
    }

    public void setF(Flight f) {
        this.f = f;
    }

} // End of Class Reservation.