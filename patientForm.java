import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class patientForm {
    private ArrayList<patient> allPatients = new ArrayList<>(); // list of all archived patients
    private ArrayList<patient> trainingPatients = new ArrayList<>(); // list of all patients that will be used for
                                                                     // training
    private ArrayList<patient> testPatients = new ArrayList<>(); // list of all patients that will be used for testing

    // for holding age groups
    private ArrayList<patient> M020 = new ArrayList<>();
    private ArrayList<patient> F020 = new ArrayList<>();
    private ArrayList<patient> M2040 = new ArrayList<>();
    private ArrayList<patient> F2040 = new ArrayList<>();
    private ArrayList<patient> M4060 = new ArrayList<>();
    private ArrayList<patient> F4060 = new ArrayList<>();
    private ArrayList<patient> M6080 = new ArrayList<>();
    private ArrayList<patient> F6080 = new ArrayList<>();
    private ArrayList<patient> M80100 = new ArrayList<>();
    private ArrayList<patient> F80100 = new ArrayList<>();

    // only one accessible/ we are working with
    public ArrayList<patient> bootstrappedData = new ArrayList<>(); // list of all patients that are bootstrapped

    private int numOfPatients;
    private int numOfFeatures;

    public patientForm(ArrayList<patient> ap, int ss){// inital call so we can bootstrap
        for (patient p : ap) {
            allPatients.add(p);
        }

        createLists();
        updateSplits();
        bootstrapDataset(ss);
        updateStats();
        // printDS("bd");

    } // constructor

    public void createLists() {
        ArrayList<patient> temporaryHolding4Imputation = new ArrayList<>();
        for (int i = 0; i < allPatients.size(); i++) {
            double age = allPatients.get(i).getIndex(1);
            double gender = allPatients.get(i).getIndex(0);
            // set aside all patients missing age or gender for imputation later
            if (gender == -1 || age == -1) {
                temporaryHolding4Imputation.add(allPatients.get(i));
            } // if age and gender are both present, place patients into the correct
              // arrayList.
            else if (age > 0 && age <= 20) {
                if (gender == 1) {
                    M020.add(allPatients.get(i));
                } else {
                    F020.add(allPatients.get(i));
                }
            } else if (age > 20 && age <= 40) {
                if (gender == 1) {
                    M2040.add(allPatients.get(i));
                } else {
                    F2040.add(allPatients.get(i));
                }
            } else if (age > 40 && age <= 60) {
                if (gender == 1) {
                    M4060.add(allPatients.get(i));
                } else {
                    F4060.add(allPatients.get(i));
                }
            } else if (age > 60 && age <= 80) {
                if (gender == 1) {
                    M6080.add(allPatients.get(i));
                } else {
                    F6080.add(allPatients.get(i));
                }
            } else if (age > 80 && age <= 100) {
                if (gender == 1) {
                    M80100.add(allPatients.get(i));
                } else {
                    F80100.add(allPatients.get(i));
                }
            }
            // impute the data inside of each arrayList group
            // using the fully imputed patients, take from temporaryHolding4Imputation,
            // impute age and gender, enter into correct arrayList.
        }
        Impute(temporaryHolding4Imputation);
    }

    public patientForm(ArrayList<patient> ap){// calls for making left and right subtrees, where we dont need to call
                                               // bootstrap again
        for (patient p : ap) {
            bootstrappedData.add(p);
        }

        updateStats();
    } // constructor

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

    public ArrayList<Integer> getLabels() {
        ArrayList<Integer> label = new ArrayList<Integer>();
        for (patient patient : bootstrappedData) {
            label.add(patient.getSD());
        }

        return label;
    }

    public void Impute(ArrayList<patient> tempAgeGenderGroup) {// function written by fabio
        for (int i = 0; i < M020.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (M020.get(i).getIndex(j) == -1) {
                    M020.get(i).setIndex(getAverageStats(M020, j), j);
                }
            }
        }
        for (int i = 0; i < F020.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (F020.get(i).getIndex(j) == -1) {
                    F020.get(i).setIndex(getAverageStats(F020, j), j);
                }
            }
        }
        for (int i = 0; i < M2040.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (M2040.get(i).getIndex(j) == -1) {
                    M2040.get(i).setIndex(getAverageStats(M2040, j), j);
                }
            }
        }
        for (int i = 0; i < F2040.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (F2040.get(i).getIndex(j) == -1) {
                    F2040.get(i).setIndex(getAverageStats(F2040, j), j);
                }
            }
        }
        for (int i = 0; i < M4060.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (M4060.get(i).getIndex(j) == -1) {
                    M4060.get(i).setIndex(getAverageStats(M4060, j), j);
                }
            }
        }
        for (int i = 0; i < F4060.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (F4060.get(i).getIndex(j) == -1) {
                    F4060.get(i).setIndex(getAverageStats(F4060, j), j);
                }
            }
        }
        for (int i = 0; i < M6080.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (M6080.get(i).getIndex(j) == -1) {
                    M6080.get(i).setIndex(getAverageStats(M6080, j), j);
                }
            }
        }
        for (int i = 0; i < F6080.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (F6080.get(i).getIndex(j) == -1) {
                    F6080.get(i).setIndex(getAverageStats(F6080, j), j);
                }
            }
        }
        for (int i = 0; i < M80100.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (M80100.get(i).getIndex(j) == -1) {
                    M80100.get(i).setIndex(getAverageStats(M80100, j), j);
                }
            }
        }
        for (int i = 0; i < F80100.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (F80100.get(i).getIndex(j) == -1) {
                    F80100.get(i).setIndex(getAverageStats(F80100, j), j);
                }
            }
        }
        // add in what remains in the temporary list
        for (int i = 0; i < tempAgeGenderGroup.size(); i++) {
            // impute for gender
            if (tempAgeGenderGroup.get(i).getIndex(0) == -1) {
                int Fcounter = 0;
                int Mcounter = 0;
                double age = tempAgeGenderGroup.get(i).getIndex(1);
                if (age > 0 && age <= 20) {
                    for (int j = 0; j < 10; j++) {
                        if (Math.abs(getAverageStats(M020, j) - tempAgeGenderGroup.get(i).getIndex(j)) < Math
                                .abs(getAverageStats(F020, j) - tempAgeGenderGroup.get(i).getIndex(j))) {
                            Mcounter++;
                        } else {
                            Fcounter++;
                        }
                    }
                    if (Mcounter > Fcounter) {
                        tempAgeGenderGroup.get(i).setIndex(1, 0);
                        M020.add(tempAgeGenderGroup.get(i));
                    } else {
                        tempAgeGenderGroup.get(i).setIndex(0, 0);
                        F020.add(tempAgeGenderGroup.get(i));
                    }

                } else if (age > 20 && age <= 40) {
                    for (int j = 0; j < 10; j++) {
                        if (Math.abs(getAverageStats(M2040, j) - tempAgeGenderGroup.get(i).getIndex(j)) < Math
                                .abs(getAverageStats(F2040, j) - tempAgeGenderGroup.get(i).getIndex(j))) {
                            Mcounter++;
                        } else {
                            Fcounter++;
                        }
                    }
                    if (Mcounter > Fcounter) {
                        tempAgeGenderGroup.get(i).setIndex(1, 0);
                        M2040.add(tempAgeGenderGroup.get(i));
                    } else {
                        tempAgeGenderGroup.get(i).setIndex(0, 0);
                        F2040.add(tempAgeGenderGroup.get(i));
                    }
                } else if (age > 40 && age <= 60) {
                    for (int j = 0; j < 10; j++) {
                        if (Math.abs(getAverageStats(M4060, j) - tempAgeGenderGroup.get(i).getIndex(j)) < Math
                                .abs(getAverageStats(F4060, j) - tempAgeGenderGroup.get(i).getIndex(j))) {
                            Mcounter++;
                        } else {
                            Fcounter++;
                        }
                    }
                    if (Mcounter > Fcounter) {
                        tempAgeGenderGroup.get(i).setIndex(1, 0);
                        M4060.add(tempAgeGenderGroup.get(i));
                    } else {
                        tempAgeGenderGroup.get(i).setIndex(0, 0);
                        F4060.add(tempAgeGenderGroup.get(i));
                    }
                } else if (age > 60 && age <= 80) {
                    for (int j = 0; j < 10; j++) {
                        if (Math.abs(getAverageStats(M6080, j) - tempAgeGenderGroup.get(i).getIndex(j)) < Math
                                .abs(getAverageStats(F6080, j) - tempAgeGenderGroup.get(i).getIndex(j))) {
                            Mcounter++;
                        } else {
                            Fcounter++;
                        }
                    }
                    if (Mcounter > Fcounter) {
                        tempAgeGenderGroup.get(i).setIndex(1, 0);
                        M6080.add(tempAgeGenderGroup.get(i));
                    } else {
                        tempAgeGenderGroup.get(i).setIndex(0, 0);
                        F6080.add(tempAgeGenderGroup.get(i));
                    }
                } else if (age > 80 && age <= 100) {
                    for (int j = 0; j < 10; j++) {
                        if (Math.abs(getAverageStats(M80100, j) - tempAgeGenderGroup.get(i).getIndex(j)) < Math
                                .abs(getAverageStats(F80100, j) - tempAgeGenderGroup.get(i).getIndex(j))) {
                            Mcounter++;
                        } else {
                            Fcounter++;
                        }
                    }
                    if (Mcounter > Fcounter) {
                        tempAgeGenderGroup.get(i).setIndex(1, 0);
                        M80100.add(tempAgeGenderGroup.get(i));
                    } else {
                        tempAgeGenderGroup.get(i).setIndex(0, 0);
                        F80100.add(tempAgeGenderGroup.get(i));
                    }
                }
            } else if (tempAgeGenderGroup.get(i).getIndex(1) == -1) {
                double temp[] = new double[5];
                double min = 99999999;
                if (tempAgeGenderGroup.get(i).getIndex(0) == 1) {
                    if (!Double.isNaN(Math.abs(getAverageStats(M020, 7)))) {
                        temp[0] = Math.abs(getAverageStats(M020, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    } else {
                        temp[0] = 99999999;
                    }
                    if (!Double.isNaN(Math.abs(getAverageStats(M6080, 7)))) {
                        temp[3] = Math.abs(getAverageStats(M6080, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    } else {
                        temp[3] = 99999999;
                    }

                    if (!Double.isNaN(Math.abs(getAverageStats(M80100, 7)))) {
                        temp[4] = Math.abs(getAverageStats(M80100, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    } else {
                        temp[4] = 99999999;
                    }
                    temp[2] = Math.abs(getAverageStats(M4060, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    temp[3] = Math.abs(getAverageStats(M6080, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j] < min) {
                            min = temp[j];
                        }
                    }
                    if (temp[0] == min) {
                        tempAgeGenderGroup.get(i).setIndex(10, 1);
                        M020.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[1] == min) {
                        tempAgeGenderGroup.get(i).setIndex(30, 1);
                        M2040.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[2] == min) {
                        tempAgeGenderGroup.get(i).setIndex(50, 1);
                        M4060.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[3] == min) {
                        tempAgeGenderGroup.get(i).setIndex(70, 1);
                        M6080.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[4] == min) {
                        tempAgeGenderGroup.get(i).setIndex(90, 1);
                        M80100.add(tempAgeGenderGroup.get(i));
                    }
                } else {
                    if (!Double.isNaN(Math.abs(getAverageStats(F020, 7)))) {
                        temp[0] = Math.abs(getAverageStats(F020, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    } else {
                        temp[0] = 99999999;
                    }
                    if (!Double.isNaN(Math.abs(getAverageStats(F6080, 7)))) {
                        temp[3] = Math.abs(getAverageStats(F6080, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    } else {
                        temp[3] = 99999999;
                    }

                    if (!Double.isNaN(Math.abs(getAverageStats(F80100, 7)))) {
                        temp[4] = Math.abs(getAverageStats(F80100, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    } else {
                        temp[4] = 99999999;
                    }
                    temp[1] = Math.abs(getAverageStats(F2040, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    temp[2] = Math.abs(getAverageStats(F4060, 7) - (tempAgeGenderGroup.get(i).getIndex(7)));
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j] < min) {
                            min = temp[j];
                        }
                    }
                    if (temp[0] == min) {
                        tempAgeGenderGroup.get(i).setIndex(10, 1);
                        F020.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[1] == min) {
                        tempAgeGenderGroup.get(i).setIndex(30, 1);
                        F2040.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[2] == min) {
                        tempAgeGenderGroup.get(i).setIndex(50, 1);
                        F4060.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[3] == min) {
                        tempAgeGenderGroup.get(i).setIndex(70, 1);
                        F6080.add(tempAgeGenderGroup.get(i));
                    }
                    if (temp[4] == min) {
                        tempAgeGenderGroup.get(i).setIndex(90, 1);
                        F80100.add(tempAgeGenderGroup.get(i));
                    }
                }
            }
        }
    }

    // function to acquire average statistic for age/gender group
    public double getAverageStats(ArrayList<patient> ageGenderGroup, int statIndex) {
        int divisor = 0;
        double total = 0;
        for (int i = 0; i < ageGenderGroup.size(); i++) {
            if (ageGenderGroup.get(i).getIndex(statIndex) != -1) {
                total += ageGenderGroup.get(i).getIndex(statIndex);
                divisor++;
            }
        }
        return total / divisor;
    }

    public ArrayList<patient> getPatients() {
        return bootstrappedData;
    }

    public void printDS(String type) {// to print out each diff dataset
        if (type == "ap") {
            for (int i = 0; i < allPatients.size(); i++) {
                double[] arr = allPatients.get(i).getFeatures();

                for (double d : arr)
                    System.out.print(d + " ");
                System.out.print("\n");
            }
        }
        if (type == "trainp") {
            for (int i = 0; i < trainingPatients.size(); i++) {
                double[] arr = trainingPatients.get(i).getFeatures();

                for (double d : arr)
                    System.out.print(d + " ");
                System.out.print(" " + "|" + " ");
            }
        }
        if (type == "testp") {
            for (int i = 0; i < testPatients.size(); i++) {
                double[] arr = testPatients.get(i).getFeatures();

                for (double d : arr)
                    System.out.print(d + " ");
                System.out.print(" " + "|" + " ");
            }
        } else if (type == "bd") {
            for (int i = 0; i < bootstrappedData.size(); i++) {

                double[] arr = bootstrappedData.get(i).getFeatures();
                for (double d : arr)
                    System.out.print(d + ", ");
                System.out.println();

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

        // System.out.println("the splits have been updated!"); <--
    }

    public void bootstrapDataset(int sampleSize) {// update with random after done debugging <- done
        int sizeT = trainingPatients.size();
        // System.out.println(sizeT);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < sampleSize; i++) {
            int tempKey = random.nextInt(0, sizeT);
            // int tempKey = i%sizeT;
            bootstrappedData.add(trainingPatients.get(tempKey));
        }

        // System.out.println("bootstrapping is complete, print list to double check!");
        // <--
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
