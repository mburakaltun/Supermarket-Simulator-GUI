/*THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY  PROGRAM WRITTEN BY OTHER STUDENTS or 
  DID NOT COPY ANY PROGRAM FROM OTHER SOURCES. 
  I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING ASSIGNMENT.
  NAME: Muhammed Burak Altun*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationGUI {
	Timer timer;
	int current_time = 0;
	JFrame frame = new JFrame("Simulation HW2");
	SimulationStatistics sim = new SimulationStatistics(1, 1, 1);

	// panels
	JPanel variablesPanel = new JPanel();
	JPanel simulationPanel = new JPanel();
	JPanel simulationStatisticsPanel = new JPanel();

	// Variable Panel
	JLabel maxSimulatonTimeLabel = new JLabel("Max Simulation Time");
	JLabel maxArrivalTimeLabel = new JLabel("Max Arrival Time");
	JLabel maxServiceTimeLabel = new JLabel("Max Service Time");
	JButton startButton = new JButton("Start");
	JTextField simulationTimeTF = new JTextField();
	JTextField maxArrivalTimeTF = new JTextField();
	JTextField maxServiceTimeTF = new JTextField();
	JButton stopButton = new JButton("Stop");

	// Simulation Label
	JLabel currentSimulationTimeLabel = new JLabel("Current Simulation Time: 0");
	JLabel currentQueueLengthLabel = new JLabel("Current Queue Length: 0");
	JLabel totalServiceTimeLabel = new JLabel("Total Service Time: 0");

	// Simulation Statistics
	JLabel numberOfCustomersLabel = new JLabel("");
	JLabel averageWaitTimeLabel = new JLabel("");
	JLabel averageServiceTimeLabel = new JLabel("");
	JLabel maximumWaitTimeLabel = new JLabel("");
	JLabel maximumQueueLength = new JLabel("");

	public SimulationGUI() {
		frame.setVisible(true);
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		variablesPanel.setBorder(BorderFactory.createTitledBorder("Variables Panel"));
		simulationPanel.setBorder(BorderFactory.createTitledBorder("Simulation Panel"));
		simulationStatisticsPanel.setBorder(BorderFactory.createTitledBorder("Simulation Statistics"));

		variablesPanel.setLayout(new GridLayout(4, 2));
		variablesPanel.add(maxSimulatonTimeLabel);
		variablesPanel.add(simulationTimeTF);
		variablesPanel.add(maxArrivalTimeLabel);
		variablesPanel.add(maxArrivalTimeTF);
		variablesPanel.add(maxServiceTimeLabel);
		variablesPanel.add(maxServiceTimeTF);
		variablesPanel.add(startButton);
		variablesPanel.add(stopButton);

		simulationPanel.setLayout(new GridLayout(3, 1));
		simulationPanel.add(currentSimulationTimeLabel);
		simulationPanel.add(currentQueueLengthLabel);
		simulationPanel.add(totalServiceTimeLabel);

		simulationStatisticsPanel.setLayout(new GridLayout(5, 1));
		simulationStatisticsPanel.add(numberOfCustomersLabel);
		simulationStatisticsPanel.add(averageWaitTimeLabel);
		simulationStatisticsPanel.add(averageServiceTimeLabel);
		simulationStatisticsPanel.add(maximumWaitTimeLabel);
		simulationStatisticsPanel.add(maximumQueueLength);

		frame.setLayout(new GridLayout(3, 1));
		frame.add(variablesPanel);
		frame.add(simulationPanel);
		frame.add(simulationStatisticsPanel);
		frame.setVisible(true);

		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current_time += 1;
				if (current_time == 1) {
					String simulationTimeString = simulationTimeTF.getText();
					String maxArrivalTimeString = maxArrivalTimeTF.getText();
					String maxServiceTimeString = maxServiceTimeTF.getText();

					int simulationTime = Integer.parseInt(simulationTimeString);
					int maxArrivalTime = Integer.parseInt(maxArrivalTimeString);
					int maxServiceTime = Integer.parseInt(maxServiceTimeString);

					SimulationStatistics stat = new SimulationStatistics(simulationTime, maxArrivalTime,
							maxServiceTime);
					sim = stat;

					sim.simulate(current_time);
					currentSimulationTimeLabel.setText("Current Simulation Time: " + current_time);
					currentQueueLengthLabel.setText("Current Queue Length: " + sim.getQueue().size());
					totalServiceTimeLabel.setText("Total Service Time: " + sim.getTotalServiceTime());
				} else if (current_time <= sim.getSimulationTime()) {
					sim.simulate(current_time);
					currentSimulationTimeLabel.setText("Current Simulation Time: " + current_time);
					currentQueueLengthLabel.setText("Current Queue Length: " + sim.getQueue().size());
					totalServiceTimeLabel.setText("Total Service Time: " + sim.getTotalServiceTime());
				} else if (current_time > sim.getSimulationTime()) {
					numberOfCustomersLabel.setText("Number of Customer: " + sim.totalCustomer());
					averageWaitTimeLabel.setText("Average Wait Time: " + sim.averageWaitingTime());
					averageServiceTimeLabel.setText("Average Service Time: " + sim.averageServiceTime());
					maximumWaitTimeLabel.setText("Maximum Wait Time: " + sim.maximumWaitingTime());
					maximumQueueLength.setText("Maximum Queue Length: " + sim.maxQueueLength());
					timer.stop();
				}
			}
		};

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current_time >= sim.getSimulationTime()) {
					current_time = 0;
					numberOfCustomersLabel.setText("");
					averageWaitTimeLabel.setText("");
					averageServiceTimeLabel.setText("");
					maximumWaitTimeLabel.setText("");
					maximumQueueLength.setText("");
					sim.setWaitsZero();
				}
				timer.start();
			}
		});

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});

		timer = new Timer(100, task);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SimulationGUI();
	}
}
