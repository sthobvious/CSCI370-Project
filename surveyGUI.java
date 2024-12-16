import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
public class surveyGUI extends JFrame implements ActionListener{
	JPanel panel[] = new JPanel[22]; 
	JTextField patientInput[] = new JTextField[10];
	JTextArea patientAttribute[] = new JTextArea[12];
	String[] instructions = new String[11];
	patientForm sleepForm3;
	public surveyGUI(String title, int height, int width, patientForm sleepForm2) {
		sleepForm3 = sleepForm2;
		setTitle(title);
		setSize(height, width);
		setLocation(600, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(22, 1));
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			contentPane.add(panel[i]);
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
		instructions[10]= "Click on the OK button once all questions have been answered.";
		
		for (int i = 0; i < patientInput.length; i++) {
			patientInput[i] = new JTextField(20);
		}
		
		int j = 0; 
		int k = 0;
		for (int i = 0; i < panel.length; i++) {
			if (i % 2 == 0) {
				patientAttribute[j] = new JTextArea();
				patientAttribute[j].append(instructions[j]);
				patientAttribute[j].setEditable(false);
				panel[i].add(patientAttribute[j]);
				j++;
			}
			else if (i < 20){
				panel[i].add(patientInput[k]);
				k++;
			}
		}
		
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panel[21].add(ok);
		setVisible(true);
	}    
		public void actionPerformed(ActionEvent event){
			String buttonName;
	    	buttonName = event.getActionCommand();
	    	if (buttonName.equals("OK")) {      
	    		boolean formFilled = true;
	    		String dataToSend[] = new String[13];
	    		dataToSend[0] = "-1";
	    		for (int i = 0; i < patientInput.length; i++) {
	    			if (patientInput[i].getText().isEmpty()) {
	    				formFilled = false;
	    			}
	    		}
	    		if (formFilled == true) {
	    			for (int i = 0; i < 7; i++) { 
						dataToSend[i+1] = patientInput[i].getText();
					}
	 
	    			String oldBloodPressure = patientInput[7].getText().toString();
	    			String[] newBloodPressure = oldBloodPressure.split("/");
		    		dataToSend[8] = newBloodPressure[0];
		    		dataToSend[9] = newBloodPressure[1];
		    		for (int i = 10; i < 12; i++) { 
						dataToSend[i] = patientInput[i-2].getText();
					}
		    		dataToSend[12] = "999999";
		    		for (int i = 0; i < dataToSend.length; i++) { 
		    			System.out.println(dataToSend[i]);
					}
		    		dataToSend[12] = "999999";
	    			patient pete = new patient(dataToSend);
	    			ArrayList<patient> patientStorage = new ArrayList<patient>();
	    			patientStorage.add(pete);
	    			patientForm b = new patientForm(patientStorage, 100); 
	    			//functionality goes here (the code to enter patient info so that the random forest can make a prediction)
	    			
	    			//open GUI to display results to user.
	    			
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(null, "Please fill in all empty fields before submitting.");
	    		}
	    	} 
		}
	}
