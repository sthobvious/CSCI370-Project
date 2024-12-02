import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class mainGUI extends JFrame implements ActionListener{
	
	public mainFrame(String title, int height, int width) {
		//make the actual jframe
		setTitle(title);
		setSize(height, width);
		setLocation(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout());
		//and relevant GUI components
		JPanel panel[] = new JPanel[2]; 
		JButton options[] = new JButton[2];

		
		//buttons for admin vs user
		panel[0] = new JPanel();
		panel[1] = new JPanel();
		options[0] = new JButton("Patient Sleep Disorder Survey");
		options[1] = new JButton("Edit Patient Database");
		contentPane.add(panel[0]);
		contentPane.add(panel[1]);
		panel[0].add(options[0]);
		panel[1].add(options[1]);
		options[0].addActionListener(this);
		options[1].addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent event){
		String  buttonName;
    	buttonName = event.getActionCommand();
    	if (buttonName.equals("Patient")) {      
    		//code to change 
    		JOptionPane.showMessageDialog(null, "Test 1!");
    	} 
    	else if (buttonName.equals("Admin")) {      
    		JOptionPane.showMessageDialog(null, "Test 2!");
    	} 
	}
}
