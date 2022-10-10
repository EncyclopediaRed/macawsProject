package macawsProject;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {

/**
 * Menu Class
 * 
 * Creates a menu class to detail what makes up a UI menu for a airline reservation system
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Oct 6, 2022
 *
 */
		ArrayList<Customer> cust = new ArrayList<Customer>();
		ArrayList<Pilot> pilot = new ArrayList<Pilot>();
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		
		Customer c = new Customer("Connor", "Hogg", "Hogg.Connor@gmail.com");
		cust.add(c);
		c = new Customer("David", "Bryan", "David.Bryan@gmail.com");
		cust.add(c);
		c = new Customer("John", "Gafildafield", "John.Gafildafield@gmail.com");
		cust.add(c);
		c = new Customer("Terry", "Bubbkis", "Terry.Bubbkis@gmail.com");
		cust.add(c);
		c = new Customer("Larry", "Outhouse", "Larry.Outhouse@gmail.com");
		cust.add(c);
		c = new Customer("Meg", "Griffen", "Meg.Griffen@gmail.com");
		cust.add(c);
		c = new Customer("Charles", "Hoag", "Charles.Hoag@gmail.com");
		cust.add(c);
		c = new Customer("Larry", "Harder", "Larry.Harder@gmail.com");
		cust.add(c);
		c = new Customer("James", "Madison", "James.Madison@gmail.com");
		cust.add(c);
		c = new Customer("Finley", "Davis", "Finley.Davis@gmail.com");
		cust.add(c);
		
		
		
		
		
		
		Pilot p1 = new Pilot("Pilot 1");
		pilot.add(p1);
		Pilot p2 = new Pilot("Pilot 2");
		pilot.add(p2);
		Pilot p3 = new Pilot("Pilot 3");
		pilot.add(p3);
		
		String[][] seatMap = { { "  ", "1A", "1B", "  "},
				   				{ "  ", "2A" ,"2B", "  " },
				   				{ "3A", "3B", "3C", "3D", },
				   				{ "4A", "4B", "4C", "4D", } };
		
		Flight f1 = new Flight("ROA to PHX", "2022-11-12", "am", seatMap, p1);
		flight.add(f1);
		Flight f2 = new Flight("PHX to ROA", "2022-11-12", "am", seatMap, p2);
		flight.add(f2);
		Flight f3 = new Flight("ROA to PHX", "2022-11-12", "pm", seatMap, p3);
		flight.add(f3);
		Flight f4 = new Flight("PHX to ROA", "2022-11-12", "pm", seatMap, p1);
		flight.add(f4);
		Flight f5 = new Flight("ROA to PHX", "2022-11-12", "am", seatMap, p2);
		flight.add(f5);
		Flight f6 = new Flight("PHX to ROA", "2022-11-12", "am", seatMap, p3);
		flight.add(f6);
		Flight f7 = new Flight("ROA to PHX", "2022-11-12", "pm", seatMap, p1);
		flight.add(f7);
		Flight f8 = new Flight("PHX to ROA", "2022-11-12", "pm", seatMap, p2);
		flight.add(f8);
		
		
		
		
		
	int choice = 0;
	while(choice !=11) {
		choice = menu();
		if(choice == 1) {
			AirlineDriver.printSeatMap(flight);
		} else if (choice ==2) {
			AirlineDriver.printFlightInfo(flight);
		} else if (choice == 3) {
			AirlineDriver.printPilots(pilot, flight);
		} else if (choice == 4) {
			AirlineDriver.printReservation();
		} else if (choice == 5) {
			AirlineDriver.printCustomerByNum();
		} else if (choice ==6) {
			AirlineDriver.bookReservation(cust, pilot, res, flight);
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
	System.out.println("1.  Print a map of the seats by flight number.");
	System.out.println("2.  Print flight information.");
	System.out.println("3.  Print a Pilots Schedule.");
	System.out.println("4.  Print the Reservations by Flight and Seat Number.");
	System.out.println("5.  Print Customer information by Number customer Number.");
	System.out.println("6.  Book a Reservation.");
	System.out.println("7.  Cancel a Reservation.");
	System.out.println("8.  Generate Profit by flight number.");
	System.out.println("9.  Search Reservations");
	System.out.println("10. Search deleted flights by reservation number.");
	System.out.println("11.  Exit.");
	int ans = scan.nextInt();
	return ans;
	
	}
	
	
	
}

