public abstract class baseNode {
    private int depth;
    public int feature;

    public baseNode() {
        depth = 0;
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }
}
