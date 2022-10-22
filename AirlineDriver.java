package macawsProject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectInputStream.GetField;
import java.text.NumberFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;

/**
 * AirlineDriver Class
 * 
 * Creates a driver class to test the functionality of the airline reservation system
 * 
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */
public class AirlineDriver {

    /**
     * Print a the seat map of the airplane Each Seat is represented by a row number and seat letter
     * First Class is rows 1-2 and Economy is rows 3-4 First Class has 2 seats per row and Economy
     * has 4 seats per row both divided by a aisle Use a 2D array to represent the seats and print
     * the seat map
     * 
     * @param f - Flight seat map
     */
    public static void printSeatMap(ArrayList<Flight> f) {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
        	System.out.println();
            System.out.println(
                "***Input the Flight Number you want to print out the seats for (For a general layout, enter '1')***");
            System.out.println();
            System.out.println("Flight Numbers: ");
            System.out.println();
            for (int i = 0; i < f.size(); i++) {
                System.out.println(f.get(i).getFlightNum() + "\n---");
            }
            int flightss = scan.nextInt();
            scan.nextLine();
            if (flightss == 1) {

                // Create a 2D array to represent the seats
            	System.out.println();
                System.out.println("Airplane Seat Map");
                System.out.println("=========================");
                String[][] seatMap = { { "  ", "1A", "1B", "  " },
                                       { "  ", "2A", "2B", "  " },
                                       { "3A", "3B", "3C", "3D", },
                                       { "4A", "4B", "4C", "4D", } };

                // Print the seat map
                for (int i = 0; i < seatMap.length; i++) {
                    for (int j = 0; j < seatMap[i].length; j++) {
                        System.out.print("| " + seatMap[i][j] + " |");
                    }
                    System.out.println();
                }
            } else {

                for (int i = 0; i < f.size(); i++) {
                    if (flightss == f.get(i).getFlightNum()) {
                        System.out.println(f.get(i).toMString());
                    }
                }
            }
            System.out.println("More flights? true/false");
            more = scan.nextBoolean();
        }

    }

    public static void printFlightInfo(ArrayList<Flight> f) {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
        	System.out.println();
            System.out.println(
                "***Please select the numerical value for the Flight that you would like to print information on***");
            System.out.println();
            for (int i = 0; i < f.size(); i++) {
                System.out.println((i + 1) + ":     Flight Number " + f.get(i).getFlightNum()
                    + " Flying " + f.get(i).getRoute() + " on " + f.get(i).getDate() + " at "
                    + f.get(i).getTime() + ".\n----------------------------------------------------------------");
            }
            int flightnum = scan.nextInt();
            scan.nextLine();
            flightnum = flightnum - 1;
            System.out.println(f.get(flightnum).toMString());
            System.out.println("\nMore flights? true/false");
            more = scan.nextBoolean();
            
        }
        
    }

    public static void printPilots(ArrayList<Pilot> p, ArrayList<Flight> f) {
        // 11. We need an option for printing each Pilots schedule for the week
        // (which flights are they flying---date, time direction.
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
        	System.out.println();
            System.out.println("***Input the Pilot's Number you would like information on***");
            System.out.println();
            for (int i = 0; i < p.size(); i++) {
                System.out.println("Pilot's Number: " + p.get(i).getPilotNum() + " || Their Name: "
                    + p.get(i).getPilotName() + ".\n---------------------------------------------");
            }
            int pilotn = scan.nextInt();
            System.out.println();
            for (int i = 0; i < f.size(); i++) {
                if (pilotn == f.get(i).getP().getPilotNum()) {
                    System.out.println("The Details for this Pilot's Flight Number: "
                        + f.get(i).getFlightNum() + f.get(i).toString());
                    System.out.println(f.get(i).toMString() + "\n");
                }
            }

            System.out.println("More Pilots? true/false");
            more = scan.nextBoolean();
        } // more loop

        //scan.close();
    }

    public static void addCustomer(ArrayList<Customer> c) {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
            System.out.println("What's the Customer's first name?");
            String fname = scan.nextLine();
            System.out.println("What's the Customer's last name?");
            String Lname = scan.nextLine();
            System.out.println("What's the Customer's email?");
            String email = scan.nextLine();
            Customer c1 = new Customer(fname, Lname, email);
            c.add(c1);
            System.out.println();
            System.out.println("***Customer Added!***");
            System.out.println();
            System.out.println("More Customers? true/false");
            more = scan.nextBoolean();
        }
    }

    public static void printReservation(ArrayList<Reservation> r) {
        // print who is sitting in a certain seat not a requirement but probably a good idea
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        System.out.println();
        System.out.println("***Current Reservations***");
        // Search for zero reservations.
        if (r.size() == 0) {
        	System.out.println();
        	System.out.println("(No Reservations were found)");
        }
        System.out.println();
        while (more) {
            for (int i = 0; i < r.size(); i++)
                System.out.println(r.get(i).toString());
            System.out.println("More Reservations? true/false");
            more = scan.nextBoolean();
        }
    }

    public static void printCustomerByNum(ArrayList<Customer> c) {
        Scanner scan = new Scanner(System.in);
        boolean more = true;

        // 12. We need an option for printing all the reservations for a give Customer if given
        // their customer number.
        System.out.println();
        System.out.println("***Current Customers***");
        System.out.println();
        while (more) {
            for (int i = 0; i < c.size(); i++) {
                System.out.println(c.get(i).toString());
            }
            System.out.println("More Customers? true/false");
            more = scan.nextBoolean();
        }
    }

    public static void bookReservation(ArrayList<Customer> c, ArrayList<Pilot> p,
            ArrayList<Reservation> r, ArrayList<Flight> f) {
            Scanner scan = new Scanner(System.in);
            
            boolean more = true;
            int moreseats = 0;// base counter
            double cost = 0;// base cost holder
            Flight ff = f.get(0);// base flight holder
            int findnums = 0;
            int partyNum = 0;// amount of people in the party
            int flightnum = 0;// flight
            ArrayList<String> seatn = new ArrayList<String>();// array to hold seat numbers

            // the following are base arrays
            String[][] seatMString = { { "NA", "1A", "1B", "NA" },
                                       { "NA", "2A", "2B", "NA" },
                                       { "3A", "3B", "3C", "3D", },
                                       { "4A", "4B", "4C", "4D", } };
            int[][] seatMID = { { -1, 1, 1, -1 },
                                { -1, 1, 1, -1 },
                                { 2, 2, 2, 2 },
                                { 2, 2, 2, 2 } };
            int[][] seatscust = { { -1, 0, 0, -1 },
                                  { -1, 0, 0, -1 },
                                  { 0, 0, 0, 0 },
                                  { 0, 0, 0, 0 } };

            // base rows for 2d arrays
            int row1 = 0;
            int seatNum = 0;

            while (more) { 
                // do you want a new customer?
                boolean repeat = true;
                System.out.println("Do you need to add a New Customer? Yes/No");
                String answer = scan.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    addCustomer(c);
                }

                // customer selection
                System.out.println();
                System.out.println("***Please select a numerical value for the Customer you want***");
                System.out.println();
                for (int i = 0; i < c.size(); i++) {
                    System.out.println((i + 1) + ":    " + c.get(i).toString());
                }
                int custNum = scan.nextInt();
                custNum = custNum - 1;
                Customer cc = c.get(custNum);// needed to add into the reservation at the bottom

                // selects the amount of seats needed
                System.out.println("How many people are in your party (seats needed)?");
                partyNum = scan.nextInt();

                // function to select the flight, and pick their individual seats. They are asked after
                // seeing available seats if they want to switch planes.
                while (repeat) {
                    System.out.println();
                    System.out.println("***Please select a numerical value for the Flight you want***");
                    System.out.println();
                    for (int i = 0; i < f.size(); i++) {
                        System.out.println((i + 1) + ":     Flight Number " + f.get(i).getFlightNum()
                            + " Flying " + f.get(i).getRoute() + " on " + f.get(i).getDate() + " at "
                            + f.get(i).getTime() + "."
                            + "\n----------------------------------------------------------------");
                    }
                    flightnum = scan.nextInt();
                    flightnum = flightnum - 1;
                    ff = f.get(flightnum);
                    scan.nextLine();// scanner problem
                    moreseats = 0;

                    System.out.println(
                        "Rows 1 and 2 seats cost $850. Coach seats are $450. 'na' Seats are not available.");
                    System.out.println(f.get(flightnum).toMPString());
                    System.out.println("Does the following flight have the seat(s) you need? (Enter yes/no)"
                    		+ "\n***Seats labeled with all numbers or 'na' are NOT available***");
                    String answ = scan.nextLine();
                    if (answ.equalsIgnoreCase("yes")) {
                        System.out.println("Rows 1 and 2 seats cost $850. Coach seats are $450."
                        		+ " 'na' Seats are not available.");
                        System.out.println(f.get(flightnum).toMPString());
                        repeat = false;
                    } else {
                        System.out.println("Try again");
                    }
                }
                // function to add seats each run through adds one seat, with the actual added to the
                // ArrayList
                // for a reservation happening at the end after the total loop is done
                while (moreseats != partyNum) {

                    // gets the private maps from the flight
                  boolean fix=true;
                	while (fix) {
                	seatMID = (f.get(flightnum).getIdmap());
                    seatMString = (f.get(flightnum).getPmap());
                    seatscust = (f.get(flightnum).getCustidmap());
                    System.out.println("Please enter the seat's Row (rows range from 1 - 4):");
                    row1 = scan.nextInt();
                    scan.nextLine();// scanner problem
                    System.out.println("Please enter the seat's Number (seats range from 1 - 4):");
                    seatNum = scan.nextInt();
                    scan.nextLine();// scanner problem
                    
                    
                    // sets the seats back to the correct index also checks to see if they can book the seat
                	row1 = row1 - 1;
                    seatNum = seatNum - 1;
                    String seatfix=seatMString[row1][seatNum];
                    if (seatfix.equalsIgnoreCase("na")) {
                    	System.out.println("Please select a different seat this is not avalible.");
                    }else {
                    	fix=false;
                    }
                	      	}
                	
                    
                   

                    // adding the cost together
                    String seatNums = seatMString[row1][seatNum];
                    if (seatNums.equalsIgnoreCase("1a") || (seatNums.equalsIgnoreCase("1b"))
                        || (seatNums.equalsIgnoreCase("2a")) || (seatNums.equalsIgnoreCase("2b"))) {
                        cost = cost + 850;
                    } else {
                        cost = cost + 450;
                    }
                    // adds the seat to the array list
                    seatn.add(seatNums);
                    // sets the seat arrays
                    seatscust[row1][seatNum] = c.get(custNum).getCustNum();
                    seatMID[row1][seatNum] = c.get(custNum).getCustNum() + partyNum;
                    findnums = seatMID[row1][seatNum];// important for the last function allows us to
                                                      // switch it to the reservation number
                    seatMString[row1][seatNum] = "na";
                    f.get(flightnum).setPmap(seatMString);
                    f.get(flightnum).setIdmap(seatMID);
                    f.get(flightnum).setCustidmap(seatscust);
                    System.out.println("This is the seat number: " + seatNum);
                    System.out.println(f.get(flightnum).toMPString());
                    moreseats++;
                    // added one seat and returned all arrays back to their home, this is done each time
                    // for each addition
                }
                // setting the profit after calculating the total cost
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                double javaisfun = f.get(flightnum).getProfit();
                javaisfun = javaisfun + cost;
                f.get(flightnum).setProfit(javaisfun);

                // booking reservation
                System.out.println("Booked reservation for: " + c.get(custNum).getfirstName() + " "
                    + c.get(custNum).getlastName() + " on flight " + f.get(flightnum).getFlightNum()
                    + " flying " + f.get(flightnum).getRoute() + " on " + f.get(flightnum).getDate()
                    + " at " + f.get(flightnum).getTime() + " with " + partyNum
                    + " seat(s) for a total cost of " + nf.format(cost) + ".");
                Reservation rr = new Reservation(partyNum, seatn, cost, cc, ff);
                r.add(rr);
                // finding the index of that reservation so we can set the one array to the reservation
                // number
                int index = r.indexOf(rr);
                int resnum = r.get(index).getResNum();
                seatMID = (f.get(flightnum).getIdmap());
                for (int i = 0; i < seatMID.length; i++) {
                    for (int j = 0; j < seatMID[i].length; j++) {
                        if (seatMID[i][j] == findnums) {
                            seatMID[i][j] = resnum;
                        }
                    }
                }
                f.get(flightnum).setIdmap(seatMID);
                cost = 0;
                // completely changes any thing that was added by the previous function to reservation
                // number so it can be removed if a reservation is canceled

                // FINALLY MORE RESERVATIONS???
                System.out.println("More Reservations? true/false");
                more = scan.nextBoolean();
                scan.nextLine();

            } // more reservations loop
        }

    public static void cancelReservation(ArrayList<Customer> c, ArrayList<Pilot> p,
        ArrayList<Reservation> r, ArrayList<Reservation> cr, ArrayList<Flight> f) {
        Scanner scan = new Scanner(System.in);
        
        // THIS ARRAY IS NEEDED TO ALLOW US TO RETURN THE Array people see when booking reservations
        // back to normal.
        String[][] other = { { "NA", "1A", "1B", "NA" },
                             { "NA", "2A", "2B", "NA" },
                             { "3A", "3B", "3C", "3D", },
                             { "4A", "4B", "4C", "4D", } };
        boolean more = true;
        while (more) {
        	
        	// Check for empty Reservation ArrayList.
        	if(r.size() == 0) {
        		System.out.println("***No Reservations were found***");
        		break;      		
        	}
      
            // select which reservation you want.
        	System.out.println();
            System.out.println("***Select the numerical value for the Reservation you want to cancel (type '0' to exit without change)***");
            System.out.println();
            for (int i = 0; i < r.size(); i++) {
                System.out.println((i + 1) + ": Reservation for " + r.get(i).getCust().getfirstName()
                    + " " + r.get(i).getCust().getlastName() + " (" + r.get(i).getCust().getEmail() + ") " + "on flight "
                    + r.get(i).getF().getFlightNum() + " flying " + r.get(i).getF().getRoute()
                    + " on " + r.get(i).getF().getDate() + " at " + r.get(i).getF().getTime()
                    + "\n--------------------------------------------------------------------------------------------------------------");
            }
            int resnum = scan.nextInt();
            if (resnum == 0)
            	break;
            resnum = resnum - 1;
            Reservation rr = r.get(resnum);
            cr.add(rr);
            // add it to our canceled ArrayList

            // getting all our flight seat arrays so we can remove this reservation from them
            String[][] seatMString = (r.get(resnum).getF().getPmap());
            int[][] custidArray = (r.get(resnum).getF().getCustidmap());
            int[][] seatMID = (r.get(resnum).getF().getIdmap());
            int findnum = r.get(resnum).getResNum();
            // removing the reservation
            for (int i = 0; i < seatMID.length; i++) {
                for (int j = 0; j < seatMID[i].length; j++) {
                    if (seatMID[i][j] == findnum) {
                        seatMID[i][j] = 0;// this is the reservation number array
                        seatMString[i][j] = other[i][j];// this is the array the customers see when
                                                        // booking
                        custidArray[i][j] = 0;// this is the array we see when we want to see who is
                                              // booking a seat
                    }
                }
            }
            // putting the arrays back after we removed the reservation
            r.get(resnum).getF().setIdmap(seatMID);
            r.get(resnum).getF().setPmap(seatMString);
            r.get(resnum).getF().setCustidmap(custidArray);
            // removing the profit from the flight
            double javaisfun = r.get(resnum).getF().getProfit();
            double javaisreallyfun = r.get(resnum).getCost();
            javaisfun = javaisfun - javaisreallyfun;
            r.get(resnum).getF().setProfit(javaisfun);
            r.remove(rr);// finally removing the reservation 
            
            System.out.println();
            System.out.println("***Reservation Canceled***");
            System.out.println();
            System.out.println("More Cancelations? true/false");
            more = scan.nextBoolean();
          	// Check for empty Reservation ArrayList.
        	if(r.size() == 0 && more) {
        		System.out.println("***No Reservations were found***");
        		more = false;      		
        	}

        }
    }

    public static void printGrossIncome(ArrayList<Flight> f, ArrayList<Reservation> r) {
        // print the Gross income of the flight by flight number
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            System.out.println();
            System.out.println("***What do you want?***");
            System.out.println();
            System.out.println("1.   The total profit for all schedualed flights.");
            System.out.println("2.   An individual flight.");
            int answer = scan.nextInt();
            double profit = 0;
            double add = 0;
            double total = 0;
            if (answer == 1) {
                for (int i = 0; i < r.size(); i++) {
                    add = r.get(i).getCost();
                    total = total + add;
                }
                System.out.println();
                System.out.println("***Total profit is: " + nf.format(total) + "***");
            } else {
                System.out.println("***Input the Flight Number that you want to print out Gross Income for***");
                System.out.println();
                for (int i = 0; i < f.size(); i++) {
                    System.out.println("Flight Number: " + f.get(i).getFlightNum() 
                    					+ "\n-------------------");
                }

                int flightss = scan.nextInt();
                scan.nextLine();
                for (int i = 0; i < r.size(); i++) {
                    if (r.get(i).getF().getFlightNum() == flightss) {
                    		add = r.get(i).getCost();
                    		profit = profit + add;
                    }
                }
                System.out.println();
                System.out.println("***Gross income for flight number " + flightss + " is "
                    + nf.format(profit) + "***");
            }
            System.out.println();
            System.out.println("More Profit information? true/false");
            more = scan.nextBoolean();
        }
    }

    public static void closeConnection() {
        // Close the connection
    }

    public static void searchReservation(ArrayList<Reservation> r) {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
            // Search for an empty reservation array.
            System.out.println();
            if (r.size() == 0) {
            	System.out.println("***No Reservations were found***");
            	break;
            }
        	System.out.println();
            System.out.println("How would you like to search the Reservations?");
            System.out.println("1.     By Last Name.");
            System.out.println("2.     By Email.");
            System.out.println("3.     By Reservation Number.");
            System.out.println("4.     By Customer Number.");
            System.out.println("5.     Just list them.");
            int find = scan.nextInt();
            scan.nextLine();// scanner problems
            boolean wastrue = true;
            if (find == 1) {
                System.out.println("Please enter the Customer's last name:");
                String enter = scan.nextLine();
                for (int i = 0; i < r.size(); i++) {
                    if (enter.equalsIgnoreCase(r.get(i).getCust().getlastName())) {
                        System.out.println(r.get(i).toString());
                        wastrue = false;
                    }                
                    else if (wastrue && i == r.size() - 1) {                 
                		System.out.println("***There are no records under that parameter***");
                		System.out.println();
                    }
                }
            } else if (find == 2) {
                System.out.println("Please enter the Customers email:");
                String enter = scan.nextLine();
                for (int i = 0; i < r.size(); i++) {
                    if (enter.equalsIgnoreCase(r.get(i).getCust().getEmail())) {
                        System.out.println(r.get(i).toString());
                        wastrue = false;
                    }                
                	else if (wastrue && i == r.size() - 1) {             
                		System.out.println("***There are no records under that parameter***");
                		System.out.println();
                	}
                }
            } else if (find == 3) {
                System.out.println("Please enter the Reservation Number:");
                int enter = scan.nextInt();
                scan.nextLine();
                for (int i = 0; i < r.size(); i++) {
                    if (enter == r.get(i).getResNum()) {
                        System.out.println(r.get(i).toString());
                        wastrue = false;
                    }           
                    else if (wastrue && i == r.size() - 1) {                   
                    	System.out.println("***There are no records under that parameter***");
                    	System.out.println();
                    }
                }
            } else if (find == 4) {
                System.out.println("Please enter the Customer Number:");
                int enter = scan.nextInt();
                scan.nextLine();
                for (int i = 0; i < r.size(); i++) {
                    if (enter == r.get(i).getCust().getCustNum()) {                 
                        System.out.println(r.get(i).toString());
                        wastrue = false;   
                    }
                    else if (wastrue && i == r.size() - 1) {              
                    	System.out.println("***There are no records under that parameter***");
                    	System.out.println();
                    }
                }
            } else if (find == 5) {
            	System.out.println("***Displaying All Reservations***");
            	System.out.println();
                for (int i = 0; i < r.size(); i++) {
                	 System.out.println((i + 1) + ": Reservation for: " + r.get(i).getCust().getfirstName() 
        					 + " " + r.get(i).getCust().getlastName() + " Customer Number: " + r.get(i).getCust().getCustNum()
        					 + " (" + r.get(i).getCust().getEmail() + ")" + " Resvervation Number: " + 
                             + r.get(i).getResNum() + " on flight " + r.get(i).getF().getFlightNum() + " flying "
                             + r.get(i).getF().getRoute() + " on " + r.get(i).getF().getDate() + " at "
                             + r.get(i).getF().getTime()
                	 		 + "\n--------------------------------------------------------------------------------------------"
                	 		 + "--------------------------------------------------------------------");
        
                }
                System.out.println();
            }
            System.out.println("More Searches? true/false");
            more = scan.nextBoolean();
        }

    }

    public static void searchDeleted(ArrayList<Reservation> cr) {
        // search deleted reservations
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
        	System.out.println();
            System.out.println("How would you like to search the Canceled Reservations?");
            System.out.println("1.     By Last Name.");
            System.out.println("2.     By Email.");
            System.out.println("3.     By Reservation Number.");
            System.out.println("4.     By Customer Number.");
            System.out.println("5.     Just list them.");
            System.out.println();
            int find = scan.nextInt();
            scan.nextLine();// scanner problems
            boolean wastrue = true;
            if (find == 1) {
                System.out.println("Please enter the Customer's last name:");
                String enter = scan.nextLine();
                for (int i = 0; i < cr.size(); i++) {
                    if (enter.equalsIgnoreCase(cr.get(i).getCust().getlastName())) {
                        System.out.println(cr.get(i).toCString());
                        wastrue = false;             
                    }
                    else if (wastrue && i == cr.size() - 1) { 
                    	System.out.println("***There are no records under that parameter***");
                    	System.out.println();
                    }
                }            
            } else if (find == 2) {
                System.out.println("Please enter the Customer's email:");
                String enter = scan.nextLine();
                for (int i = 0; i < cr.size(); i++) {
                    if (enter.equalsIgnoreCase(cr.get(i).getCust().getEmail())) {
                        System.out.println(cr.get(i).toCString());
                        wastrue = false;
                    }
                
                	else if (wastrue && i == cr.size() - 1) {
                		System.out.println("***There are no records under that parameter***");
                		System.out.println();
                	}
                }
            } else if (find == 3) {
                System.out.println("Please enter the Reservation Number:");
                int enter = scan.nextInt();
                scan.nextLine();
                for (int i = 0; i < cr.size(); i++) {
                    if (enter == cr.get(i).getResNum()) {
                        System.out.println(cr.get(i).toCString());
                        wastrue = false;
                    }                
                    else if (wastrue && i == cr.size() - 1) {                 
                    	System.out.println("***There are no records under that parameter***");
                    	System.out.println();
                    }
                }
            } else if (find == 4) {
                System.out.println("Please enter the Customer's number:");
                int enter = scan.nextInt();
                scan.nextLine();
                for (int i = 0; i < cr.size(); i++) {
                    if (enter == cr.get(i).getCust().getCustNum()) {
                        System.out.println(cr.get(i).toCString());
                        wastrue = false;
                    }
                	else if (wastrue && i == cr.size() - 1) {                 
                		System.out.println("***There are no records under that parameter***");
                		System.out.println();
                	}
                }
            } else if (find == 5) {
            	System.out.println("***Displaying All Canceled Reservations***");
            	System.out.println();
                for (int i = 0; i < cr.size(); i++) {
                    System.out.println((i + 1) + ": Canceled Reservation for: " + cr.get(i).getCust().getfirstName() 
                    					 + " " + cr.get(i).getCust().getlastName() + " Customer Number: " + cr.get(i).getCust().getCustNum()
                    					 + " (" + cr.get(i).getCust().getEmail() + ")" + " Resvervation Number: " + 
                                         + cr.get(i).getResNum() + " on flight " + cr.get(i).getF().getFlightNum() + " flying "
                                         + cr.get(i).getF().getRoute() + " on " + cr.get(i).getF().getDate() + " at "
                                         + cr.get(i).getF().getTime()
                                         + "\n--------------------------------------------------------------------------------------------"
                            	 		 + "----------------------------------------------------------------------------");
                    
                }
                System.out.println();
            }
            System.out.println("More Searches? true/false");
            more = scan.nextBoolean();
        }
    }

    /*
     * Helper Method to add seats each run through adds one seat, 
     * with the actual added to the ArrayList for a reservation happening 
     * at the end after the total loop is done
     * 
     * 
     */
}
