import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class removePatientGUI extends JFrame implements ActionListener{
	JPanel panel[] = new JPanel[3]; 
	JTextField adminInput = new JTextField(20);
	JTextArea instructions = new JTextArea();
	public removePatientGUI(String title, int height, int width) {
		setTitle(title);
		setSize(height, width);
		setLocation(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(3, 1));
		
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			contentPane.add(panel[i]);
		}
		instructions.append("Enter the patientID of the patient to be removed");
		panel[0].add(instructions);
		panel[1].add(adminInput);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panel[2].add(ok);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent event) {
		String  buttonName;
		buttonName = event.getActionCommand();
		if (buttonName.equals("OK")) {      
			//need to take in the patient database through the constructor to add this functionality
			if (adminInput.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter all fields before performing deletion.");
			}
			else if (adminInput.getText().isEmpty() != true) {
				try {
    				int patientIDInput1 = Integer.parseInt(adminInput.getText());
    				if (false) {
    					JOptionPane.showMessageDialog(null, "Patient"+"ID#"+"successfully deleted");
    				}  
    				else if (false) { 
    					JOptionPane.showMessageDialog(null, "Patient ID specified does not exist");
    				}
    			}
				catch (NumberFormatException e) {
	    			JOptionPane.showMessageDialog(null, "Not a valid patient ID, please enter an integer.");
	    		}
			}
		}
	}
}
