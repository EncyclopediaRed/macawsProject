package macawsProject;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {

/**
 * Menu Class
 * 
 * Creates a menu class to detail what makes up a UI menu for a airline reservation system
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Sep 22, 2022
 *
 */


	int choice = 0;
	while(choice !=11) {
		choice = menu();
		if(choice == 1) {
			AirlineDriver.printSeatMap();
		} else if (choice ==2) {
			AirlineDriver.printFlightInfo();
		} else if (choice == 3) {
			AirlineDriver.printPilots();
		} else if (choice == 4) {
			AirlineDriver.printReservation();
		} else if (choice == 5) {
			AirlineDriver.printCustomerByNum();
		} else if (choice ==6) {
			AirlineDriver.bookReservation();
		} else if (choice ==7) {
			AirlineDriver.cancelReservation();
		} else if (choice ==8) {
			AirlineDriver.printGrossIncome();
		} else if (choice ==9) {
			AirlineDriver.searchReservation();
		} else if (choice ==10) {
			AirlineDriver.searchDeleted();
		} else if (choice ==11) { 
			AirlineDriver.closeConnection();
			System.exit(0);
			System.out.println("Thank you for choosing Java Airlines!\nHave a good day :).");
		}
	}
	
}
	


public static int menu() {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nMenu:");
	System.out.println("1.  Generate a map of the seats.");
	System.out.println("2.  Generate flight information.");
	System.out.println("3.  Generate Pilots.");
	System.out.println("4.  Generate the Reservations by Flight and Seat Number.");
	System.out.println("5.  Generate Customer information by Number.");
	System.out.println("6.  Book a Reservation.");
	System.out.println("7.  Cancel Reservation.");
	System.out.println("8.  Generate Profit by flight number.");
	System.out.println("9.  Search Reservations");
	System.out.println("10. Search deleted flights by reservation number.");
	System.out.println("11.  Exit.");
	int ans = scan.nextInt();
	return ans;
	
	}
	
	
	
}

