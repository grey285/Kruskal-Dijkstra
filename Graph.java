import java.io.IOException;
import java.util.ArrayList;

public class Graph { 
    ArrayList<Node> nodes;
    MinHeap minPriorityQueue;
    boolean isDirected;

    Graph(){
        nodes = new ArrayList<Node>(); // Array list to store graph nodes
    }

    public static void main(String[] args) throws IOException {

		Graph graph=new Graph(); 
		graph.isDirected = true; // Setting the graph type to directed

        // adding the nodes...
		Node weimar = new Node("Weimar");
		graph.addNode(weimar);
		Node erfurt = new Node("Erfurt");
		graph.addNode(erfurt);
		Node jena = new Node("Jena");
		graph.addNode(jena);
		Node gotha = new Node("Gotha");
		graph.addNode(gotha);
		Node frankfurt = new Node("Frankfurt");
		graph.addNode(frankfurt);
        Node offenbach = new Node("Offenbach");
        graph.addNode(offenbach);
        Node mannheim = new Node("Mannheim");
        graph.addNode(mannheim);
        Node heidelberg = new Node("Heidelberg");
        graph.addNode(heidelberg);

        // adding the edges...
		weimar.addEdge(jena, 1);
		weimar.addEdge(erfurt, 3);
		weimar.addEdge(gotha, 3);
		jena.addEdge(erfurt, 1);
		erfurt.addEdge(frankfurt, 15);
		gotha.addEdge(frankfurt, 13);
        frankfurt.addEdge(offenbach, 3);
        gotha.addEdge(offenbach, 15);
        offenbach.addEdge(mannheim, 10);
        frankfurt.addEdge(mannheim, 11);
        mannheim.addEdge(heidelberg, 3);
        offenbach.addEdge(heidelberg, 13);

        // Uncomment to remove edges (to test the Node.removeEdge method)
        // weimar.removeEdge(jena); 
        // erfurt.removeEdge(frankfurt);
        // gotha.removeEdge(offenbach);

        // printing the unprocessed graph in *dot format
        PrintInDot print_unprocessed_graph = new PrintInDot(graph, "unprocessed");
		print_unprocessed_graph.printGraph(graph, "unprocessed_graph");

        // performing the Dijkstra algorithm on the graph
        Dijkstra dijkstra = new Dijkstra();
		dijkstra.getSSSP(weimar, graph);
		dijkstra.printDijkstra(graph); // prints all shortest paths in the output
        // printing the graph with an indication of the single source shortest path in *dot format
        PrintInDot print_dijkstra = new PrintInDot(graph, "Dijkstra");
        print_dijkstra.printGraph(graph, "SSSP_graph");

        // performing the Kruskal algorithm on the graph
        Kruskal kruskal = new Kruskal();
        MinHeap minHeapMST = kruskal.getMST(graph);
        graph.isDirected = false;
        // printing the graph with an indication of the single source shortest path in *dot format
        PrintInDot print_kruskal = new PrintInDot(graph, "Kruskal", minHeapMST);
		print_kruskal.printGraph(graph, "MST_graph");
    }

        // Add a node
    void addNode(Node node){
        nodes.add(node);
    }
        // Remove a node
    void removeNode(Node node){
        nodes.remove(node);
    }
}