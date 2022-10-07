package macawsProject;

import java.util.Scanner;

/**
 * AirlineDriver Class
 * 
 * Creates a driver class to test the functionality of the airline reservation system
 * 
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Oct 6, 2022
 *
 */
public class AirlineDriver {

	/**
	 * Print a the seat map of the airplane
	 * Each Seat is represented by a row number and seat letter
	 * First Class is rows 1-2 and Economy is rows 3-4
	 * First Class has 2 seats per row and Economy has 4 seats per row both divided by a aisle
	 * Use a 2D array to represent the seats and print the seat map
	 * 
	 * @param args
	 */
	public static void printSeatMap() {

		// Create a 2D array to represent the seats
		String[][] seatMap = { { "1A", "1B", },
							   { "2A", "2B", },
							   { "3A", "3B", "3C", "3D", },
							   { "4A", "4B", "4C", "4D", } };
		
		// Print the seat map
		System.out.println();
		System.out.println("Airplane Seat Map");
		System.out.println("=========================");
		for (int i = 0; i < seatMap.length; i++) {
			for (int j = 0; j < seatMap[i].length; j++) {
				System.out.print("| " + seatMap[i][j] + " |");
			}
			System.out.println();
		}
		
		}
	/**
	 * Print the flight information
	 * Flight number 
	 * EXAMPLE: 12RPAM or 13PRPM
	 * - Keeps the day (12), route (R for Roanoke, P for Phoenix), and time (AM for morning, PM for evening))
	 * - There are 4 flights per day
	 * - The first flight Plane A is Roanoke to Phoenix AM
	 * - The second flight Plane B is Phoenix to Roanoke AM
	 * - The third flight Plane A is Phoenix to Roanoke PM
	 * - The fourth flight Plane B is Roanoke to Phoenix PM
	 * 
	 * @param args
	 */
	public static void printFlightInfo() {

		}
	
	public static void printPilots() {
    	//11.	We need an option for printing each Pilot’s schedule for the week 
		//(which flights are they flying---date, time direction.


    	}
	
	public static void printReservation() {
    	// print who is sitting in a certian seat not a requriment but probaby a good idea
	   	}
	
	public static void printCustomerByNum() {
    	//12.	We need an option for printing all the reservations for a give Customer if given their customer number.
	   	}
	
	public static void bookReservation() {
    	//book a reservation the meat and potatos
    	}
	
	public static void cancelReservation() {
    	//cancel a reservation the meat and potatos
    	}
	public static void printGrossIncome() {
    	//print the Grossincome of the flight by flight number
    	}
	public static void closeConnection() {
    	//Close the connection
    	}
	public static void searchReservation() {
    	//Close the connection
    	}
	public static void searchDeleted() {
    	//search deleted flights by reservation number
    	}


}


