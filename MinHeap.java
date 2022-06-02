import java.util.ArrayList;

// Min Heap for managing the priority queue operations
public class MinHeap {
    ArrayList<MinHeapNode> minHeap;

    MinHeap() {
        minHeap = new ArrayList<MinHeapNode>();
    }

    // Adding a node to the heap and restoring the min heap condition
    public void push(MinHeapNode node) {
        int nodeIdx = minHeap.indexOf(node); // current node index
        if (nodeIdx > 0) {
            node.parent = minHeap.get(node.getParentIdx(nodeIdx)); // get the parent node
        }
        minHeap.add(node); // add new node at the end
        // restore min heap condition
        while (node.hasParent(nodeIdx) && node.weight <= node.parent.weight) {
            swap(node.getParentIdx(nodeIdx), nodeIdx);
            nodeIdx = node.getParentIdx(nodeIdx);
        }
    }

    // Get the minimum value node (root)
    public MinHeapNode getRoot() {
        if (minHeap.isEmpty()) {
            throw new IllegalStateException();
        }
        return minHeap.get(0);
    }

    // Pulling the root node from the heap and restoring the min heap condition
    public MinHeapNode pull() {
        if (minHeap.isEmpty()) {throw new IllegalStateException();}
        MinHeapNode rootNode = getRoot();
        int nodeIdx = 0;
        if (rootNode.getRightChildIdx(nodeIdx) < minHeap.size()) {
            rootNode.rightChild = minHeap.get(rootNode.getRightChildIdx(nodeIdx));
        }
        if (rootNode.getLeftChildIdx(nodeIdx) < minHeap.size()) {
            rootNode.leftChild = minHeap.get(rootNode.getLeftChildIdx(nodeIdx));
        }
        minHeap.remove(0); // removing the first element
        // restore the min heap condition
        while (rootNode.getLeftChildIdx(nodeIdx) < minHeap.size()) { // if the node has a left child
            int closestChildIdx = rootNode.getLeftChildIdx(nodeIdx);
            if (rootNode.getRightChildIdx(nodeIdx) < minHeap.size()
                    && rootNode.rightChild.weight < rootNode.leftChild.weight) { // if the node has right child and if
                                                                                 // it`s less than the left child
                closestChildIdx = rootNode.getRightChildIdx(nodeIdx);
            }
            if (minHeap.get(nodeIdx).weight < minHeap.get(closestChildIdx).weight) { // if the weight of the current
                                                                                     // index (root) is less than the
                                                                                     // closest child --> min heap
                                                                                     // condition resored
                break;
            } else { // if not, swap
                swap(nodeIdx, closestChildIdx);
            }
            nodeIdx = closestChildIdx;
        }
        return rootNode;
    }

    public void swap(int idx1, int idx2) { // method for swapping min heap elements
        MinHeapNode temp = minHeap.get(idx1);
        minHeap.set(idx1, minHeap.get(idx2));
        minHeap.set(idx2, temp);
    }
}