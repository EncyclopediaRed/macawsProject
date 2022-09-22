package macawsProject;

/**
 * Customer Class
 *
 * @authors R. Barrowclift, C. Hogg, M. Porter - ITP 220
 * @version Sep 22, 2022
 *
 */
public class Customer {
	
	// Create the instance variables for a customer.
	private int custNum;
	private String firstName;
	private String lastName;
	private String email;
	
	// Create a static variable for unique customer ID.
	public static int num = 100;
	
	// Create a no argument constructor that generates a customer ID.
	public Customer() {
		
		// Assign the static variable for a customer ID.
		custNum = num;
		num += 10;
	} // End of no argument constructor.
	
	// Create a full argument constructor.
	public Customer(String fName, String lName, String e) {
		
		// Assign each instance variable for a customer.
		fName = firstName;
		lName = lastName;
		e = email;
		custNum = num;
		num += 10;			
	} // End of full argument constructor.
	
	// Create a toString method for displaying customer information.
	public String toString() {
		
		// Create the customer information to be displayed.
		return "Customer Name: " + firstName + " " + lastName + "\nEmail: " + email
				+ "\nID Number: " + custNum;		
	} // End of method toString.
	
	// Generate getters and setters for each of the instance variables.
	public int getCustNum() {
		return custNum;
	}

	public void setCustNum(int custNum) {
		this.custNum = custNum;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

} // End of Class Customer.
