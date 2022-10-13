package macawsProject;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat; 
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
		int moreseats=0;//base counter
		double cost=0;//base cost holder
		Flight ff=f.get(0);//base flight holder
		int findnums=0;
		int partyNum=0;//amoutn of people in the party
		int flightnum=0;//flight
		ArrayList<String> seatn =new ArrayList<String>();//array to hold seat numbers
		
		//the following are base arrays
		String[][] seatMString = { { "NA", "1A", "1B", "NA"},
						{ "NA", "2A" ,"2B", "NA" },
						{ "3A", "3B", "3C", "3D", },
						{ "4A", "4B", "4C", "4D", } };
		int[][] seatMID = { { -1, 1, 1, -1},
					{ -1, 1 ,1, -1},
						{ 2, 2, 2, 2 },
						{ 2, 2, 2, 2 } };
		int[][] seatscust = { { -1, 0, 0, -1},
				{ -1, 0 ,0, -1},
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 } };
		
		//base rows for 2d arrays
		int row1=0;
		int seatNum=0;
		
		
		while (more) {
			//do you want a new customer?
			boolean repete=true;
			System.out.println("Do you need to add a new customer? Yes/No");
			String answer=scan.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
			addCustomer(c);
			}
			
			// customer selection
			System.out.println("Please select the customer you want.");
			for(int i=0;i<c.size();i++) {
				System.out.println(i+ ":    " + c.get(i).toString());
			}
			int custNum=scan.nextInt();
			Customer cc=c.get(custNum);//needed to add into the reservation at the bottom
			
			//selects the amount of seets needed
			System.out.println("How many people are in your party (seats needed)?");
			partyNum=scan.nextInt();
			
			//function to select the flight, and pick their individual seats. They are asked after seeing avalible seets if they want to switch planes.
			while (repete) {
				System.out.println("Please select the flight you want.");
				for (int i=0; i<f.size();i++) {
					System.out.println((i) + ":     Flight Number " + f.get(i).getFlightNum() + " Flying " + f.get(i).getRoute() + " on " + f.get(i).getDate() + " at " + f.get(i).getTime() +".");
				}
				flightnum=scan.nextInt();
				ff=f.get(flightnum);
				scan.nextLine();//scanner problem
				moreseats=0;
				
				System.out.println("Rows 1 and 2 seats cost 850. Coach seats are 450. na Seats are not avalible.");
				System.out.println("Airplane Seat Map" + "\n=========================");
				System.out.println(f.get(flightnum).toMPString());
				System.out.println("Does the following flight have the seats you need? yes/no\n Seats with all numbers or na are not avalible.");
				String answ = scan.nextLine();
				if (answ.equalsIgnoreCase("yes")) {
					System.out.println("Rows 1 and 2 seats cost 850. Coach seats are 450. na Seats are not avalible.");
					System.out.println("Airplane Seat Map" + "\n=========================");
					System.out.println(f.get(flightnum).toMPString());
					repete=false;
				}else {
					System.out.println("Try again");
				}
				}
			//function to add seats each run through adds one seat, with the actual added to the arraylist
			//for a reservation happening at the end after the total loop is done
				while (moreseats!=partyNum) {
						
							//gets the private maps from the flight
							seatMID=(f.get(flightnum).getIdmap());
							seatMString=(f.get(flightnum).getPmap());
							seatscust=(f.get(flightnum).getCustidmap());
							System.out.println("Please enter the seat's row, row starts at 1.");
							row1 = scan.nextInt();
							scan.nextLine();//scanner problem
							System.out.println("Please enter the seat's number, seat starts at 1.");
							seatNum = scan.nextInt();
							scan.nextLine();//scanner problem
							
							//sets the seats back to the correct index
							row1=row1-1;
							seatNum=seatNum-1;
							
							//adding the cost together
							String seatNums=seatMString[row1][seatNum];
							if (seatNums.equalsIgnoreCase("1a") ||(seatNums.equalsIgnoreCase("1b"))||(seatNums.equalsIgnoreCase("2a"))||(seatNums.equalsIgnoreCase("2b"))){
								cost=cost+850;
								} else {
								cost=cost+450;
								}
							//adds the seat to the array list
							seatn.add(seatNums);
							//sets the seat arrays
							seatscust[row1][seatNum]=c.get(custNum).getCustNum();
							seatMID[row1][seatNum]=c.get(custNum).getCustNum()+partyNum;
							findnums=seatMID[row1][seatNum];//important for the last function allows us to switch it to the reservation number
							seatMString[row1][seatNum]="na";
							f.get(flightnum).setPmap(seatMString);
							f.get(flightnum).setIdmap(seatMID);
							f.get(flightnum).setCustidmap(seatscust);
							System.out.println("this is the seat num" +seatNum);
							System.out.println(f.get(flightnum).toMPString());
							moreseats++;
							//added one seat and returned all arryas back to their home, this is done each time for each addition
					}
				//setting the profit after calculating the total cost
				NumberFormat nf = NumberFormat.getCurrencyInstance(); 
				double javaisfun=f.get(flightnum).getProfit();
				javaisfun=javaisfun+cost;
				f.get(flightnum).setProfit(javaisfun);
				
				//booking reservation
				System.out.println("Booked reservation for " + c.get(custNum).getfirstName()+ " "+c.get(custNum).getlastName()+ " on flight " + f.get(flightnum).getFlightNum() + " flying "+ f.get(flightnum).getRoute()+ " on " + f.get(flightnum).getDate()+ " at "+ f.get(flightnum).getTime()+ " with " + partyNum + " seats for a total cost of "+ nf.format(cost) +".");
				Reservation rr= new Reservation(partyNum,seatn,cost,cc,ff);
				r.add(rr);
				//finding the index of that reservation so we can set the one array to the reservation number
				int index = r.indexOf(rr);
				int resnum= r.get(index).getResNum();
				seatMID=(f.get(flightnum).getIdmap());
				for (int i = 0; i < seatMID.length; i++) {
			        for (int j = 0; j < seatMID[i].length; j++) {
			            if (seatMID[i][j] == findnums) {
			            	seatMID[i][j] = resnum;
			            }
			        }
				}
				f.get(flightnum).setIdmap(seatMID);
				//completly changes any thing that was added by the previous function to reservation
				//number so it can be removed if a reservation is cannceled
				
				//FINALLY MORE RESERVATIONS???
				System.out.println("More Reservations? true/false");
				more=scan.nextBoolean();
			 
				}//more reservations loop
}

		
	
	
	public static void cancelReservation(ArrayList<Customer> c, ArrayList<Pilot> p, ArrayList<Reservation> r, ArrayList<Reservation> cr,  ArrayList<Flight> f) {
		Scanner scan = new Scanner(System.in);
		//THIS ARRAY IS NEEDED TO ALLOW US TO RETURN THE Array people see when booking reservations back to normal.
		String[][] other = { { "NA", "1A", "1B", "NA"},
				{ "NA", "2A" ,"2B", "NA" },
				{ "3A", "3B", "3C", "3D", },
				{ "4A", "4B", "4C", "4D", } };
		boolean more =true;
		while (more) {
			
			//select which reservation you want.
			System.out.println("Which Reservation would you like to cancel?");
			for (int i=0;i<r.size();i++) {
				System.out.println(i+ ": Reservation for " + r.get(i).getCust().getfirstName()+ " "+r.get(i).getCust().getlastName()+ "on flight " + r.get(i).getF().getFlightNum() + " flying "+ r.get(i).getF().getRoute()+ " on " + r.get(i).getF().getDate()+ " at "+ r.get(i).getF().getTime());
				}
			int resnum=scan.nextInt();
			Reservation rr=r.get(resnum);
			cr.add(rr);
			//add it to our canceled arraylist
			
			//getting all our flight seat arrays so we can remove this resrvation from them
			String[][] seatMString = (r.get(resnum).getF().getPmap());
			int[][]custidArray=(r.get(resnum).getF().getCustidmap());
			int[][] seatMID= (r.get(resnum).getF().getIdmap());
			int findnum=r.get(resnum).getResNum();
			//removing the reservation
			for (int i = 0; i < seatMID.length; i++) {
		        for (int j = 0; j < seatMID[i].length; j++) {
		            if (seatMID[i][j] == findnum) {
		            	seatMID[i][j] = 0;//this is the reservation number array
		            	seatMString[i][j]=other[i][j];//this is the array the customers see when booking
		            	custidArray[i][j]=0;//this is the array we see when we want to see who is booking a seat
		            }
		        }
			}
			//putting the arrays back after we removed the reservation
			r.get(resnum).getF().setIdmap(seatMID);
			r.get(resnum).getF().setPmap(seatMString);
			r.get(resnum).getF().setCustidmap(custidArray);
			//removing the profit from the flight
			double javaisfun=r.get(resnum).getF().getProfit();
			double javaisreallyfun=r.get(resnum).getCost();
			javaisfun=javaisfun-javaisreallyfun;
			r.get(resnum).getF().setProfit(javaisfun);
			r.remove(rr);//finally removing the reservation
						
		System.out.println("More Cancelations? true/false");
		more=scan.nextBoolean();
	   	}
    	}
	public static void printGrossIncome(ArrayList<Flight> f) {
		//print the Grossincome of the flight by flight number
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			System.out.println("What do you want?");
			System.out.println("1.   The total profit for all schedualed flights.");
			System.out.println("2.   An individual flight.");
			int answer=scan.nextInt();
			double profit=0;
			double add=0;
			double total=0;
			if (answer==1) {
				for (int i =0; i<f.size();i++) {
					add=f.get(i).getProfit();
					total=total+add;
				}
				
				System.out.println("Total profit is " + nf.format(total)+".");
			}else { 
			System.out.println("Which is the flight do you want to print out gross income for?");
			for (int i =0; i<f.size();i++) {
				System.out.println("Flight number: "+f.get(i).getFlightNum());
			}
			
				int flightss =scan.nextInt();
				scan.nextLine();
				for (int i=0;i<f.size();i++) {
					if(f.get(i).getFlightNum()==flightss);
					 profit = f.get(i).getProfit();
				}
			System.out.println("Gross income for flight number " + flightss + " is " + nf.format(profit)+".");
			}
		System.out.println("More Profit information? true/false");
		more=scan.nextBoolean();
		}
    	}
	public static void closeConnection() {
    	//Close the connection
    	}
	public static void searchReservation(ArrayList<Reservation> r) {
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
			
					System.out.println("How would you like to search the  reservations?");
					System.out.println("1.     By Last Name.");
					System.out.println("2.     By Email.");
					System.out.println("3.     By Reservation Number.");
					System.out.println("4.     By Costomer Number.");
					System.out.println("5.     Just list them.");
					int find=scan.nextInt();
					scan.nextLine();//scanner problems
					boolean wastrue=true;
					if(find==1) {
						System.out.println("Please enter the customers last name.");
						String enter = scan.nextLine();
						for (int i=0;i<r.size();i++) {
							if(enter.equalsIgnoreCase(r.get(i).getCust().getlastName())){
								System.out.println(r.get(i).toString());
							wastrue=false;
							}}if (wastrue);
						System.out.println("There are no records under that peramiter.");
					}else if (find==2) {
						System.out.println("Please enter the customers email.");
						String enter = scan.nextLine();
						for (int i=0;i<r.size();i++) {
							if(enter.equalsIgnoreCase(r.get(i).getCust().getlastName())) {
							System.out.println(r.get(i).toString());
							wastrue=false;
							}}if (wastrue);
						System.out.println("There are no records under that peramiter.");
					}else if (find==3) {
						System.out.println("Please enter the Reservation number.");
						int enter = scan.nextInt();
						scan.nextLine();
						for (int i=0;i<r.size();i++) {
							if(enter==r.get(i).getResNum()){
							System.out.println(r.get(i).toString());
							wastrue=false;
							}}if (wastrue);
						System.out.println("There are no records under that peramiter.");
					}else if (find==4) {
						System.out.println("Please enter the customer number.");
						int enter = scan.nextInt();
						scan.nextLine();
						for (int i=0;i<r.size();i++) {
							if(enter==r.get(i).getCust().getCustNum());{
							System.out.println(r.get(i).toString());
							wastrue=false;
							}}if (wastrue);
						System.out.println("There are no records under that peramiter.");
					}else if (find==5) {
						for (int i=0;i<r.size();i++) {
							System.out.println(i+ ": Canceled Reservation for " + r.get(i).getCust().getfirstName()+ " "+r.get(i).getCust().getlastName()+ "on flight " + r.get(i).getF().getFlightNum() + " flying "+ r.get(i).getF().getRoute()+ " on " + r.get(i).getF().getDate()+ " at "+ r.get(i).getF().getTime());
							}
					}
					
				System.out.println("More Searches? true/false");
				more=scan.nextBoolean();
			   	}
		
	   	}
    	

	public static void searchDeleted(ArrayList<Reservation> r) {
		//search deleted reservations
		Scanner scan = new Scanner(System.in);
		boolean more =true;
		while (more) {
			System.out.println("How would you like to search the canceled reservations?");
			System.out.println("1.     By Last Name.");
			System.out.println("2.     By Email.");
			System.out.println("3.     By Reservation Number.");
			System.out.println("4.     By Costomer Number.");
			System.out.println("5.     Just list them.");
			int find=scan.nextInt();
			scan.nextLine();//scanner problems
			boolean wastrue=true;
			if(find==1) {
				System.out.println("Please enter the customers last name.");
				String enter = scan.nextLine();
				for (int i=0;i<r.size();i++) {
					if(enter.equalsIgnoreCase(r.get(i).getCust().getlastName().toString())){
					System.out.println(r.get(i).toCString());
					wastrue=false;
				}}if (wastrue);
				System.out.println("There are no records under that peramiter.");
			}else if (find==2) {
				System.out.println("Please enter the customers email.");
				String enter = scan.nextLine();
				for (int i=0;i<r.size();i++) {
					if(enter.equalsIgnoreCase(r.get(i).getCust().getlastName().toString())) {
					System.out.println(r.get(i).toCString());
					wastrue=false;
					}}if (wastrue);
				System.out.println("There are no records under that peramiter.");
			}else if (find==3) {
				System.out.println("Please enter the Reservation number.");
				int enter = scan.nextInt();
				scan.nextLine();
				for (int i=0;i<r.size();i++) {
					if(enter==r.get(i).getResNum()) {
					System.out.println(r.get(i).toCString());
					wastrue=false;
				}}if (wastrue);
				System.out.println("There are no records under that peramiter.");
			}else if (find==4) {
				System.out.println("Please enter the customers number.");
				int enter = scan.nextInt();
				scan.nextLine();
				for (int i=0;i<r.size();i++) {
					if(enter==r.get(i).getCust().getCustNum()) {
					System.out.println(r.get(i).toCString());
					wastrue=false;
				}}if (wastrue);
				System.out.println("There are no records under that peramiter.");
			}else if (find==5) {
				for (int i=0;i<r.size();i++) {
					System.out.println(i+ ": Canceled Reservation for " + r.get(i).getCust().getfirstName()+ " "+r.get(i).getCust().getlastName()+ "on flight " + r.get(i).getF().getFlightNum() + " flying "+ r.get(i).getF().getRoute()+ " on " + r.get(i).getF().getDate()+ " at "+ r.get(i).getF().getTime());
					}}
			
			
		System.out.println("More Searches? true/false");
		more=scan.nextBoolean();
	   	}
    }


}


