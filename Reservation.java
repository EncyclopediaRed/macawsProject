package macawsProject;

import java.util.ArrayList;

/**
 * Reservation Class
 * 
 * Creates a reservation class to detail what makes up a reservation:
 *  customer
 *  flight
 *  number of seats
 *  seat number
 *  cost
 *  confirmation number
 *  and a boolean to determine if the reservation is paid for
 * 
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Sep 22, 2022
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
        numSeats=n_s;
        seatNum=s_n;
        cost=c;
        cust = custt;
        f = fi;
        resNum = num;
        num += 100;
    } // End of the full argument constructor.

    // Create a toString method for displaying reservation, customer, and flight information.


    // Create the getters and setters for each instance variable.
    public int getNumSeats() {
        return numSeats;
    }

    
	@Override
	public String toString() {
	 return	"Number of Seats: " + numSeats
        + "\nSeat: " + seatNum
        + "\nCost: " + cost
        + "\nReservation ID Number: " + resNum
        + cust.toString()
        + f.toString();
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