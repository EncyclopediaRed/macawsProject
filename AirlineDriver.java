package macawsProject;

import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.metadata.IIOMetadataFormatImpl;

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
	public static void printSeatMap(ArrayList<Flight>f) {
		Scanner scan = new Scanner(System.in);
		boolean more=true;
		while (more) {
			System.out.println("What is the flight number of you want to print out the seats for? If you want a general layout enter 1.");
			for (int i =0; i<f.size();i++) {
			System.out.println(f.get(i).getFlightNum());
			}
			int flightss =scan.nextInt();
			scan.nextLine();
			if (flightss==1) {
			
				// Create a 2D array to represent the seats
				String[][] seatMap = { { "  ", "1A", "1B", "  "},
						{ "  ", "2A" ,"2B", "  " },
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
				}else {
					System.out.println();
		
					System.out.println("Airplane Seat Map");
					System.out.println("=========================");
					for (int i =0; i<f.size();i++) {
						if (flightss==f.get(i).getFlightNum()) {
							System.out.println(f.get(i).toMString());
						}
					}	
				}
			System.out.println("More flights? true/false");
			more=scan.nextBoolean();
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
	public static void printFlightInfo(ArrayList<Flight>f) {
		Scanner scan = new Scanner(System.in);
		boolean more=true;
		while (more) {
			System.out.println("Please select the flight that you would like to print the information on.");
			for (int i=0; i<f.size(); i++) {
				System.out.println((i +1) + ":     Flight Number " + f.get(i).getFlightNum() + " Flying " + f.get(i).getRoute() + " on " + f.get(i).getDate() + " at " + f.get(i).getTime() +".");
			}
			int flightnum=scan.nextInt();
			scan.nextLine();
			flightnum=flightnum-1;
			System.out.println(f.get(flightnum).toString());
			System.out.println("Airplane Seat Map");
			System.out.println("=========================");
			System.out.println(f.get(flightnum).toMString());
			System.out.println("\nMore flights? true/false");
			more=scan.nextBoolean();
			}
		}
		
		
	
	public static void printPilots(ArrayList<Pilot> p, ArrayList<Flight> f) {
    	//11.	We need an option for printing each Pilot’s schedule for the week 
		//(which flights are they flying---date, time direction.
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
			System.out.println("Please select the Pilot's number you would like information on.");
			for (int i=0; i<p.size(); i++) {
				System.out.println("Pilot's number: " + p.get(i).getPilotNum() + " Their Name: " + p.get(i).getPilotName() +".");
			}
			int pilotn=scan.nextInt();
			for (int i=0; i<f.size(); i++) {
				if (pilotn==f.get(i).getP().getPilotNum()) {
					System.out.println("The Details for this pilot's flight number: " + f.get(i).getFlightNum() + f.get(i).toString());
					System.out.println("Airplane Seat Map" + "\n=========================");
					System.out.println(f.get(i).toMString()+"\n");
					}
			}
			
			
			
		System.out.println("More Pilots? true/false");
		more=scan.nextBoolean();
		}//moreloop

    	}
	
	public static void addCustomer(ArrayList<Customer> c) {
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
			System.out.println("What's the customers First name?");
			String fname=scan.nextLine();
			System.out.println("What's the customers Last name?");
			String Lname=scan.nextLine();
			System.out.println("What's the customers email?");
			String email=scan.nextLine();
			Customer c1=new Customer(fname,Lname,email);
			c.add(c1);
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
	}
	
	public static void printReservation(ArrayList<Reservation> r) {
		// print who is sitting in a certian seat not a requriment but probaby a good idea
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
			for(int i=0; i<r.size();i++)
				System.out.println(r.get(i).toString());
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
	   	}
	
	public static void printCustomerByNum(ArrayList<Customer> c) {
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		
		//12.	We need an option for printing all the reservations for a give Customer if given their customer number.
		while (more) {
			for (int i=0; i<c.size();i++) {
				System.out.println(c.get(i).toString());
			}
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
	   	}
	
	public static void bookReservation(ArrayList<Customer> c, ArrayList<Pilot> p, ArrayList<Reservation> r, ArrayList<Flight> f) {
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		int moreseats=0;
		double cost=0;
		Flight ff=f.get(0);
		int partyNum=0;
		int flightnum=0;
		ArrayList<String> seatn =new ArrayList<String>();
		String[][] seatMString = { { "  ", "1A", "1B", "  "},
						{ "  ", "2A" ,"2B", "  " },
						{ "3A", "3B", "3C", "3D", },
						{ "4A", "4B", "4C", "4D", } };
		int[][] seatMID = { { -1, 1, 1, -1},
					{ -1, 1 ,1, -1},
						{ 2, 2, 2, 2 },
						{ 2, 2, 2, 2 } };
		int row1=0;
		int seatNum=0;
		boolean repete=true;
		while (more) {
			System.out.println("Do you need to add a new customer? Yes/No");
			String answer=scan.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
			addCustomer(c);
			}
			System.out.println("Please select the customer you want.");
			for(int i=0;i<c.size();i++) {
				System.out.println(i+ ":    " + c.get(i).toString());
			}
			int custNum=scan.nextInt();
			System.out.println("How many people are in your party?");
			partyNum=scan.nextInt();
			Customer cc=c.get(custNum);
			
			while (repete) {
				System.out.println("Please select the flight you want.");
				for (int i=0; i<f.size();i++) {
					System.out.println((i) + ":     Flight Number " + f.get(i).getFlightNum() + " Flying " + f.get(i).getRoute() + " on " + f.get(i).getDate() + " at " + f.get(i).getTime() +".");
				}
				flightnum=scan.nextInt();
				ff=f.get(flightnum);
				scan.nextLine();//scanner problem
				moreseats=0;
				System.out.println("Does the following flight have the seats you need? yes/no");
				System.out.println("Rows 1 and 2 seats cost 850. Coach seats are 450. na Seats are not avalible.");
				System.out.println("Airplane Seat Map" + "\n=========================");
				System.out.println(f.get(flightnum).toMString());
				String answ = scan.nextLine();
				if (answ.equalsIgnoreCase("yes")) {
					repete=false;
				}else {
					System.out.println("Try again");
				}
				}
				while (moreseats!=partyNum) {
					System.out.println("Does the following flight have the seats you need? yes/no");
					System.out.println("Rows 1 and 2 seats cost 850. Coach seats are 450. na Seats are not avalible.");
					System.out.println("Airplane Seat Map" + "\n=========================");
					System.out.println(f.get(flightnum).toMString());
						System.out.println("Please enter the seat's row, row starts at 1.");
							seatMID=(f.get(flightnum).getIdmap());
							seatMString=(f.get(flightnum).getPmap());
							row1 = scan.nextInt();
							scan.nextLine();
							System.out.println("Please enter the seat's number, seat starts at 1.");
							seatNum = scan.nextInt();
							scan.nextLine();
							row1=row1-1;
							seatNum=seatNum-1;
							String seatNums=seatMString[row1][seatNum];
							if (seatNums.equalsIgnoreCase("1a") ||(seatNums.equalsIgnoreCase("1b"))||(seatNums.equalsIgnoreCase("2a"))||(seatNums.equalsIgnoreCase("2b"))){
								//(f.get(flightnum).getProfit()+850);
								cost=cost+850;
								} else {
								//(f.get(flightnum).getProfit()+450);
								cost=cost+450;
								}
							seatn.add(seatNums);
							seatMID[row1][seatNum]=c.get(custNum).getCustNum();
							seatMString[row1][seatNum]="na";
							f.get(flightnum).setPmap(seatMString);
							f.get(flightnum).setIdmap(seatMID);
							System.out.println("this is the seat num" +seatNum);
							System.out.println(f.get(flightnum).toMString());
							moreseats++;
					}
				Reservation rr= new Reservation(partyNum,seatn,cost,cc,ff);
				r.add(rr);
				for (int i=0;i<r.size();i++) {
					System.out.println(r.get(i).toString());
				}
				System.out.println("More Reservations? true/false");
				more=scan.nextBoolean();
				}//more reservations loop
			
	   	}//more reservations loop

		
	
	
	public static void cancelReservation() {
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
    	}
	public static void printGrossIncome() {
		//print the Grossincome of the flight by flight number
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
    	}
	public static void closeConnection() {
    	//Close the connection
    	}
	public static void searchReservation() {
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
    	}
	public static void searchDeleted() {
		//search deleted reservations
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
		System.out.println("More Customers? true/false");
		more=scan.nextBoolean();
	   	}
    	}


}


