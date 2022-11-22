package macawsProject;

import java.util.Scanner;

/**
 * Creates a Menu Class to detail what makes up a UI menu for a airline reservation system Uses
 * MySQL stored procedures to access the database and perform CRUD operations
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
                AirlineDriverDB.printSeatMap();
//            } else if (choice == 2) {
//                AirlineDriverDB.printFlightInfo();
            } else if (choice == 3) {
                AirlineDriverDB.printPilots();
            } else if (choice == 4) {
                AirlineDriverDB.printReservation();
            } else if (choice == 5) {
                AirlineDriverDB.printCustomerByNum();
            } else if (choice == 6) {
                AirlineDriverDB.bookReservation();
            } else if (choice == 7) {
//                AirlineDriverDB.cancelReservation();
            } else if (choice == 8) {
                AirlineDriverDB.printGrossIncome();
            } else if (choice == 9) {
                AirlineDriverDB.searchReservation();
//            } else if (choice == 10) {
//                AirlineDriverDB.searchDeleted();
            } else if (choice == 11) {
                AirlineDriverDB.addCustomer();
            } else if (choice == 12) {
                AirlineDriverDB.closeConnection();
                System.out.println("Thank you for choosing Java Airlines!\nHave a good day :)");
                System.exit(0);
            }
        }

    }

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nMenu:");
        System.out.println("0.  Connect to the Database");
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