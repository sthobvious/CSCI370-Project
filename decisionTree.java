import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class decisionTree extends node {
    private node root;

    private int minSamples = 10;
    private int currDepth = 0;
    private int maxDepth = 3;
    private int c1, c2, c0 = -1;

    // partition func variables
    //double split;
    double iG;
    //ArrayList<patient> leftsubTree = new ArrayList<patient>(); // all true values
    ArrayList<patient> rightsubTree = new ArrayList<patient>(); // all false values

    public decisionTree() {
        root.setLeft(null);
        root.setRight(null);
    }

    public void buildTree(patientForm dataset, int minSamples) {
        this.minSamples = minSamples;
        node node = new node(dataset.getPatients());
        root = build(node);
    }

    public node build(node currNode) {
        // if either condition is made, means the tree is finished growing
        if (currNode.getnumOfSamples() >= minSamples || currDepth < maxDepth) {

            currNode.setfeatureIndex(pickRand());
            getbestSplit(currNode, currNode.getfeatureIndex());

            if (iG > 0) {
                currDepth++;
                currNode.setLeft(build(new node(currNode.getfeatureIndex(), currNode.getThreshold(), currNode.getLeft().getSamples())));
                currNode.setRight(build(new node(currNode.getfeatureIndex(), currNode.getThreshold(), currNode.getRight().getSamples())));
        
            }
        }

        //this means the currnode hasnt split anymore, meaning its should be turned into a leafnode
        currNode.setLeaf(1);
        int leafVal = calculateleafNode(currNode);
        currNode.setValueSD(leafVal);

        return root;
    }

    public int calculateleafNode(node leafNode) {
        int ones=0, zeroes=0; //counters for the collection of data
        for(patient p: leafNode.getSamples()){
            if(p.getLabel()==1) ones++;
            else zeroes++;
        }
        if(ones>zeroes) return 1;
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
            // patient patient = dataset.bootstrappedData.get(i);
            allThresholds.put(p.getspecificFeature(threshIndex), p.getLabel());
        }

        for (Map.Entry<Double, Integer> entry : allThresholds.entrySet()) {
            partition(splitingNode.getSamples(), threshIndex, entry.getKey(), leftTree, rightTree);
            if (leftTree.size() > 0 && rightTree.size() > 0) {

                for (patient p : splitingNode.getSamples()) {// getting the results from ALL patients
                    results.add(p.getLabel());
                }
            
                for (patient p : leftTree) {// getting the results from left subtree patients
                    leftTreeResults.add(p.getLabel());
                }
                for (patient p : rightTree) {// getting the results from right subtree patients
                    rightTreeResults.add(p.getLabel());
                }

                double currPurity = gini(splitingNode.getSamples());
                curriG = infoGain(leftTree, rightTree, currPurity);

                if (curriG > maxiG) {
                    splitingNode.setThreshold(entry.getKey());
                    splitingNode.setLeft(new node(leftTree));
                    splitingNode.setRight(new node(rightTree));
                    maxiG = curriG;
                    splitingNode.setiG(curriG);
                }
            }
        }
    }

    public void partition(ArrayList<patient> currdataset, int featureI, double splitingVal, ArrayList<patient> leftT,
            ArrayList<patient> rightT) {
        for (patient p : currdataset){
            if (p.getspecificFeature(featureI) > splitingVal)
                rightT.add(p);
            else
                leftT.add(p);
        }
    }

    public double gini(ArrayList<patient> patients) {
        ArrayList<Integer> values = new ArrayList<>();
        for (patient p : patients) {
            values.add(p.getLabel());
        }

        double sumOfDifference = values.stream()
                .flatMapToDouble(v1 -> values.stream().mapToDouble(v2 -> Math.abs(v1 - v2))).sum();
        double mean = values.stream().mapToDouble(v -> v).average().getAsDouble();
        return sumOfDifference / (2 * values.size() * values.size() * mean);
    }

    public double infoGain(ArrayList<patient> left, ArrayList<patient> right, double curr) {
        double p = left.size() / left.size() + right.size();
        return curr - p * gini(left) - (1 - p) * gini(right);
    }

    public int pickRand() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randnum = random.nextInt(0, 12);
        ;

        c0 = randnum;
        while (c0 == randnum || c1 == randnum || c2 == randnum) {
            randnum = random.nextInt(0, 12);
        }
        if (currDepth == 0)
            c0 = randnum;
        else if (currDepth == 1)
            c1 = randnum;
        else if (currDepth == 2)
            c2 = randnum;

        return randnum;

    }

    //following code is to print and check manually if the trees are splitting properly 
    public void traverseNodes(StringBuilder sb, String padding, String pointer, node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getSamples());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }   

    public String traversePreOrder(node root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getSamples());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }   

    public void print(PrintStream os) {
        os.print(traversePreOrder(root));
    }
    









}
