import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class surveyGUI extends JFrame implements ActionListener{
	JPanel panel[] = new JPanel[24]; 
	JTextField patientInput[] = new JTextField[11];
	JTextArea patientAttribute[] = new JTextArea[13];
	String[] instructions = new String[13];

	public surveyGUI(String title, int height, int width) {
		setTitle(title);
		setSize(height, width);
		setLocation(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(24, 1));
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			contentPane.add(panel[i]);
		}
			
		instructions[0]= "Gender";
		instructions[1]= "Age";
		instructions[2]= "Occupation";
		instructions[3]= "Sleep Duration";
		instructions[4]= "Quality of sleep";
		instructions[5]= "Physical activity (minutes per day)";
		instructions[6]= "Stress level on a scale of 1-10";
		instructions[7]= "BMI estimation (normal, overweight, obese)";
		instructions[8]= "Blood Pressure";
		instructions[9]= "Resting Heart Rate";
		instructions[10]= "Daily Steps";
		instructions[11]= "Click on the OK button once all questions have been answered.";
		
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
		String buttonName;
	    	buttonName = event.getActionCommand();
	    	if (buttonName.equals("OK")) {      
	    		boolean formFilled = true;
	    		String dataToSend[] = new String[11];
	    		for (int i = 0; i < patientInput.length; i++) {
	    			if (patientInput[i].getText().isEmpty()) {
	    				formFilled = false;
	    			}
	    		}
	    		if (formFilled == true) {
	    			for (int i = 0; i < patientInput.length; i++) { 
					dataToSend[i] = patientInput[i].getText();
				}
	    			this.dispose();
	    			//functionality goes here (the code to enter patient info so that the random forest can make a prediction)
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(null, "Please fill in all empty fields before submitting.");
	    		}
	    	} 
		}
	}
