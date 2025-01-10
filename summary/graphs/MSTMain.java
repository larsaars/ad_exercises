import graph.Graph;
import graph.Edge;
import graph.MST;

public class MSTMain {
    public static void main(String[] args) {
        float[][] weightMatrix = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        Graph graph = new Graph(weightMatrix);
        Graph mst = MST.kruskal(graph);

        System.out.println("Edges of MST with Kruskal:");
        for (Edge edge : mst.getEdges()) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }

        mst = MST.prim(graph, 0);
        System.out.println("\nEdges of MST with Prim:");
        for (Edge edge : mst.getEdges()) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }
    }
}
