import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
public class addPatientGUI extends JFrame implements ActionListener{			
	JPanel panel[] = new JPanel[24]; 
	JTextField patientInput[] = new JTextField[11];
	JTextArea patientAttribute[] = new JTextArea[13];
	String[] instructions = new String[13];
	patientForm sleepForm3;
	public addPatientGUI(String title, int height, int width, patientForm sleepForm2) {
		sleepForm3 = sleepForm2;
		setTitle(title);
		setSize(height, width);
		setLocation(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(24, 1));
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
		}
		for (int i = 0; i < patientInput.length; i++) {
			patientInput[i] = new JTextField(20);
		}		
		instructions[0]= "Gender";
		instructions[1]= "Age";
		instructions[2]= "Sleep Duration in hours";
		instructions[3]= "Quality of sleep on a scale of 1-10";
		instructions[4]= "Physical activity (minutes per day)";
		instructions[5]= "Stress level on a scale of 1-10";
		instructions[6]= "BMI estimation (normal, overweight, obese)";
		instructions[7]= "Blood Pressure";
		instructions[8]= "Resting Heart Rate";
		instructions[9]= "Daily Steps";
		instructions[10]= "Presence of sleep disorder (Y/N)";
		instructions[11]= "Click on the OK button once all patient data is ready to be entered into the database.";
		
		int j = 0; 
		int k = 0;
		for (int i = 0; i < (panel.length); i++) {
			contentPane.add(panel[i]);
			if (i % 2 == 0) {
				patientAttribute[j] = new JTextArea();
				patientAttribute[j].append(instructions[j]);
				patientAttribute[j].setEditable(false);
				panel[i].add(patientAttribute[j]);
				j++;
			}
			else if (i < 22){
				panel[i].add(patientInput[k]);
				k++;
			}
		}
		
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panel[23].add(ok);
		setVisible(true);
	}    
	public void actionPerformed(ActionEvent event){
		String  buttonName;
		buttonName = event.getActionCommand();

		if (buttonName.equals("OK")) {      
			String dataToSend[] = new String[12];
    		boolean formFilled = true;
    		for (int i = 0; i < patientInput.length; i++) {
    			if (patientInput[i].getText().isEmpty()) {
    				formFilled = false;
    			}
    		}
    		if (formFilled == true) {
    			for (int i = 0; i < patientInput.length; i++) { 
					dataToSend[i] = patientInput[i].getText();
				}
    			//functionality goes here (the code to enter patient info so that they can be added to the database used to make predictions)
    			JOptionPane.showMessageDialog(null, "Patient succesfully added to database");
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "Please fill in all empty fields before submitting.");
    		}
    	} 
	}
}
