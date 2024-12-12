public class patient {
    private double[] features = new double[11];
    private int sd; // presence of Sleep Disorder or not
    private int id;

    public patient(double[] featureArr, int newsd) {
        this.features = featureArr;
        this.sd = newsd;
    }

    patient() {
    }

    public double[] getFeatures() {
        return features;
    }

    public double getspecificFeature(int index) {
        return features[index];
    }

    public int getSD() {
        return sd;
    }

    public double getID() {
        return id;
    }

    public void setID(int newID) {
        this.id = newID;
    }
    /*
     * public double getFeatData(int feat) {
     * 
     * }
     * 
     * public void printPatient(patient patient) {
     * 
     * }
     */

}
