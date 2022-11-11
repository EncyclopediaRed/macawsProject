package macawsProject;

import java.util.Scanner;

/**
 * Creates a Menu Class to detail what makes up a UI menu for a airline reservation system
 * Uses MySQL stored procedures to access the database and perform CRUD operations
 *
 * @author R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */
public class MenuDB {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("***Welcome to Java Airlines!***");
        int choice = 0; // initialize choice to 0
        while (choice != 12) { // while choice is not 12, keep looping
            choice = menu(); // call menu method and assign choice to the returned value
            if (choice == 0) {
                AirlineDriverDB.createConnection();
            } else if (choice == 1) { 
                AirlineDriver.printSeatMap(flight);
            } else if (choice == 2) {
                AirlineDriver.printFlightInfo(flight);
            } else if (choice == 3) {
                AirlineDriver.printPilots(pilot, flight);
            } else if (choice == 4) {
                AirlineDriver.printReservation(res);
            } else if (choice == 5) {
                AirlineDriver.printCustomerByNum(cust);
            } else if (choice == 6) {
                AirlineDriver.bookReservation(cust, pilot, res, flight);
            } else if (choice == 7) {
                AirlineDriver.cancelReservation(cust, pilot, res, resCanceled, flight);
            } else if (choice == 8) {
                AirlineDriver.printGrossIncome(flight, res);
            } else if (choice == 9) {
                AirlineDriver.searchReservation(res);
            } else if (choice == 10) {
                AirlineDriver.searchDeleted(resCanceled);
            } else if (choice == 11) {
                AirlineDriver.addCustomer(cust);
            } else if (choice == 12) {
                AirlineDriver.closeConnection();
                System.out.println("Thank you for choosing Java Airlines!\nHave a good day :)");
                System.exit(0);
            }
        }

    }

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nMenu:");
        System.out.println("0. Connect to the Database");
        System.out.println("1.  Print a map of the seats by Flight Number.");
        System.out.println("2.  Print Flight information.");
        System.out.println("3.  Print a Pilot's Schedule.");
        System.out.println("4.  Print the Reservations by Flight and Seat Number.");
        System.out.println("5.  Print Customer information by Customer Number.");
        System.out.println("6.  Book a Reservation.");
        System.out.println("7.  Cancel a Reservation.");
        System.out.println("8.  Generate Profit by Flight Number.");
        System.out.println("9.  Search Reservations");
        System.out.println("10. Find canceled flights.");
        System.out.println("11. Add a Customer.");
        System.out.println("12. Exit.");
        int ans = scan.nextInt();
        return ans;

    }

}
