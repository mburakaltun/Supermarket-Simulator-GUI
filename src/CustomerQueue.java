/*THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY  PROGRAM WRITTEN BY OTHER STUDENTS or 
  DID NOT COPY ANY PROGRAM FROM OTHER SOURCES. 
  I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING ASSIGNMENT.
  NAME: Muhammed Burak Altun*/

import java.util.LinkedList;
import java.util.Queue;

public class CustomerQueue {
	public Queue<Customer> queue = new LinkedList<>();

	public CustomerQueue() {
		
	}

	public void addCustomer(Customer c) {
		queue.add(c);
	}

	public void removeCustomer() {
		if (!queue.isEmpty()) {
			queue.remove();
		}
	}

	public int size() {
		return queue.size();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public void removeAll() {
		queue.removeAll(queue);
	}
}
