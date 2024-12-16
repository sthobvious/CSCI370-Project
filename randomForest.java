import java.util.ArrayList;

public class randomForest {
    int nTrees = 13; // make sure this is always odd to avoid a perfect 50/50 split between the trees
    int maxDepth = 3;
    int minsamplessplit = 3;
    int nFeatures;
    ArrayList<decisionTree> trees;

    public randomForest() {
    }// empty constructor

    public ArrayList<decisionTree> getTrees() { //set for trees
        return trees;
    }

    public void setTrees(ArrayList<decisionTree> newTrees) { //get for trees
        this.trees = newTrees;
    }

    public void fit(patientForm dataset) {// to start the RF process
        setTrees(new ArrayList<decisionTree>());

        for (int i = 0; i < nTrees; i++) {
            trees.add(new decisionTree(dataset, 3));
        }
    }

    public ArrayList<Integer> predict(patientForm testcases) {// predict the patients SD 
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

        for (decisionTree dt : getTrees()) {
            results.add(dt.predict(testcases));
        }

        ArrayList<Integer> predictions = majorityVote(results);

        return predictions;
    }

    public ArrayList<Integer> majorityVote(ArrayList<ArrayList<Integer>> input) {
        ArrayList<ArrayList<Integer>> placeholder = transpose(input); // helper function to transpose the list whithin
                                                                      // the list

        ArrayList<Integer> collection = new ArrayList<>();

        for (ArrayList<Integer> list : placeholder) { // finding the majority with helper function
            System.out.println(list);
            collection.add(findMajority(list));
        }
        return collection; // return the majority voted list
    }

    public static int findMajority(ArrayList<Integer> list) {;// helper function to find majority
        int oneCount = 0;
        int zeroCount = 0;

        for (Integer i : list) {
            if (i == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
        }
        if (oneCount > zeroCount)
            return 1;
        else
            return 0;
    }

    public static ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>> matrixIn) {//helper function to transpose matrix
        ArrayList<ArrayList<Integer>> matrixOut = new ArrayList<ArrayList<Integer>>();
        if (!matrixIn.isEmpty()) {
            int noOfElementsInList = matrixIn.get(0).size();
            for (int i = 0; i < noOfElementsInList; i++) {
                ArrayList<Integer> col = new ArrayList<Integer>();
                for (ArrayList<Integer> row : matrixIn) {
                    col.add(row.get(i));
                }
                matrixOut.add(col);
            }
        }
        return matrixOut;
    }

}
