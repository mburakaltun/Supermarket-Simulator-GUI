/*THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY  PROGRAM WRITTEN BY OTHER STUDENTS or 
  DID NOT COPY ANY PROGRAM FROM OTHER SOURCES. 
  I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING ASSIGNMENT.
  NAME: Muhammed Burak Altun*/

import java.util.LinkedList;
import java.util.Random;

public class SimulationStatistics {
	private CustomerQueue cq = new CustomerQueue();
	private int simulationTime = 0;
	private int max_arrival_time = 0;
	private int max_service_time = 0;

	private int total_service_time = 0;
	private int max_line = 0;
	private int total_customer = 0;
	private int serviced_number = 0;
	private int arrival_time = 0;
	private int total_waiting_time = 0;
	private int max_waiting_time = 0;

	private int service_time = 0;
	private int new_customer_arrival_time = 0;
	private int current_service_finish_time = 0;

	private LinkedList<Integer> WaitingTimes = new LinkedList<>();

	public SimulationStatistics(int simulationTime, int max_arrival_time, int max_service_time) {
		super();
		this.simulationTime = simulationTime;
		this.max_arrival_time = max_arrival_time;
		this.max_service_time = max_service_time;

		Random rand = new Random();
		int rand_arrival_time = rand.nextInt(max_arrival_time);
		int rand_serivce_time = rand.nextInt(max_service_time);
		new_customer_arrival_time = rand_arrival_time + 1;
		service_time = rand_serivce_time + 1;
		current_service_finish_time = service_time + new_customer_arrival_time;

		total_service_time = 0;
		arrival_time = 0;
		total_waiting_time = 0;
		max_waiting_time = 0;
		max_line = 0;
		total_customer = 0;
		serviced_number = 0;
		cq.removeAll();
	}

	public void simulate(int time) {
		Random rand = new Random();
		if (new_customer_arrival_time == time) {
			Customer c = new Customer(new_customer_arrival_time);
			cq.addCustomer(c);
			WaitingTimes.add(0);

			int rand_arrival_time = rand.nextInt(max_arrival_time);

			arrival_time = rand_arrival_time + 1;

			new_customer_arrival_time = arrival_time + time;
			total_customer += 1;
		}
		if (current_service_finish_time == time) {
			cq.removeCustomer();

			int rand_serivce_time = rand.nextInt(max_service_time);

			service_time = rand_serivce_time + 1;
			total_service_time += service_time;

			if (cq.isEmpty()) {
				current_service_finish_time = service_time + new_customer_arrival_time;
			} else {
				current_service_finish_time = service_time + time;
			}
			serviced_number += 1;
		}

		for (int i = serviced_number + 1; i < total_customer; i++) {
			WaitingTimes.set(i, WaitingTimes.get(i) + 1);
		}

		if (max_line < cq.size()) {
			max_line = cq.size();
		}
	}

	public int getSimulationTime() {
		return simulationTime;
	}

	public void setSimulationTime(int simulationTime) {
		this.simulationTime = simulationTime;
	}

	public int getMaxArrivalTime() {
		return max_arrival_time;
	}

	public void setMaxArrivalTime(int max_arrival_time) {
		this.max_arrival_time = max_arrival_time;
	}

	public int getMaxServiceTime() {
		return max_service_time;
	}

	public void setMaxServiceTime(int max_service_time) {
		this.max_service_time = max_service_time;
	}

	public CustomerQueue getQueue() {
		return cq;
	}

	public int maxQueueLength() {
		return max_line;
	}

	public int currentQueueLength() {
		return cq.size();
	}

	public int totalWaitingTime() {
		for (int i : WaitingTimes) {
			total_waiting_time += i;
		}
		return total_waiting_time;
	}

	public float averageWaitingTime() {
		return (float) this.totalWaitingTime() / this.totalCustomer();
	}

	public int maximumWaitingTime() {
		for (int i : WaitingTimes) {
			if (i > max_waiting_time) {
				max_waiting_time = i;
			}
		}
		return max_waiting_time;
	}

	public int getTotalServiceTime() {
		return total_service_time;
	}

	public float averageServiceTime() {
		return (float) total_service_time / serviced_number;
	}

	public int totalCustomer() {
		return total_customer;
	}

	public int servicedCustomer() {
		return serviced_number;
	}

	public void setWaitsZero() {
		for(int i=0;i<WaitingTimes.size();i++) {
			WaitingTimes.set(i, 0);
		}
	}
}
