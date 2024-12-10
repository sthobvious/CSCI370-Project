public abstract class baseNode {
    private int depth;

    public baseNode() { //empty constructor
        depth = 0;
    }

    //set depth
    public void setDepth(int depth) {
        this.depth = depth;
    }
    //get depth
    public int getDepth() {
        return depth;
    }
}
