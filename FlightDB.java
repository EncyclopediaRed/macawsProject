package macawsProject;

/**
 * Flight Class
 * 
 * Creates a flight class to detail what makes up a flight: Flight route designation Flight number
 * Departure date Departure time Pilot
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 *
 */

public class FlightDB {

    // Create the instance variables for a flight.
    private String route;
    private String date;
    private String time;
    private int flightNum;
    private String[][] pmap;// to print out open seats
    private int[][] idmap;// to allow us to resort back if reservation is canceled
    private int[][] custidmap; // so we can see who had a reservation
    private double profit;
    Pilot p;

    // Create static a variable for a unique flight ID.
    public static int num = 500;
    public static double num2 = 0;

    // Create a no argument constructor that generates a unique flight ID.
    public FlightDB() {

        // Assign the static variable.
        flightNum = num;
        num += 10;
    } // End of no argument constructor.

    // Create a full argument constructor for a flight.
    public FlightDB(String r, String string, String string2, String[][] m, int[][] j, int[][] f,
        Pilot p) {

        // Assign each instance variable for a flight.
        route = r;
        date = string;
        time = string2;
        flightNum = num;
        num += 10;
        pmap = m;
        idmap = j;
        custidmap = f;
        this.p = p;
        profit = num2;
    } // End of full argument constructor.

    // Create a toString method for displaying flight information and pilot.
    public String toString() {

        // Create the flight information to be displayed.
        return ""
            + "\nRoute: " + route
            + "\nDate: " + date
            + "\nTime: " + time
            + "\nFlight ID Number: " + flightNum
            + "\n" + p.toString();
    } // End of method toString.

    // Create the getters and setters for each instance variable.
    public String toMString() {
        String javaisfun = "";
        System.out.println();
        System.out.println("Airplane Seat Map");
        System.out.println("=========================");
        for (int i = 0; i < pmap.length; i++) {
            for (int j = 0; j < pmap[i].length; j++) {
                System.out.print("| " + pmap[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println("\nAirplane Seat Map By Customer Number");
        System.out.println("=========================");
        for (int i = 0; i < custidmap.length; i++) {
            for (int j = 0; j < custidmap[i].length; j++) {
                System.out.print("| " + custidmap[i][j] + " |");
            }
            System.out.println();
        }
        return javaisfun;
    }

    public String toMPString() {
        String javaisfun = "";
        System.out.println("Airplane Seat Map");
        System.out.println("=========================");
        for (int i = 0; i < pmap.length; i++) {
            for (int j = 0; j < pmap[i].length; j++) {
                System.out.print("| " + pmap[i][j] + " |");
            }
            System.out.println();
        }
        return javaisfun;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String[][] getPmap() {
        return pmap;
    }

    public void setPmap(String[][] pmap) {
        this.pmap = pmap;
    }

    public int[][] getIdmap() {
        return idmap;
    }

    public void setIdmap(int[][] idmap) {
        this.idmap = idmap;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public Pilot getP() {
        return p;
    }

    public void setP(Pilot p) {
        this.p = p;
    }

    public int[][] getCustidmap() {
        return custidmap;
    }

    public void setCustidmap(int[][] custidmap) {
        this.custidmap = custidmap;
    }

} // End of Class Flight.