import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class patientForm {
    private ArrayList<patient> allPatients = new ArrayList<>(); // list of all archived patients
    private ArrayList<patient> trainingPatients = new ArrayList<>(); // list of all patients that will be used for training
    private ArrayList<patient> testPatients = new ArrayList<>(); // list of all patients that will be used for testing

    //only one accessible/ we are working with
    public ArrayList<patient> bootstrappedData = new ArrayList<>(); // list of all patients that are bootstrapped

    private int numOfPatients;
    private int numOfFeatures;

    public patientForm(ArrayList<patient> ap, int ss) {//inital call so we can bootstrap
        this.allPatients = ap;
        updateSplits();
        bootstrapDataset(ss);
        updateStats();
    } //constructor

    public patientForm(ArrayList<patient> ap) {//calls for making left and right subtrees, where we dont need to call bootstrap again
        bootstrappedData = ap;
        updateStats();
    } //constructor



    public void updateStats() {
        this.numOfPatients = bootstrappedData.size();
        this.numOfFeatures = bootstrappedData.get(0).getFeatures().length;
    }

    public int getNumOfPatients() {
        return numOfPatients;
    }
    public int getNumOfFeatures() {
        return numOfFeatures;
    }

    public ArrayList<Integer> getLabels(){
        ArrayList<Integer> label = new ArrayList<Integer>();
        for(patient patient: bootstrappedData){
            label.add(patient.getLabel());
        }

        return label;
    }

    public ArrayList<patient> getPatients(){
        return bootstrappedData;
    }
    public void printDS(String type) {// to print out each diff dataset
        if (type == "ap") {
            for (int i = 0; i < allPatients.size(); i++) {
                System.out.println(allPatients.get(i).getFeatures());
            }
        }
        if (type == "trainp") {
            for (int i = 0; i < trainingPatients.size(); i++) {
                System.out.println(trainingPatients.get(i).getFeatures());
            }
        }
        if (type == "testp") {
            for (int i = 0; i < testPatients.size(); i++) {
                System.out.println(testPatients.get(i).getFeatures());
            }
        } 
        else if (type == "bd") {
            for (int i = 0; i < bootstrappedData.size(); i++) {
                System.out.println(bootstrappedData.get(i).getFeatures());
            }
        }

    }

    public int size = bootstrappedData.size();

    public void updateSize() {
        size = allPatients.size();
    }
    public int getSize() {
        return bootstrappedData.size();
    }

    public void updateSplits() {
        updateSize();
        int limit = size * 70 / 100;

        int counter = 0;
        while (counter < limit) {
            trainingPatients.add(allPatients.get(counter));
            counter++;
        }

        for (; counter < size; counter++) {
            testPatients.add(allPatients.get(counter));
        }

        System.out.println("the splits have been updated!");
    }

    public void bootstrapDataset(int sampleSize) {
        int sizeT = trainingPatients.size();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < sampleSize; i++) {
            int tempKey = random.nextInt(0, sizeT);
            bootstrappedData.add(trainingPatients.get(tempKey));
        }

        System.out.println("bootstrapping is complete, print list to double check!");
    }

    public Set<Double> getAllThresh(int featNum, patientForm dataset) { // check
        Set<Double> val = new HashSet<>();

        for (int i = 0; i < dataset.getSize(); i++) {
            patient patient = dataset.bootstrappedData.get(i);
            val.add(patient.getspecificFeature(featNum));
        }

        return val;
    }

}
