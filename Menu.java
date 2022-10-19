package macawsProject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a Menu Class to detail what makes up a UI menu for a airline reservation system
 *
 * @author R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */
public class Menu {

    public static void main(String[] args) {

        ArrayList<Customer> cust = new ArrayList<Customer>();
        ArrayList<Pilot> pilot = new ArrayList<Pilot>();
        ArrayList<Reservation> res = new ArrayList<Reservation>();
        ArrayList<Flight> flight = new ArrayList<Flight>();
        ArrayList<Reservation> resCanceled = new ArrayList<Reservation>();

        Customer c1 = new Customer("Connor", "Hogg", "Hogg.Connor@gmail.com");
        cust.add(c1);
        Customer c2 = new Customer("David", "Bryan", "David.Bryan@gmail.com");
        cust.add(c2);
        Customer c3 = new Customer("John", "Gafildafield", "John.Gafildafield@gmail.com");
        cust.add(c3);
        Customer c4 = new Customer("Terry", "Bubbkis", "Terry.Bubbkis@gmail.com");
        cust.add(c4);
        Customer c5 = new Customer("Larry", "Outhouse", "Larry.Outhouse@gmail.com");
        cust.add(c5);
        Customer c6 = new Customer("Meg", "Griffen", "Meg.Griffen@gmail.com");
        cust.add(c6);
        Customer c7 = new Customer("Charles", "Hoag", "Charles.Hoag@gmail.com");
        cust.add(c7);
        Customer c8 = new Customer("Larry", "Harder", "Larry.Harder@gmail.com");
        cust.add(c8);
        Customer c9 = new Customer("James", "Madison", "James.Madison@gmail.com");
        cust.add(c9);
        Customer c10 = new Customer("Finley", "Davis", "Finley.Davis@gmail.com");
        cust.add(c10);

        Pilot p1 = new Pilot("Pilot 1");
        pilot.add(p1);
        Pilot p2 = new Pilot("Pilot 2");
        pilot.add(p2);
        Pilot p3 = new Pilot("Pilot 3");
        pilot.add(p3);

        String[][] seatMap = { { "na", "1A", "1B", "na" },
                               { "na", "2A", "2B", "na" },
                               { "3A", "3B", "3C", "3D", },
                               { "4A", "4B", "4C", "4D", } };
        int[][] seats = { { -1, 1, 1, -1 },
                          { -1, 1, 1, -1 },
                          { 2, 2, 2, 2 },
                          { 2, 2, 2, 2 } };
        int[][] seatscust = { { -1, 0, 0, -1 },
                              { -1, 0, 0, -1 },
                              { 0, 0, 0, 0 },
                              { 0, 0, 0, 0 } };

        Flight f1 = new Flight("ROA to PHX", "2022-11-12", "am", seatMap, seats, seatscust, p1);
        flight.add(f1);
        Flight f2 = new Flight("PHX to ROA", "2022-11-12", "am", seatMap, seats, seatscust, p2);
        flight.add(f2);
        Flight f3 = new Flight("ROA to PHX", "2022-11-12", "pm", seatMap, seats, seatscust, p3);
        flight.add(f3);
        Flight f4 = new Flight("PHX to ROA", "2022-11-12", "pm", seatMap, seats, seatscust, p1);
        flight.add(f4);
        Flight f5 = new Flight("ROA to PHX", "2022-11-12", "am", seatMap, seats, seatscust, p2);
        flight.add(f5);
        Flight f6 = new Flight("PHX to ROA", "2022-11-12", "am", seatMap, seats, seatscust, p3);
        flight.add(f6);
        Flight f7 = new Flight("ROA to PHX", "2022-11-12", "pm", seatMap, seats, seatscust, p1);
        flight.add(f7);
        Flight f8 = new Flight("PHX to ROA", "2022-11-12", "pm", seatMap, seats, seatscust, p2);
        flight.add(f8);

        ArrayList<String> seatss = new ArrayList<String>();
        seatss.add("1A");
        seatss.add("1B");

        Reservation r1 = new Reservation(2, seatss, 1700, c1, f1, 1030);
        String[][] seatMString2 = { { "NA", "NA", "NA", "NA" },
                                    { "NA", "2A", "2B", "NA" },
                                    { "3A", "3B", "3C", "3D", },
                                    { "4A", "4B", "4C", "4D", } };

        int[][] seatMID2 = { { -1, 1030, 1030, -1 }, // RESERVATION NUMBER
                             { -1, 1, 1, -1 },
                             { 2, 2, 2, 2 },
                             { 2, 2, 2, 2 } };

        int[][] seatscust2 = { { -1, 100, 100, -1 }, // customer number
                               { -1, 0, 0, -1 },
                               { 0, 0, 0, 0 },
                               { 0, 0, 0, 0 } };

        f1.setPmap(seatMString2);
        f1.setIdmap(seatMID2);
        f1.setCustidmap(seatscust2);
        res.add(r1);

        Reservation r2 = new Reservation(2, seatss, 1700, c4, f4, 1040);
        String[][] seatMString3 = { { "NA", "NA", "NA", "NA" },
                                    { "NA", "2A", "2B", "NA" },
                                    { "3A", "3B", "3C", "3D", },
                                    { "4A", "4B", "4C", "4D", } };

        int[][] seatMID3 = { { -1, 1040, 1040, -1 }, // RESERVATION NUMBER
                             { -1, 1, 1, -1 },
                             { 2, 2, 2, 2 },
                             { 2, 2, 2, 2 } };

        int[][] seatscust3 = { { -1, 130, 130, -1 }, // customer number
                               { -1, 0, 0, -1 },
                               { 0, 0, 0, 0 },
                               { 0, 0, 0, 0 } };

        f4.setPmap(seatMString3);
        f4.setIdmap(seatMID3);
        f4.setCustidmap(seatscust3);
        res.add(r2);

        Reservation r3 = new Reservation(2, seatss, 1700, c5, f5, 1050);
        String[][] seatMString4 = { { "NA", "NA", "NA", "NA" },
                                    { "NA", "2A", "2B", "NA" },
                                    { "3A", "3B", "3C", "3D", },
                                    { "4A", "4B", "4C", "4D", } };

        int[][] seatMID4 = { { -1, 1050, 1050, -1 }, // RESERVATION NUMBER
                             { -1, 1, 1, -1 },
                             { 2, 2, 2, 2 },
                             { 2, 2, 2, 2 } };

        int[][] seatscust4 = { { -1, 140, 140, -1 }, // customer number
                               { -1, 0, 0, -1 },
                               { 0, 0, 0, 0 },
                               { 0, 0, 0, 0 } };

        f5.setPmap(seatMString4);
        f5.setIdmap(seatMID4);
        f5.setCustidmap(seatscust4);
        res.add(r3);

        Reservation cr1 = new Reservation(2, seatss, 1700, c5, f6, 1000);
        Reservation cr2 = new Reservation(2, seatss, 1700, c6, f7, 1010);
        Reservation cr3 = new Reservation(2, seatss, 1700, c8, f8, 1020);
        resCanceled.add(cr3);
        resCanceled.add(cr2);
        resCanceled.add(cr1);

        int choice = 0;
        while (choice != 12) {
            choice = menu();
            if (choice == 1) {
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
                AirlineDriver.printGrossIncome(flight);
            } else if (choice == 9) {
                AirlineDriver.searchReservation(res);
            } else if (choice == 10) {
                AirlineDriver.searchDeleted(resCanceled);
            } else if (choice == 11) {
                AirlineDriver.addCustomer(cust);
            } else if (choice == 12) {
                AirlineDriver.closeConnection();
                System.out.println("Thank you for choosing Java Airlines!\nHave a good day :).");
                System.exit(0);
            }
        }

    }

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nMenu:");
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
