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
     */
    public static void printSeatMap() {
        checkConnect();
        Scanner input = new Scanner(System.in);
        String flight_id = ""; // Initialize flight_id to an empty string.
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

    // Using the stored procedures add_reservation and then add_seat_to_reservation to add a reservation
    // to the database.
    /*
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
            System.out.println("***Search Reservation?***");
            System.out.println();
            System.out.println("1.   Search by Reservation ID.");
            System.out.println("2.   Print all reservations.");
            try {
                int answer = scan.nextInt();
                // User selected menu 1.
                if (answer == 1) {
                    System.out.println();
                    System.out.println("*** Input the Reservation ID that you want to search for ***");
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
