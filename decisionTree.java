
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class decisionTree extends node {
    private node root;

    private int minSamples = 10;
    private int currDepth = 0;
    private int maxDepth = 3;
    private int c1, c2, c0;

    // partition func variables
    // double split;
    // double iG;
    // ArrayList<patient> leftsubTree = new ArrayList<patient>(); // all true values
    // ArrayList<patient> rightsubTree = new ArrayList<patient>(); // all false
    // values

    public decisionTree() {
    }

    public node getRoot() {
        return root;
    }

    public void buildTree(patientForm dataset, int minSamples) {
        this.minSamples = minSamples;
        // node node = new node(dataset.getPatients());
        root = new node((dataset.getPatients()));
        root = build(root);
        /*
         * ArrayList<patient> arr = root.getSamples();
         * 
         * for(patient p: arr){
         * for(double d: p.getFeatures()){
         * System.out.print(d + " ");
         * }
         * System.out.print("\n");
         * }
         */

    }

    public node build(node currNode) {
        // if either condition is made, means the tree is finished growing
        if (currNode.getnumOfSamples() >= minSamples || currDepth < maxDepth) {
            int rand = pickRand();
            currNode.setfeatureIndex(rand);
            // ystem.out.println(rand);
            getbestSplit(currNode, currNode.getfeatureIndex());

            if (currNode.getiG() > 0) {
                currDepth++;
                currNode.setLeft(build(new node(currNode.getfeatureIndex(), currNode.getThreshold(),
                        currNode.getLeft().getSamples())));
                currNode.setRight(build(new node(currNode.getfeatureIndex(), currNode.getThreshold(),
                        currNode.getRight().getSamples())));

            }
        }

        // this means the currnode hasnt split anymore, meaning its should be turned
        // into a leafnode
        currNode.setLeaf(1);
        int leafVal = calculateleafNode(currNode);
        currNode.setValueSD(leafVal);
        currNode.setLeft(null);
        currNode.setRight(null);

        return root;
    }

    public int calculateleafNode(node leafNode) {
        int ones = 0, zeroes = 0; // counters for the collection of data
        for (patient p : leafNode.getSamples()) {
            if (p.getSD() == 1)
                ones++;
            else
                zeroes++;
        }
        if (ones > zeroes)
            return 1;
        return 0;
    }

    public void getbestSplit(node splitingNode, int threshIndex) {// split all possible values and return the best
        double curriG, maxiG = Double.NEGATIVE_INFINITY;

        ArrayList<patient> leftTree = new ArrayList<patient>(); // all true values
        ArrayList<patient> rightTree = new ArrayList<patient>(); // all false values

        ArrayList<Integer> results = new ArrayList<Integer>(); // all result values
        ArrayList<Integer> leftTreeResults = new ArrayList<Integer>(); // all true values
        ArrayList<Integer> rightTreeResults = new ArrayList<Integer>(); // all false values

        Map<Double, Integer> allThresholds = new HashMap<>();

        for (patient p : splitingNode.getSamples()) {
            allThresholds.put(p.getspecificFeature(threshIndex), p.getSD());
        }

        // System.out.println(allThresholds);<--

        for (Map.Entry<Double, Integer> entry : allThresholds.entrySet()) {
            // System.out.println("spliting with " + entry.getKey());
            partition(splitingNode.getSamples(), threshIndex, entry.getKey(), leftTree, rightTree);
            // System.out.println(leftTree.size());
            // .out.println(rightTree.size());

            if (leftTree.size() > 0 && rightTree.size() > 0) {

                for (patient p : splitingNode.getSamples()) {// getting the results from ALL patients
                    results.add(p.getSD());
                }

                for (patient p : leftTree) {// getting the results from left subtree patients
                    leftTreeResults.add(p.getSD());
                }
                for (patient p : rightTree) {// getting the results from right subtree patients
                    rightTreeResults.add(p.getSD());
                }

                // System.out.print("the # of samples in parent: " +
                // splitingNode.getnumOfSamples() + "\n"); <---
                // System.out.print(" parent entropy is: " + gini(splitingNode.getSamples()) +
                // "\n"); <--

                // System.out.print("the # of samples in left child: " + leftTree.size() +
                // "\n"); <---
                // System.out.print(" left child entropy is: " + gini(leftTree) + "\n"); <--

                /*
                 * System.out.print(" sd values are left: " + "\n");
                 * for(patient p: leftTree){
                 * System.out.print(p.getSD() + ", ");
                 * }
                 * System.out.println();
                 */

                // System.out.print("the # of samples in right child: " + rightTree.size() +
                // "\n"); <---
                // System.out.print(" right child entropy is: " + gini(rightTree) + "\n"); <--

                /*
                 * System.out.print(" sd values are right: " + "\n");
                 * for(patient p: rightTree){
                 * System.out.print(p.getSD() + ", ");
                 * }
                 * System.out.println();
                 */

                curriG = infoGain(leftTree, rightTree, splitingNode.getSamples());

                // System.out.println(curriG);

                // System.out.println("testing if... " + curriG + " is > " + maxiG); <--

                if (curriG > maxiG) {
                    // System.out.println("yes!"); <--
                    splitingNode.setThreshold(entry.getKey());
                    splitingNode.setLeft(new node(leftTree));
                    splitingNode.setRight(new node(rightTree));
                    maxiG = curriG;
                    splitingNode.setiG(curriG);
                }
                // else System.out.println("no..."); <--

            }
            // clearing values from previous splits
            leftTree.clear();
            rightTree.clear();
            results.clear();
            leftTreeResults.clear();
            rightTreeResults.clear();
        }
        // System.out.println("spliting done, accepted split was" +
        // splitingNode.getThreshold());
    }

    public void partition(ArrayList<patient> currdataset, int featureI, double splitingVal, ArrayList<patient> leftT,
            ArrayList<patient> rightT) {
        for (patient p : currdataset) {
            if (p.getspecificFeature(featureI) > splitingVal)
                rightT.add(p);
            else
                leftT.add(p);
        }
    }

    public double gini(ArrayList<patient> patients) {
        ArrayList<Integer> values = new ArrayList<>();
        for (patient p : patients) {
            values.add(p.getSD());
        }

        double sumOfDifference = values.stream()
                .flatMapToDouble(v1 -> values.stream().mapToDouble(v2 -> Math.abs(v1 - v2))).sum();
        double mean = values.stream().mapToDouble(v -> v).average().getAsDouble();

        // mean is 0, iff ALL values are 0, therefore the node is completely pure
        if (mean == 0.0)
            return 0.0; // added to avoid NaN being returned

        double giniNumb = sumOfDifference / (2 * values.size() * values.size() * mean);
        return giniNumb;
    }

    public double infoGain(ArrayList<patient> left, ArrayList<patient> right, ArrayList<patient> parent) {
        double weight_l = left.size() / parent.size();
        double weight_r = right.size() / parent.size();
        double w_entropy = weight_l * gini(left) + weight_r * gini(right);

        // System.out.println("entrophy for the children combined: " + w_entropy); <--

        return gini(parent) - w_entropy;
    }

    public int pickRand() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randnum = random.nextInt(0, 11);

        while (c0 == randnum || c1 == randnum || c2 == randnum) {
            randnum = random.nextInt(0, 11);
        }
        if (currDepth == 0)
            c0 = randnum;
        else if (currDepth == 1)
            c1 = randnum;
        else if (currDepth == 2)
            c2 = randnum;

        return randnum;

    }

    // return the trained data for voting in RF class
    public ArrayList<Integer> predict(patientForm form) {
        ArrayList<Integer> returningArr = new ArrayList<>();

        for (patient p : form.getPatients()) {
            returningArr.add(makePrediction(p, getRoot()));
        }

        return returningArr;
    }

    public int makePrediction(patient p, node n) {

        if (n.getLeaf() == 1)
            return n.getValueSD();

        double featureSelect = p.getspecificFeature(n.getfeatureIndex());

        if (featureSelect <= n.getThreshold())
            return makePrediction(p, n.getLeft());
        else
            return makePrediction(p, n.getRight());

    }

}
