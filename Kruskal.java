import java.util.ArrayList;
import java.util.Iterator;

public class Kruskal {
    Graph graph;
    MinHeap minHeap = new MinHeap();
    MinHeap minHeapMST = new MinHeap(); // auxiliary Min Heap (needed for printing in dot language)
    ArrayList<ArrayList<Node>> A = new ArrayList<ArrayList<Node>>(); 

    // Method for getting the Minimum Spanning Tree of a graph
    public MinHeap getMST(Graph graph){
        for (Node node : graph.nodes) {
            node.adjacentNodes.forEach((adjNode, weight) 
            -> minHeap.push(new MinHeapNode(new Edge(node, adjNode), weight))); // Adding all graph nodes and edge weights to the Min Heap...
            ArrayList<Node> individualArray = new ArrayList<Node>(); // Array List for storing each individual graph node
            individualArray.add(node);
            A.add(individualArray); // A is list of lists, each list contains one graph node
        }
        while (A.size() > 1) { // at the end only one Array List, containing the MST, should remain in A
            MinHeapNode heapNode = minHeap.pull();
            for (ArrayList<Node> nodeArray : A) { // traversing through A and looking a complete heapNode (root of the MinHeap) edge -> edge with minimal weight
                if (nodeArray.contains(heapNode.edge.src) && !nodeArray.contains(heapNode.edge.dst)) { 
                    nodeArray.add(heapNode.edge.dst);
                    for (ArrayList<Node> nodeArray1 : A) {
                        if (nodeArray1.contains(heapNode.edge.dst) && nodeArray1.size() == 1) {
                            nodeArray1.clear(); // clear the individual array after adding the node to another array
                            minHeapMST.push(heapNode);
                        }
                    }
                } else if (!nodeArray.contains(heapNode.edge.src) && nodeArray.contains(heapNode.edge.dst)) {
                    nodeArray.add(heapNode.edge.src);
                    for (ArrayList<Node> nodeArray1 : A) {
                        if (nodeArray1.contains(heapNode.edge.src) && nodeArray1.size() == 1) {
                            nodeArray1.clear();
                            minHeapMST.push(heapNode);
                        }
                    }
                } else {continue;}
            }
            for (Iterator<ArrayList<Node>> it = A.iterator(); it.hasNext();) { // remove empty lists...
                ArrayList<Node> emptyList = it.next();
                if (emptyList.isEmpty()){
                    it.remove();  
                }
            } 
        }
        return minHeapMST; // return the minHeapMST used for printing in *dot
    }
}