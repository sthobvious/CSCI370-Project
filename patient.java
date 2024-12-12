public class patient {
    private double[] features = new double[11];
    private int label; // presence of Sleep Disorder or not

    public patient(double[] featureArr, int labelNum) {
        this.features = featureArr;
        this.label = labelNum;
    }

    patient() {
    }

    public double[] getFeatures() {
        return features;
    }

    public double getspecificFeature(int index) {
        return features[index];
    }

    public int getLabel() {
        return label;
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
