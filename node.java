import java.util.ArrayList;

public class node {
    private node left; // left child
    private node right; // right child

    private ArrayList<patient> samples = new ArrayList<>();; // array list containing all the patients in the node
    private int featureIndex; // the feature spliting on
    private double threshold; // threshold value for feature
    private double iG; // information gain

    // only needed for leaf nodes
    private int leaf = 0; // 0 = not leaf node, 1 = leaf node
    private int valueSD; // the most common value in the leaf node calculated at the end of the trees

    public node() {
    }

    public node(ArrayList<patient> dataset) {
        for (patient p : dataset) {
            samples.add(p);
        }
    }

    public node(int newfeatureIndex, double newThreshold, ArrayList<patient> dataset) {// constructor for decision nodes
        super();
        featureIndex = newfeatureIndex;
        threshold = newThreshold;
        left = null;
        right = null;
        leaf = 0;
        samples = dataset;

    }

    public node(int newleaf) { // constructor for leaf nodes
        leaf = 1;
    }

    public int getValueSD() {
        return valueSD;
    }

    public void setValueSD(int newvalueSD) {
        this.valueSD = newvalueSD;
    }

    public ArrayList<patient> getSamples() {
        return samples;
    }

    public void setSamples(ArrayList<patient> newsamples) {
        this.samples = newsamples;
    }

    public int getnumOfSamples() {
        return samples.size();
    }

    public void setLeft(node newLeft) {
        this.left = newLeft;
    }

    public node getLeft() {
        return left;
    }

    public void setRight(node newRight) {
        this.right = newRight;
    }

    public node getRight() {
        return right;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double newThreshold) {
        this.threshold = newThreshold;
    }

    public int getLeaf() {
        return leaf;
    }

    public void setLeaf(int newleaf) {
        this.leaf = newleaf;
    }

    public int getfeatureIndex() {
        return featureIndex;
    }

    public void setfeatureIndex(int newfeatureIndex) {
        this.featureIndex = newfeatureIndex;
    }

    public double getiG() {
        return iG;
    }

    public void setiG(double newiG) {
        this.iG = newiG;
    }

}
