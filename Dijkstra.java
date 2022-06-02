import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra {
	Graph graph;
	MinHeap minHeap = new MinHeap();
	ArrayList<Node> unprocessedNodes = new ArrayList<Node>(); // to distinguish between processed and unprocessed nodes
	
	// Method to find the single source shortest path from the node "first"
	public void getSSSP(Node first, Graph graph) {
		setupNodes(first, graph); // Set start node distance to 0 and all other node to "infinity", all parents
									// nil
		for (HashMap.Entry<Node, Integer> adjNode : first.adjacentNodes.entrySet()) { // add the first node`s adjacent
																						// edges to the Min Heap
			int weight = adjNode.getValue();
			Edge edge = new Edge(first, adjNode.getKey());
			minHeap.push(new MinHeapNode(edge, weight));
		}

		while (!unprocessedNodes.isEmpty()) { // iterating through the MinHeap nodes until all graph nodes are processed
			for (MinHeapNode heapNode : minHeap.minHeap) {
				Node thisNode = heapNode.edge.src; // source (first)
				Node neighborNode = heapNode.edge.dst; // destination (first`s child)
				int pathLength = thisNode.distance + heapNode.weight;
				if (pathLength < neighborNode.distance) { // if the current path length is shorter than the distance to
															// the neighbor -> this distance becomes the path length
					neighborNode.distance = pathLength;
					neighborNode.parent = thisNode; // child becomes parent
				}
			}
			unprocessedNodes.remove(first); // "first" is now "processed" -> remove from list
			first = minHeap.pull().edge.dst; // the other node on the edge is now "first"
			for (HashMap.Entry<Node, Integer> adjNode : first.adjacentNodes.entrySet()) { // repeat as above.. add the
																							// node`s adjacent edges to
																							// the Min Heap
				int weight = adjNode.getValue();
				Edge edge = new Edge(first, adjNode.getKey());
				minHeap.push(new MinHeapNode(edge, weight));
			}
		}
	}

	// Set start node distance to 0 and all other node to "infinity", all parents nil
	public void setupNodes(Node start, Graph graph) {
		for (Node node : graph.nodes) {
			if (node != start) {
				node.distance = Integer.MAX_VALUE;
				node.parent = null;
			} else {
				node.distance = 0;
				node.parent = null;
			}
			unprocessedNodes.add(node);
		}
	}

	// prints all shortest paths in the output
	public void printDijkstra(Graph graph) {
		System.out.println("\nSingle Source Shortest Path from " + graph.nodes.get(0).label + ":\n");
		for (Node node : graph.nodes) {
			int distance = node.distance;
			ArrayList<String> list = new ArrayList<String>();
			list.add(graph.nodes.get(0).label);
			while (node.parent != null) {
				list.add(1, node.label);
				node = node.parent;
			}
			System.out.println("Connection: " + list.get(0) + " - " + list.get(list.size() - 1) + " | Distance: "
					+ distance + " | Path: " + list);
		}
		System.out.println("\n");
	}
}