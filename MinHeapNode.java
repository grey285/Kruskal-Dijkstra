// Class for creation of Min Heap nodes
public class MinHeapNode {
    Edge edge;
    int weight;
    MinHeapNode parent;
    MinHeapNode leftChild;
    MinHeapNode rightChild;

    MinHeapNode(Edge edge, int weight){
        this.edge = edge;
        this.weight = weight;
    }
    // Obtaining indices of children and parents
    public int getLeftChildIdx(int parentIdx){ 
        return 2 * parentIdx + 1;
    }

    public int getRightChildIdx(int parentIdx){
        return 2 * parentIdx + 2;
    }

    public int getParentIdx(int childIdx){
        return (childIdx - 1) / 2;
    }
    // check if a node has a parent
    public boolean hasParent(int idx){
        return getParentIdx(idx) >= 0;
    }
}