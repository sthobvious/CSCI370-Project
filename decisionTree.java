public class decisionTree extends baseNode {
    baseNode root;
    private int maxFeatures;
    //private int numLabels = 3;
    private int minSamplesSplit;
    private int maxDepth;

    public decisionTree() { //empty constructor
        root = null;
    }
    public decisionTree(decisionNode node) { //constructor to start a tree with a given patient
        root = node;
    }


    public void train(decisionTree data) {
        root = buildTree(data);
    }

    private baseNode buildTree(decisionTree dataTree){
        baseNode node = dataTree.root;




        return node;
    }



    public int getMaxFeatures() {
        return maxFeatures;
    }
    public void setMaxFeatures(int newmaxFeatures) {
        this.maxFeatures = newmaxFeatures;
    }
    public int getminSamplesSplit() {
        return minSamplesSplit;
    }
    public void setminSamplesSplit(int newminSamplesSplit) {
        this.minSamplesSplit = newminSamplesSplit;
    }
    public int getmaxDepth() {
        return maxDepth;
    }
    public void setMinSamplesLeaf(int newmaxDepth) {
        this.maxDepth = newmaxDepth;
    }
}
