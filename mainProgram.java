import java.io.*;
import java.util.*;
public class mainProgram {
	public static void main(String[] args) throws IOException{
		ArrayList<patient> patientStorage1 = new ArrayList<patient>();
		try {
	    	File patientFile = new File("Sleep_health_and_lifestyle_dataset.csv");
	    	Scanner csvReader = new Scanner(patientFile);
	    	
	    	while (csvReader.hasNextLine()) {
	    		int dataToEnterSlotNumber = 0;
	    		String[] dataToEnter = new String[13];
	    		String data = csvReader.nextLine();
	    		StringTokenizer tokenizer = new StringTokenizer(data, ",/");
	    		
	            while (tokenizer.hasMoreTokens()) {
	                dataToEnter[dataToEnterSlotNumber] = tokenizer.nextToken();
	                if (dataToEnterSlotNumber == (dataToEnter.length - 1)) {
	                	patient temporary = new patient(dataToEnter);
	                	if (temporary.checkData() < 2) {
	                		patientStorage1.add(temporary);
	                	}
	                }
	                dataToEnterSlotNumber++;
	            }
	    	}
	    	csvReader.close();
	    }
	    catch (FileNotFoundException e) {
		    System.out.println("CSV File could not be found.");
	    }
		patientForm sleepForm = new patientForm(patientStorage1, 100);
		new mainGUI("Sleep Disorder Detector", 600, 600, sleepForm);
	}
}
