public class decisionNode extends baseNode {

    private baseNode left;
    private baseNode right;

    private int feature1;
    private int feature2;
    private int feature3;

    private double threshold; //threshold value for feature
    private double iG; //information gain

    //only needed for leaf nodes
    private boolean label; // 0 = not leaf node, 1 = leaf node
    private int value;

    public decisionNode() {
        left = null;
        right = null;
    }
    
    public decisionNode(int f1, int f2, int f3) {//constructor for decision nodes
        left = null;
        right = null;
        feature1 = f1;
        feature2 = f2;
        feature3 = f3;
    }

    public decisionNode(boolean v, int val){//constructor for leaf nodes
        label = v;
        value = val;
    }

    public void setLeft(baseNode left) {
        this.left = left;
    }
    public baseNode getLeft() {
        return left;
    }

    public void setRight(baseNode right) {
        this.right = right;
    }
    public baseNode getRight() {
        return right;
    }

    public void setFeature1(int feature1) {
        this.feature1 = feature1;
    }
    public int getFeature1() {
        return feature1;
    }

    public void setFeature2(int feature2) {
        this.feature2 = feature2;
    }
    public int getFeature2() {
        return feature2;
    }

    public int getFeature3() {
        return feature3;
    }
    public void setFeature3(int feature3) {
        this.feature3 = feature3;
    }

    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public double getiG() {
        return iG;
    }
    public void setiG(double iG) {
        this.iG = iG;
    }

    public boolean getLabel(){
        return label;
    }
    public void setLabel(boolean label) {
        this.label = label;
    }

}
