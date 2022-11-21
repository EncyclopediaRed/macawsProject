package macawsProject;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.NumberFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * AirlineDriver Class
 * 
 * Creates a driver class to test the functionality of the airline reservation system
 * Uses MySQL stored procedures to access the database and perform CRUD operations
 * 
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */
public class AirlineDriverDB {


    // Create static instances of the Connection, Statement, and Scanner Classes.
	static Connection conn = null;
	static Statement stmt = null;
    
	// Create a method that creates a new connection to a database.
	@SuppressWarnings("deprecation")
    public static Connection createConnection() {
		
		// Ask the user for user name, password, and database name.
		String user = "itp220";
		String pass = "itp220";
		String name = "macaws";
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/" + name;

		System.out.println(driver);
		System.out.println(url);
		
		// Create a try block that attempts to find the database.
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection really is from : " + conn.getClass().getName());
			System.out.println("Connection successful!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the connection.
		return conn;
	} // End of method createConnection.
	
	// Create a method that will close the connection to the database.
	public static void closeConnection() {
		
		// Check if connection is null.
		if (conn != null) {
			// Close the connection.
			try {
				conn.close();
				conn = null;
				// stmt.close();
				System.out.println("The connection was successfully closed");
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		} // End of if block.
	} // End of method closeConnection.
	
	// Create a method that checks the connection status of the database.
	public static void checkConnect() {
		
		// Check to see if the connection is null.
		if (conn == null) {
			conn = createConnection();
		}
		// Check to see if the Statement is null.
		if (stmt == null) {
			try {
				stmt = conn.createStatement();
			} 
			catch (SQLException e) {
				System.out.println("Cannot create the statement");
			}
		} // End of if block.
	} // End of method checkConnect.

    /**
     * Print a the seat map of the airplane Each Seat is represented by a row number and seat letter
     * First Class is rows 1-2 and Economy is rows 3-4 First Class has 2 seats per row and Economy
     * has 4 seats per row both divided by a aisle. Use the seat_map procedure to get the seat map
     * 
     * @param f - Flight seat map
     */
    public static void printSeatMap(ArrayList<Flight> f) {
        // Create a for loop to print the seat map.
        for (int i = 0; i < f.size(); i++) {
            // Create a try block that attempts to print the seat map.
            try {
                // Create a String variable to hold the seat map.
                String seatMap = "CALL seat_map(" + f.get(i).getFlightNum() + ")";
                // Create a ResultSet variable to hold the seat map.
                java.sql.ResultSet rs = stmt.executeQuery(seatMap);
                // Create a while loop to print the seat map.
                while (rs.next()) {
                    // Print the seat map.
                    System.out.println(rs.getString(1));
                } // End of while loop.
            } // End of try block.
            catch (SQLException e) {
                e.printStackTrace();
            } // End of catch block.
        } // End of for loop.
    } // End of method printSeatMap.

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
                    + f.get(i).getTime()
                    + ".\n----------------------------------------------------------------");
            }

            try {
                int flightnum = scan.nextInt();
                scan.nextLine();
                flightnum = flightnum - 1;
                System.out.println(f.get(flightnum).toMString());

                try {
                    System.out.println();
                    System.out.println("More flights? true/false");
                    more = scan.nextBoolean();

                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please enter 'true' or 'false'");
                    scan.nextLine();
                }

            }
            catch (Exception e) {
                System.out.println(
                    "Invalid Input. Please try again, with a flight number from the list.");
                scan.nextLine();
            }

        }

    }

    /**
     * Print the flight information for the flight number given
     * 
     * @param f - Flight information
     */
    public static void printPilots() {
        // Call the checkConnect method for database connectivity.
        checkConnect();
        String stored = "CALL macaws.all_pilots();";

        try {
            stmt = conn.prepareCall(stored);
            ResultSet rs = stmt.executeQuery(stored);
            while (rs.next()) {
                System.out.println(
                    "Flight ID: " + rs.getInt("Flight ID") + 
                    " | Pilot Name: " + rs.getString("Pilot Name") + 
                    " | Origin: " + rs.getString("Origin") +
                    " | Destination: " + rs.getString("Destination") +
                    " | Departing: " + rs.getString("Departing") +
                    " | Time: " + rs.getString("Time")
                    );
            }
        } // End of try block.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL insert Exception");
        } // End of catch block.
    }

    public static void addCustomer() {
		// Call the checkConnect method for database connectivity.
		checkConnect();
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
            System.out.println("What's the Customer's first name?");
            String fName = scan.nextLine();
            System.out.println("What's the Customer's last name?");
            String lName = scan.nextLine();
            System.out.println("What's the Customer's email?");
            String email = scan.nextLine();
            
			// Store a procedure call in a String variable to add a new customer.
			String stored = "CALL macaws.add_customer('" + fName + "', '" + lName + "', '" + email + "');";
			
			// Try to execute the SQL statement in stored variable.
			try {
				stmt = conn.prepareCall(stored);
				stmt.executeUpdate(stored);
			} // End of try block.
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL insert Exception");
			} // End of catch block.

            System.out.println();
            System.out.println("***Customer Added!***");
            System.out.println();
            try {
                System.out.println();
                System.out.println("More Customers? true/false");
                more = scan.nextBoolean();
                scan.nextLine();

            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter 'true' or 'false'");
                scan.nextLine();
                scan.nextLine();
            }
        }
    }

    public static void printReservation(ArrayList<Reservation> r) {
        // print who is sitting in a certain seat not a requirement but probably a good idea
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        System.out.println();
        System.out.println("***Current Reservations***");
        // Search for zero reservations.
        if (r.size() == 0 || r == null) {
            System.out.println();
            System.out.println("(No Reservations were found)");
        }
        System.out.println();
        while (more) {
            for (int i = 0; i < r.size(); i++)
                System.out.println(r.get(i).toString());
            try {
                System.out.println();
                System.out.println("More Reservations? true/false");
                more = scan.nextBoolean();

            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter 'true' or 'false'");
                scan.nextLine();
            }
        }
    }

    /* Print the customer table using a ResultSet and the all_customers stored procedure */
    public static void printCustomerByNum() {
        // Call the checkConnect method for database connectivity.
        checkConnect();
        String stored = "CALL macaws.all_customers();";

        try {
            stmt = conn.prepareCall(stored);
            ResultSet rs = stmt.executeQuery(stored);
            while (rs.next()) {
                System.out
                    .println("Customer ID: " + rs.getInt("customer_id") + 
                             " | Name: " + rs.getString("Name") + 
                             " | Email: " + rs.getString("email"));
            }
        } // End of try block.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL insert Exception");
        } // End of catch block.
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
            System.out.println();
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

            try {
                int custNum = scan.nextInt();
                custNum = custNum - 1;
                Customer cc = c.get(custNum);// needed to add into the reservation at the bottom

                // selects the amount of seats needed
                System.out.println("How many people are in your party (seats needed)?");
                partyNum = scan.nextInt();

                // function to select the flight, and pick their individual seats. They are asked
                // after
                // seeing available seats if they want to switch planes.
                while (repeat) {
                    System.out.println();
                    System.out
                        .println("***Please select a numerical value for the Flight you want***");
                    System.out.println();
                    for (int i = 0; i < f.size(); i++) {
                        System.out.println((i + 1) + ":     Flight Number "
                            + f.get(i).getFlightNum()
                            + " Flying " + f.get(i).getRoute() + " on " + f.get(i).getDate()
                            + " at "
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
                    System.out
                        .println(
                            "Does the following flight have the seat(s) you need? (Enter yes/no)"
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

                // function to add seats each run through adds one seat, with the actual added to
                // the
                // ArrayList
                // for a reservation happening at the end after the total loop is done
                while (moreseats != partyNum) {

                    // gets the private maps from the flight
                    boolean fix = true;
                    while (fix) {
                        seatMID = (f.get(flightnum).getIdmap());
                        seatMString = (f.get(flightnum).getPmap());
                        seatscust = (f.get(flightnum).getCustidmap());
                        System.out.println("Please enter the seat's Row (rows range from 1 - 4):");
                        row1 = scan.nextInt();
                        scan.nextLine();// scanner problem
                        System.out
                            .println("Please enter the seat's Number (seats range from 1 - 4):");
                        seatNum = scan.nextInt();
                        scan.nextLine();// scanner problem

                        // sets the seats back to the correct index also checks to see if they can
                        // book
                        // the seat
                        row1 = row1 - 1;
                        seatNum = seatNum - 1;
                        String seatfix = seatMString[row1][seatNum];
                        if (seatfix.equalsIgnoreCase("na")) {
                            System.out
                                .println("Please select a different seat this is not available.");
                        } else {
                            fix = false;
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
                    findnums = seatMID[row1][seatNum];// important for the last function allows us
                                                      // to
                                                      // switch it to the reservation number
                    seatMString[row1][seatNum] = "na";
                    f.get(flightnum).setPmap(seatMString);
                    f.get(flightnum).setIdmap(seatMID);
                    f.get(flightnum).setCustidmap(seatscust);
                    System.out.println("This is the seat number: " + seatNum);
                    System.out.println(f.get(flightnum).toMPString());
                    moreseats++;
                    // added one seat and returned all arrays back to their home, this is done each
                    // time
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
                // finding the index of that reservation so we can set the one array to the
                // reservation
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
                // completely changes any thing that was added by the previous function to
                // reservation
                // number so it can be removed if a reservation is canceled

                // FINALLY MORE RESERVATIONS???
                try {
                    System.out.println();
                    System.out.println("More Reservations? true/false");
                    more = scan.nextBoolean();

                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please enter 'true' or 'false'");
                    scan.nextLine();
                }
                scan.nextLine();

            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter a Customer number listed above");
                scan.nextLine();
            }

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
            if (r.size() == 0) {
                System.out.println("***No Reservations were found***");
                break;
            }

            // select which reservation you want.
            System.out.println();
            System.out.println(
                "***Select the numerical value for the Reservation you want to cancel (type '0' to exit without change)***");
            System.out.println();
            for (int i = 0; i < r.size(); i++) {
                System.out.println((i + 1) + ": Reservation for "
                    + r.get(i).getCust().getfirstName()
                    + " " + r.get(i).getCust().getlastName() + " (" + r.get(i).getCust().getEmail()
                    + ") " + "on flight "
                    + r.get(i).getF().getFlightNum() + " flying " + r.get(i).getF().getRoute()
                    + " on " + r.get(i).getF().getDate() + " at " + r.get(i).getF().getTime()
                    + "\n--------------------------------------------------------------------------------------------------------------");
            }
            try {
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
                            seatMString[i][j] = other[i][j];// this is the array the customers see
                                                            // when
                                                            // booking
                            custidArray[i][j] = 0;// this is the array we see when we want to see
                                                  // who is
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
            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter a valid Reservation number");
                scan.nextLine();
            }

            // Check for empty Reservation ArrayList.
            if (r.size() == 0 && more) {
                System.out.println("***No Reservations were found***");
                more = false;
            }

            try {
                System.out.println();
                System.out.println("More Cancellations? true/false");
                more = scan.nextBoolean();

            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter 'true' or 'false'");
                scan.nextLine();
            }

        }
    }

    public static void printGrossIncome() {
        // print the Gross income of the flight by flight number
        Scanner scan = new Scanner(System.in);
        boolean more = true;
		// Call the checkConnect method for database connectivity.
		checkConnect();
    	// Store the procedure call in a String variable.
    	String stored = null
    			;
        while (more) {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            System.out.println();
            System.out.println("***What do you want?***");
            System.out.println();
            System.out.println("1.   The total profit for all scheduled flights.");
            System.out.println("2.   An individual flight.");
            try {
                int answer = scan.nextInt();
                double profit = 0;
                
                // User selected menu 1.
                if (answer == 1) {
                	stored = "CALL macaws.calc_total_profit();";
                
        			stmt = conn.prepareCall(stored);  		
        			ResultSet rs = stmt.executeQuery(stored);
        			// Get the Gross profit.
        			while(rs.next()) {
        				// Store the profit at the variable.
        				profit = rs.getDouble("Total Gross Profit"); 
        			} // Bottom of while loop.
        				// Display the Gross Profit.
                    	System.out.println();
                    	System.out.println("***Total profit is: " + nf.format(profit) + "***");
                } // End of if block. 
                
                // User selected menu 2.
                else {
                    
                    stored = "CALL macaws.print_flight_nums()";
            		stmt = conn.prepareCall(stored);  		
        			ResultSet rs = stmt.executeQuery(stored);
        			
        			// Loop through and display each flight ID.
        			while(rs.next()) {
        				
        				int flightNum = rs.getInt("flight_id");
        				System.out.println(flightNum);
        				System.out.println("---------");
        			} // Bottom of while loop.
                    System.out.println(
                            "***Input the Flight Number that you want to print out Gross Income for***");
        			System.out.println();
                    int flightss = scan.nextInt();
                    scan.nextLine();
                    
                    stored = "CALL macaws.calc_flight_profit('" + flightss + "');";
               		stmt = conn.prepareCall(stored);  		
            		rs = stmt.executeQuery(stored);
            		
            		// Loop through the price column and get the profit for this flight number.
            		while(rs.next()) {
            			
            			profit = rs.getDouble("Total Flight Profit");
            		} // Bottom of while loop.
            		
            		// Display the profit for this flight.
                    System.out.println();
                    System.out.println("***Gross income for flight number " + flightss + " is "
                        + nf.format(profit) + "***");
                } // End of else block.

                System.out.println();
                try {
                    System.out.println();
                    System.out.println("More Profit Information? true/false");
                    more = scan.nextBoolean();

                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please enter 'true' or 'false'");
                    scan.nextLine();
                }

            } // End of try block.
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter '1' or '2'");
                scan.nextLine();
            }
        } // Bottom of while loop.

    } // End of method print gross income.

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
                    } else if (wastrue && i == r.size() - 1) {
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
                    } else if (wastrue && i == r.size() - 1) {
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
                    } else if (wastrue && i == r.size() - 1) {
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
                    } else if (wastrue && i == r.size() - 1) {
                        System.out.println("***There are no records under that parameter***");
                        System.out.println();
                    }
                }
            } else if (find == 5) {
                System.out.println("***Displaying All Reservations***");
                System.out.println();
                for (int i = 0; i < r.size(); i++) {
                    System.out.println((i + 1) + ": Reservation for: "
                        + r.get(i).getCust().getfirstName()
                        + " " + r.get(i).getCust().getlastName() + " Customer Number: "
                        + r.get(i).getCust().getCustNum()
                        + " (" + r.get(i).getCust().getEmail() + ")" + " Reservation Number: " +
                        +r.get(i).getResNum() + " on flight " + r.get(i).getF().getFlightNum()
                        + " flying "
                        + r.get(i).getF().getRoute() + " on " + r.get(i).getF().getDate() + " at "
                        + r.get(i).getF().getTime()
                        + "\n--------------------------------------------------------------------------------------------"
                        + "--------------------------------------------------------------------");

                }
                System.out.println();
            }
            try {
                System.out.println();
                System.out.println("More Searches? true/false");
                more = scan.nextBoolean();

            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter 'true' or 'false'");
                scan.nextLine();
            }
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
                    } else if (wastrue && i == cr.size() - 1) {
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
                    } else if (wastrue && i == cr.size() - 1) {
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
                    } else if (wastrue && i == cr.size() - 1) {
                        System.out.println("***There are no records under that parameter***");
                        System.out.println();
                    }
                }
            } else if (find == 5) {
                System.out.println("***Displaying All Canceled Reservations***");
                System.out.println();
                for (int i = 0; i < cr.size(); i++) {
                    System.out.println((i + 1) + ": Canceled Reservation for: "
                        + cr.get(i).getCust().getfirstName()
                        + " " + cr.get(i).getCust().getlastName() + " Customer Number: "
                        + cr.get(i).getCust().getCustNum()
                        + " (" + cr.get(i).getCust().getEmail() + ")" + " Reservation Number: " +
                        +cr.get(i).getResNum() + " on flight " + cr.get(i).getF().getFlightNum()
                        + " flying "
                        + cr.get(i).getF().getRoute() + " on " + cr.get(i).getF().getDate() + " at "
                        + cr.get(i).getF().getTime()
                        + "\n--------------------------------------------------------------------------------------------"
                        + "----------------------------------------------------------------------------");

                }
                System.out.println();
            }
            try {
                System.out.println();
                System.out.println("More Searches? true/false");
                more = scan.nextBoolean();

            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please enter 'true' or 'false'");
                scan.nextLine();
            }
        }
    }
}
