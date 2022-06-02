import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Class for printing in the *.dot format
public class PrintInDot {
    Graph graph;
    String filename;
    String algorithm;
    MinHeap minHeapMST;

    // need different constructors...
    PrintInDot(Graph graph){
        this.graph = graph;
    }

    PrintInDot(Graph graph,  String algorithm){
        this.graph = graph;

        this.algorithm = algorithm;
    }

    PrintInDot(Graph graph,  String algorithm, MinHeap minHeapMST){
        this.graph = graph;
        this.algorithm = algorithm;
        this.minHeapMST = minHeapMST;
    }

    public void printGraph(Graph graph, String fileName) throws IOException{
        StringBuilder constructor = new StringBuilder(); // constructor = object which accumulates all dot language written file content
        if (graph.isDirected){
			constructor.append("digraph G {\nrankdir=LR\n");
		} else {
			constructor.append("graph G {\nrankdir=LR\n");
		}
        StringBuilder allNodes = new StringBuilder(); // allNodes contains all node relationships
        if (algorithm == "Dijkstra"){
            allNodes = printGraphInDot(new StringBuilder(), graph); 
        } else if (algorithm == "Kruskal") {
            allNodes = printGraphInDot_Kruskal(new StringBuilder(), graph, minHeapMST); 
        } else {
            allNodes = printGraphInDot(new StringBuilder(), graph);
        }
        constructor.append(allNodes);
        constructor.append("}");
        createFile(fileName + ".dot", constructor.toString()); // writes the *.dot file
      }
      
      // Printing the relationships between the nodes by the rules of the dot language
      private static StringBuilder printGraphInDot(StringBuilder constructor, Graph graph) {
          ArrayList<Node> processedNodes = new ArrayList<Node>();
          String connector = new String();
          if (graph.isDirected) {connector = "->";} else {connector = "--";}
          for (Node node : graph.nodes) {
              for (HashMap.Entry<Node, Integer> adjNode : node.adjacentNodes.entrySet()) {
                  if (!processedNodes.contains(node)) {
                      if (adjNode.getKey().parent == node) {
                          constructor.append(String.format("%s %s %s [color = blue] [label = %d]\n",
                                  adjNode.getKey().parent.label, connector, adjNode.getKey().label, adjNode.getValue()));
                      } else {
                          constructor.append(String.format("%s %s %s [label = %d]\n", node.label, connector,
                                  adjNode.getKey().label, adjNode.getValue()));
                      }
                  }
                }
            processedNodes.add(node);
            }
        return constructor; // returns a string containing all node relationships
    }

    private static StringBuilder printGraphInDot_Kruskal(StringBuilder constructor, Graph graph, MinHeap minHeapMST) { // auxiliary method for the Minimum Spanning Tree printing
        for (MinHeapNode heapNode : minHeapMST.minHeap) {
            constructor.append(String.format("%s -- %s [color = red] [label = %d]\n", heapNode.edge.src.label, heapNode.edge.dst.label, heapNode.weight));
          }
      return constructor;
  }

      // Creating the dot file
    private void createFile(String fileName, String text) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(text.getBytes());
        outputStream.close();
    }
}
