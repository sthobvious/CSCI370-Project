import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class mainGUI extends JFrame implements ActionListener{
	JPanel panel[] = new JPanel[6]; 
	JButton options[] = new JButton[6];
	Container contentPane = this.getContentPane();
	public mainGUI(String title, int height, int width) {
		//make the actual jframe
		setTitle(title);
		setSize(height, width);
		setLocation(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane.setLayout(new GridLayout(6, 1));
		
		//main menu buttons, first 2 buttons should be visible other 4 no. 
		options[0] = new JButton("Patient Sleep Disorder Survey");
		options[1] = new JButton("Edit Patient Database");
		options[2] = new JButton("Remove Patient");
		options[3] = new JButton("Add Patient");
		options[4] = new JButton("Edit Existing Patient");
		options[5] = new JButton("Return to Main Menu");
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			contentPane.add(panel[i]);
			panel[i].add(options[i]);
			options[i].addActionListener(this);
		}
		
		setVisible(true);
		options[2].setVisible(false);
		options[3].setVisible(false);
		options[4].setVisible(false);
		options[5].setVisible(false);
		
	}
	
	public void actionPerformed(ActionEvent event){
		String  buttonName;
    	buttonName = event.getActionCommand();
    	if (buttonName.equals("Patient Sleep Disorder Survey")) {      
    		new surveyGUI("Patient Survey", 600, 850);
    	} 
    	else if (buttonName.equals("Edit Patient Database")) {      
    		options[0].setVisible(false);
    		options[1].setVisible(false);
    		options[2].setVisible(true);
    		options[3].setVisible(true);
    		options[4].setVisible(true);
    		options[5].setVisible(true);
    		
    	} 
    	else if (buttonName.equals("Add Patient")) {      
    		new addPatientGUI("Patient Survey", 600, 930);
    	} 
    	else if (buttonName.equals("Remove Patient")) {      
    		new removePatientGUI("Remove Patient", 600, 300);
    	} 
    	else if (buttonName.equals("Edit Existing Patient")) {      
    		new editPatientGUI("Edit Existing Patient", 600, 980);
    	} 
    	else if (buttonName.equals("Return to Main Menu")) {      
    		options[0].setVisible(true);
    		options[1].setVisible(true);
    		options[2].setVisible(false);
    		options[3].setVisible(false);
    		options[4].setVisible(false);
    		options[5].setVisible(false);
    	} 
	}
}
