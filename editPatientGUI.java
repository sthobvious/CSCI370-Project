import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
public class editPatientGUI extends JFrame implements ActionListener{
	JPanel panel[] = new JPanel[26]; 
	JTextField adminInput[] = new JTextField[12];
	JTextArea adminInstructions[] = new JTextArea[14];
	String[] instructions = new String[13];
	patientForm sleepForm3;
	
	public editPatientGUI(String title, int height, int width, patientForm sleepForm2) {
		sleepForm3 = sleepForm2;
		setTitle(title);
		setSize(height, width);
		setLocation(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(26, 1));
		//instructions for the JTextAreas
		instructions[0] = "Enter the ID of the patient to be edited.";
		instructions[1] = "Gender";
		instructions[2] = "Age";
		instructions[3] = "Sleep Duration in hours";
		instructions[4] = "Quality of sleep on a scale of 1-10";
		instructions[5] = "Physical activity (minutes per day)";
		instructions[6] = "Stress level on a scale of 1-10";
		instructions[7] = "BMI estimation (normal, overweight, obese)";
		instructions[8] = "Blood Pressure";
		instructions[9] = "Resting Heart Rate";
		instructions[10] = "Daily Steps";
		instructions[11] = "Presence of sleep disorder (Y/N)";
		instructions[12] = "Click on the Complete edit button once all patient data is ready to be entered into the database.";
		//put panels onto the frame
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			contentPane.add(panel[i]);
		}
		//put fields & inputs onto each individual panel for panels that are = to or greater than 3
		for (int i = 0; i < 12; i++) {
			adminInstructions[i] = new JTextArea();
			adminInstructions[i].append(instructions[i]);
			adminInstructions[i].setEditable(false);
			adminInput[i] = new JTextField(20);
			panel[(i * 2) + 1].add(adminInstructions[i]);
			panel[(i * 2) + 2].add(adminInput[i]);
		}
		//code for buttons
		JButton search = new JButton("Search");
		search.addActionListener(this);
		JButton finish = new JButton("Complete edit");
		finish.addActionListener(this);
		panel[0].add(adminInstructions[0]);
		panel[1].add(adminInput[0]);
		panel[2].add(search);			
		panel[25].add(finish);
		setVisible(true);
		for (int i = 3; i < (panel.length - 1); i++) { 
			panel[i].setVisible(false); 
		}
	}
	public void actionPerformed(ActionEvent event) {
		String buttonName;
		buttonName = event.getActionCommand();
		//edit button functionality
		if (buttonName.equals("Complete edit")) {      
			//check each field to see if its empty. Bool is a temp value meant to be a flag.
			String[] dataToSend = new String[12];
			boolean formFilled = true;
    		for (int i = 1; i < adminInput.length; i++) {
    			if (adminInput[i].getText().isEmpty()) {
    				formFilled = false;
    			}
    		}
    		
    		//if any fields are empty give a pop up
			if(formFilled == false) {
				JOptionPane.showMessageDialog(null, "Please fill in all empty fields before editing.");
			}
			//if everything is filled, return to search mode
			else if (formFilled == true) {
				//all input fields go to a string array
				for (int i = 1; i < adminInput.length; i++) { 
					dataToSend[i-1] = adminInput[i].getText();
				}
				//panels back to search mode
				for (int i = 3; i < panel.length; i++) { 
					panel[i].setVisible(false); 
				}
				panel[0].setVisible(true);
				panel[1].setVisible(true);
				panel[2].setVisible(true);	
			}
    	} 	
		
		//search button functionality
		else if (buttonName.equals("Search")) {      
			//check for empty search first
			if (adminInput[0].getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all empty fields before searching.");
			}
			//if its not empty check if the entered ID exists in the database, and if it isnt, give popup window. Implementation for this needed.
			//statement below temporary, trycatch needed to check if input is integer
			else if (adminInput[0].getText().isEmpty() != true) {
			//if the patient exists, swap to edit mode and hide/reveal appropriate panels to reveal buttons/field, and enter edit mode.
				try {
					int patientIDInput1 = Integer.parseInt(adminInput[0].getText());
					if (true) {
						for (int i = 3; i < (panel.length); i++) { 
							panel[i].setVisible(true); 
						}
						panel[0].setVisible(false);
						panel[1].setVisible(false);
						panel[2].setVisible(false);	
						JOptionPane.showMessageDialog(null, "Patient successfully found.");
					}
					//if patientID doesnt exist, notify the admin
					else if (false) {
						JOptionPane.showMessageDialog(null, "The patient specififed does not exist.");
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Not a valid patient ID, please enter an integer.");
				}
			}
    	} 
	}
}
