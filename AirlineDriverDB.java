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
 * Creates a driver class to test the functionality of the airline reservation system Uses MySQL
 * stored procedures to access the database and perform CRUD operations
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

        }
        catch (Exception e) {
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
     * Print all flights
     * by flight_id showing the origin, destination, departure date, time, and seats available
     * Use the stored procedure: print_flight_all
     */
    public static void printFlightAll() {
        checkConnect();
        try {
            ResultSet rs = stmt.executeQuery("CALL print_flight_all();");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print("  |  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    } // End of method printFlightAll.


    /**
     * Print the seat map of a given flight
     * Use the stored procedure: print_flight_seats
     * 1. Call the printFlightAll() method to display all flights
     * 2. Ask the user to enter a flight_id from the list
     * 3. Call the stored procedure print_flight_seats(flight_id)
     * 4. Display the seat map for that flight_id entered by the user
     * 5. Ask the the user if they want to see a different flight
     * 6. If yes, repeat steps 1-5
     * 7. If no, return to the main menu
     * @param flight_id - the flight id passed in
     */
    public static void printSeatMap(String flight_id) {
        checkConnect();
        Scanner input = new Scanner(System.in);
        flight_id = ""; // Initialize flight_id to an empty string.
        String answer = ""; // Variable to hold user's answer.
        do {
            printFlightAll(); // Call the printFlightAll method.
            System.out.println("Enter a Flight ID from the list above: "); // Prompt user for flight_id.
            flight_id = input.nextLine(); // Store user's input in flight_id.
            try {
                ResultSet rs = stmt.executeQuery("CALL print_flight_seats(" + flight_id + ");"); // Call the stored procedure.
                ResultSetMetaData rsmd = rs.getMetaData(); // Get the metadata.
                int columnsNumber = rsmd.getColumnCount(); // Get the number of columns.
                while (rs.next()) { // Loop through the result set.
                    for (int i = 1; i <= columnsNumber; i++) { // Loop through the columns. As long as i is less than or equal to the number of columns, increment i.
                        if (i > 1) System.out.print("  |  "); // If i is greater than 1, print a separator.
                        String columnValue = rs.getString(i); // Store the value of the column in columnValue.
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue); // Print the column name and the value.
                    }
                    System.out.println("");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Would you like to see a different flight? (y/n)");
            answer = input.nextLine();
        }
        while (answer.equalsIgnoreCase("y"));
    } // End of method printSeatMap.

    public static void printFlightInfo() {

    	// Call the checkConnect method for database connectivity.
    	checkConnect();
    	// Build an ArrayList for the flights.
    	ArrayList<Integer> fNums = new ArrayList<>();;
    	String stored = null;
    	int choice = 0;
    	ResultSet rs = null;
    	Scanner scan = new Scanner(System.in);
    	
    	boolean more = true;
    	while(more) {
    		
    		stored = "CALL macaws.print_flight_nums();";

    		try {
    			
    			stmt = conn.prepareCall(stored);			
    			rs = stmt.executeQuery(stored);
    			System.out.println();
    			System.out.println("Flight Numbers:");
    			System.out.println();
    			
    			// Create a while loop to cycle through each column and store each query item.
    			while(rs.next()) {
    				
    				// Store each query item at a variable.
    				int fNum = rs.getInt("flight_id");
    			
    				// Display the flight numbers.
    				System.out.println(fNum);
    				System.out.println("-----------");
    			} // Bottom of while loop.
    			
    			System.out.println();
    			System.out.println("***Please input a Flight Number for a flight's information***");
    		
    			choice = scan.nextInt();   			
    			
    			stored = "CALL macaws.print_flight_info('" + choice + "');";
    			
    			try {
    				
        			stmt = conn.prepareCall(stored);			
        			rs = stmt.executeQuery(stored);
        			
        			System.out.println();       			
        			System.out.println("Flight Number:    |    Route:    |     Depart Date:    |     Time:");
        			System.out.println();
        			
        			while(rs.next()) {
        				
        				int flightNum = rs.getInt("flight_id");
        				String origin = rs.getString("origin");
        				String des = rs.getString("destination");
        				String date = rs.getString("depart_date");
        				String time = rs.getString("time");
        				
            			System.out.println(flightNum + "           " + origin + " to " + des + "          " + date + "            " + time);
        			} // Bottom of while loop.   				
    			} // End of try block.
    			
        		catch(SQLException e) {
        			System.out.println("SQL Exception");
        			e.printStackTrace();
        		} // End of catch block.
    				
    			try {
    				System.out.println();
    				System.out.println("More flights? true/false");
    				more = scan.nextBoolean();

    			}
    			catch (Exception e) {
    				System.out.println("Invalid Input. Please enter 'true' or 'false'");
    				scan.nextLine();
    			}
    		} // End of try block.
    		
    		catch(SQLException e) {
    			System.out.println("SQL Exception");
    			e.printStackTrace();
    		} // End of catch block.
    		
    	} // Bottom of while loop.
    } // End of method printFlightInfo.

    /**
     * Prints all the pilots and their corresponding schedules
     * Uses the stored procedure: all_pilots()
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
                        " | Time: " + rs.getString("Time"));
            }
        } // End of try block.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL insert Exception");
        } // End of catch block.
    }

    /**
     * Let's the user add a new customer to the database
     * Uses the stored procedure: add_customer()
     * A customer must have a first name, last name, and email
     */
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
            String stored = "CALL macaws.add_customer('" + fName + "', '" + lName + "', '" + email
                + "');";

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

    /**
     * Prints all the reservations in the system
     * Uses the stored procedure: print_reservations()
     */
    public static void printReservation() {
        // Call the checkConnect method for database connectivity.
        checkConnect();
        String stored = "CALL macaws.print_reservations();";

        try {
            stmt = conn.prepareCall(stored);
            ResultSet rs = stmt.executeQuery(stored);
            while (rs.next()) {
                System.out.printf(
                    "Flight ID: %-10d | Reservation: %-2d | Customer: %-20s | Seat: %-2s %n",
                    rs.getInt("Flight ID"),
                    rs.getInt("Reservation ID"),
                    rs.getString("Customer Name"),
                    rs.getString("Seat #"));
            }
        } // End of try block.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL insert Exception");
        } // End of catch block.
    }

    /**
     * Prints all the customers in the system ordering by Customer ID
     * Uses the stored procedure: all_customers()
     */
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

    /**
     * 1. "Let's start a new reservation."
     * 2. "Is this for a new customer? (true/false)"
     * 3. If true run the addCustomer() method.
     * 4. If false run the printCustomerByNum() method.
     * 5. "Please enter a customer number from the list."
     * 6. Take input and input into the add_reservation stored procedure.
     * 7. Query the database for the reservation number we just created and store it in a variable.
     * 8. Use the printFlightAll() method to print all the flights.
     * 9. Use the  "Please enter a flight number from the list."
     * 10. Use the printSeatMap() method to print the seat map.
     * 11. "Please enter the Seat ID you would like to reserve."
     * 12. Take input and input into the add_seat_to_reservation stored procedure.
     * 13. "Would you like to add another seat to this reservation? (true/false)"
     * 14. If true go back to step 10.
     * 15. If false go back to main menu.
     */
    public static void bookReservation() {
        // Call the checkConnect method for database connectivity.
        checkConnect();
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        while (more) {
            System.out.println("Let's start a new reservation.");
            System.out.println("Is this for a new customer? (true/false)");
            boolean newCustomer = scan.nextBoolean();
            scan.nextLine();
            if (newCustomer) {
                addCustomer();
            }
            else {
                printCustomerByNum();
            }
            System.out.println("Please enter a customer number from the list.");
            int customerNum = scan.nextInt();
            scan.nextLine();

            // Store a procedure call in a String variable to add a new reservation.
            String stored = "CALL macaws.add_reservation(" + customerNum + ");";

            // Try to execute the SQL statement in stored variable.
            try {
                stmt = conn.prepareCall(stored);
                stmt.executeUpdate(stored);
            } // End of try block.
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL insert Exception");
            } // End of catch block.

            // Query the database for the reservation number we just created and store it in a variable.
            String stored2 = "CALL macaws.get_last_reservation_id(" + customerNum + ");";

            // Try to execute the SQL statement in stored variable.
            try {
                stmt = conn.prepareCall(stored2);
                ResultSet rs = stmt.executeQuery(stored2);
                while (rs.next()) {
                    int reservationNum = rs.getInt("reservation_id");
                    System.out.println("Your Reservation Number Is: " + reservationNum);
                    printFlightAll();
                    System.out.println("Please enter a flight number from the list.");
                    String flightNum = scan.nextLine();
                    // printSeatMap of the flight entered by the user in the previous step.
                    printSeatMap(flightNum);
                    System.out.println("Please enter the Seat ID you would like to reserve. \n"
                        + "Only seats with an Availability of OPEN are able to be reserved.");                      
                    int seatNum = scan.nextInt();
                    scan.nextLine();

                    // Store a procedure call in a String variable to add a new reservation.
                    String stored3 = "CALL macaws.add_seat_to_reservation(" + reservationNum + ", "
                        + seatNum + ");";

                    // Try to execute the SQL statement in stored variable.
                    try {
                        stmt = conn.prepareCall(stored3);
                        stmt.executeUpdate(stored3);
                    } // End of try block.
                    catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("SQL insert Exception");
                    } // End of catch block.

                    System.out.println("Would you like to add another seat to this reservation? (true/false)");
                    more = scan.nextBoolean();
                    scan.nextLine();
                }
            } // End of try block.
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL insert Exception");
            } // End of catch block.
        } // End of while loop.                   
    }

    public static void printGrossIncome() {
        // print the Gross income of the flight by flight number
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        // Call the checkConnect method for database connectivity.
        checkConnect();
        // Store the procedure call in a String variable.
        String stored = null;
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
                    while (rs.next()) {
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
                    while (rs.next()) {

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
                    while (rs.next()) {

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

    /**
     * Search reservations by Reservation ID or ask to to print all reservations using printReservations method.
     */
    public static void searchReservation() {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        // Call the checkConnect method for database connectivity.
        checkConnect();
        // Store the procedure call in a String variable.
        String stored = null;

        while (more) {
            System.out.println();
            System.out.println("1. Search by Reservation ID.");
            System.out.println("2. Print all reservations.");
            try {
                int answer = scan.nextInt();
                // User selected menu 1.
                if (answer == 1) {
                    System.out.println();
                    printReservation();
                    System.out.println("Input the Reservation ID that you want to search for");
                    System.out.println();
                    int resID = scan.nextInt(); // Get the reservation ID from the user.
                    scan.nextLine(); // Clear the buffer.
                    
                    stored = "CALL macaws.search_reservation('" + resID + "');"; // Call the stored procedure.
                    stmt = conn.prepareCall(stored); // Prepare the statement.
                    ResultSet rs = stmt.executeQuery(stored); // Execute the query.
                    
                    // Loop through and display each reservation.
                    while (rs.next()) { // Loop through the result set.
                        int fltNum = rs.getInt("Flight ID"); // Get the flight ID.
                        int resNum = rs.getInt("Reservation ID"); // Get the reservation ID.
                        String custName = rs.getString("Customer Name"); // Get the customer name.
                        String seat = rs.getString("Seat #"); // Get the seat number.
                        int cost = rs.getInt("Cost"); // Get the cost.
                        System.out.printf(
                            "Flight ID: %-10d | Reservation: %-2d | Customer: %-20s | Seat: %-2s | Cost: %-2d %n",
                                fltNum, resNum, custName, seat, cost);                                 
                    } // Bottom of while loop.
                } // End of if block.

                // User selected menu 2.
                else {
                    printReservation();
                } // End of else block.

                System.out.println();
                try {
                    System.out.println();
                    System.out.println("More Reservation searching? true/false");
                    more = scan.nextBoolean();

                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please enter 'true' or 'false'");
                    scan.nextLine();
                }

            } // End of try block.
            catch (Exception e) {
                System.out.println("That Reservation does not exist. Please choose a valid Reservation ID");
                scan.nextLine();
            }
        } // Bottom of while loop.
    }

    /**
     * Cancel a reservation by Reservation ID.
     * Uses the cancel_reservation stored procedure.
     * 1. Print all reservations.
     * 2. Input the reservation ID that you want to cancel.
     * 3. Call the cancel_reservation stored procedure.
     * 4. Display the reservation that was canceled.
     * 5. Ask the user if they want to cancel another reservation.
     * 6. If yes, go back to step 1.
     * 7. If no, go back to the main menu.
     */
    public static void cancelReservation() {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        // Call the checkConnect method for database connectivity.
        checkConnect();
        // Store the procedure call in a String variable.
        String stored = null;
        try {
            while (more) {
                System.out.println();
                printReservation();
                System.out.println("Input the Reservation ID that you want to cancel");
                System.out.println();
                int resID = scan.nextInt(); // Get the reservation ID from the user.
                scan.nextLine(); // Clear the buffer.
                
                stored = "CALL macaws.cancel_reservation('" + resID + "');"; // Call the stored procedure.
                stmt = conn.prepareCall(stored); // Prepare the statement.
                ResultSet rs = stmt.executeQuery(stored); // Execute the query.
                
                // Loop through and display each reservation.
                while (rs.next()) { // Loop through the result set.
                    int fltNum = rs.getInt("Flight ID"); // Get the flight ID.
                    int resNum = rs.getInt("Reservation ID"); // Get the reservation ID.
                    String custName = rs.getString("Customer Name"); // Get the customer name.
                    String seat = rs.getString("Seat #"); // Get the seat number.
                    int cost = rs.getInt("Cost"); // Get the cost.
                    System.out.printf(
                        "Flight ID: %-10d | Reservation: %-2d | Customer: %-20s | Seat: %-2s | Cost: %-2d %n",
                            fltNum, resNum, custName, seat, cost);                                 
                } // Bottom of while loop.
                
                System.out.println();
                try {
                    System.out.println();
                    System.out.println("More Reservation canceling? true/false");
                    more = scan.nextBoolean();

                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please enter 'true' or 'false'");
                    scan.nextLine();
                }
            } // Bottom of while loop.
        } // End of try block.
        catch (Exception e) {
            System.out.println("That Reservation does not exist. Please choose a valid Reservation ID");
            scan.nextLine();
        }
    }

    public static void printCanceledReservation() {
        // Call the checkConnect method for database connectivity.
        checkConnect();
        // Store the procedure call in a String variable.
        String stored = null;
        stored = "CALL macaws.print_canceled_reservations();";
        try {
            stmt = conn.prepareCall(stored);
            ResultSet rs = stmt.executeQuery(stored);
            // Loop through and display each reservation.
            while (rs.next()) {
                int fltNum = rs.getInt("Flight ID");
                int resNum = rs.getInt("Reservation ID");
                String custName = rs.getString("Customer Name");
                String seat = rs.getString("Seat #");
                int cost = rs.getInt("Cost");
                System.out.printf(
                    "Flight ID: %-10d | Reservation: %-2d | Customer: %-20s | Seat: %-2s | Cost: %-2d %n",
                        fltNum, resNum, custName, seat, cost);
            } // Bottom of while loop.
        } // End of try block.
        catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    public static void searchDeleted() {
        Scanner scan = new Scanner(System.in);
        boolean more = true;
        // Call the checkConnect method for database connectivity.
        checkConnect();
        // Store the procedure call in a String variable.
        String stored = null;

        while (more) {
            System.out.println();
            System.out.println("1. Search by Reservation ID.");
            System.out.println("2. Print all canceled reservations.");
            try {
                int answer = scan.nextInt();
                // User selected menu 1.
                if (answer == 1) {
                    System.out.println();
                    printCanceledReservation();
                    System.out.println("Input the Reservation ID that you want to search for");
                    System.out.println();
                    int resID = scan.nextInt(); // Get the reservation ID from the user.
                    scan.nextLine(); // Clear the buffer.
                    
                    stored = "CALL macaws.search_deleted('" + resID + "');"; // Call the stored procedure.
                    stmt = conn.prepareCall(stored); // Prepare the statement.
                    ResultSet rs = stmt.executeQuery(stored); // Execute the query.
                    
                    // Loop through and display each reservation.
                    while (rs.next()) { // Loop through the result set.
                        int fltNum = rs.getInt("Flight ID"); // Get the flight ID.
                        int resNum = rs.getInt("Reservation ID"); // Get the reservation ID.
                        String custName = rs.getString("Customer Name"); // Get the customer name.
                        String seat = rs.getString("Seat #"); // Get the seat number.
                        int cost = rs.getInt("Cost"); // Get the cost.
                        System.out.printf(
                            "Flight ID: %-10d | Reservation: %-2d | Customer: %-20s | Seat: %-2s | Cost: %-2d %n",
                                fltNum, resNum, custName, seat, cost);                                 
                    } // Bottom of while loop.
                } // End of if block.

                // User selected menu 2.
                else {
                    printCanceledReservation();
                } // End of else block.

                System.out.println();
                try {
                    System.out.println();
                    System.out.println("More Reservation searching? true/false");
                    more = scan.nextBoolean();

                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please enter 'true' or 'false'");
                    scan.nextLine();
                }

            } // End of try block.
            catch (Exception e) {
                System.out.println("That Reservation does not exist. Please choose a valid Reservation ID");
                scan.nextLine();
            }
        } // Bottom of while loop.
    }
}
