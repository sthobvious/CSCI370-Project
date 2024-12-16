
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class decisionTree extends node {
    private node root;

    private int minSamplesLeaf;
    private int currDepth = 0;
    private int maxDepth = 3;
    private int c1, c2, c0;
    // private ArrayList<Integer> outcomes = new ArrayList<>();

    public decisionTree(patientForm dataset, int minSamples) {
        buildTree(dataset, minSamples);
    }

    public node getRoot() {
        return root;
    }

    public void buildTree(patientForm dataset, int minSamples) {
        this.minSamplesLeaf = minSamples;
        root = new node((dataset.getPatients()));
        root = build(root);
        //printTree(root);

    }

    public node build(node currNode) {
        // if either condition isnt met, means the tree is finished growing
        if (currNode.getnumOfSamples() >= minSamplesLeaf || currDepth < maxDepth) {
            int rand = pickRand();
            currNode.setfeatureIndex(rand);

            getbestSplit(currNode, currNode.getfeatureIndex());

            if (currNode.getiG() > 0) {
                currDepth++;
                currNode.setLeft(build(currNode.getLeft()));
                currNode.setRight(build(currNode.getRight()));
                return currNode;
            }
        }

        // this means the currnode hasnt split anymore, meaning its should be turned
        // into a leafnode
        

        currNode.setLeaf(1);
        currNode.setValueSD(calculateleafNode(currNode));
        //System.out.println(currNode.getValueSD());



        return currNode;
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

                curriG = infoGain(leftTree, rightTree, splitingNode.getSamples());

                if (curriG > maxiG) {
                    // System.out.println("yes!"); <--
                    splitingNode.setThreshold(entry.getKey());
                    splitingNode.setLeft(new node(leftTree));
                    splitingNode.setRight(new node(rightTree));
                    maxiG = curriG;
                    splitingNode.setiG(curriG);
                }
            }
            // clearing values from previous splits
            leftTree.clear();
            rightTree.clear();
            results.clear();
            leftTreeResults.clear();
            rightTreeResults.clear();
        }

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
        
        //System.out.println(featureAmt + " <= " + n.getThreshold());

        if (n.getLeaf() == 1) {// is node is a leaf node we return the sleep disorder status of the node
            return n.getValueSD();
        }
        double featureAmt = p.getspecificFeature(n.getfeatureIndex());

        if (featureAmt <= n.getThreshold()) {
            //System.out.println("true^");
            return makePrediction(p, n.getLeft());
        } else {
            //System.out.println("false^");
            return makePrediction(p, n.getRight());
        }
    }

    public void printTree(node root) {
        if (root == null)
            return;

        // First deal with the node
        System.out.print(root.getnumOfSamples() + " ");

        // Then recur on left subtree
        printTree(root.getLeft());

        // Finally recur on right subtree
        printTree(root.getRight());
    }

}
