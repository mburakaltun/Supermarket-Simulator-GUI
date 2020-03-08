/*THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY  PROGRAM WRITTEN BY OTHER STUDENTS or 
  DID NOT COPY ANY PROGRAM FROM OTHER SOURCES. 
  I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING ASSIGNMENT.
  NAME: Muhammed Burak Altun*/

public class Customer {
	private static int customerIDnumber = 1000;
	private static int customerNumber = 0;
	private int customerID;
	private int arrivalTime;

	public Customer(int arrivalTime) {
		this.arrivalTime = arrivalTime;
		customerID = customerIDnumber;
		customerNumber += 1;
		customerIDnumber += 1;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public int customerNumber() {
		return customerNumber;
	}
}
