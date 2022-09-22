/*
 * 9/20/2022
 * This is the Reservation Class.
 * Macaws - Robert Barrowclift, C. Hogg, M. Porter - ITP 220
 */

package macawsProject;

public class Reservation {
	
	// Create the instance variables for a reservation.
	private int numSeats;
	private int seatNum;
	private double cost;
	private boolean status;
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
	public Reservation(int n_s, int s_n, double c, boolean status, int r_id, Customer cust, Flight f) {
		
		// Assign each instance variable for a reservation.
		n_s = numSeats;
		s_n = seatNum;
		c = cost;
		this.status = status;
		r_id = resNum;
		this.cust = cust;
		this.f = f;
		resNum = num;
		num += 100;		
	} // End of the full argument constructor.
	
	// Create a toString method for displaying reservation, customer, and flight information.
	public String toString() {
		
		// Create the reservation information to be displayed.
		return "Number of Seats: " + numSeats
				+ "\nSeat: " + seatNum
				+ "\nCost: " + cost
				+ "\nStatus: " + status
				+ "\nReservation ID Number: " + resNum
				+ cust.toString()
				+ f.toString();
	} // End of method toString.
	
	// Create the getters and setters for each instance variable.
	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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