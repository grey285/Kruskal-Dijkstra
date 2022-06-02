import java.util.HashMap;
// Class for graph nodes
public class Node {
    String label;
    HashMap<Node,Integer> adjacentNodes; // Pair connected node + weight
    Node parent;
    int distance;

    Node(){
        label = "";
        adjacentNodes = new HashMap<Node,Integer>();
    }
    // Constructor overloading
    Node(String name){
        label = name;
        adjacentNodes = new HashMap<Node,Integer>();
    }

    public void addEdge(Node node, int weight){ // adding an edge in the graph
         adjacentNodes.put(node, weight);
    }

    public void removeEdge(Node nodeToRemove){ // removing an edge in the graph
        adjacentNodes.remove(nodeToRemove);
    }
}