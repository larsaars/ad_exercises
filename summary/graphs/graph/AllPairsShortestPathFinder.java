package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllPairsShortestPathFinder {

    private static Integer[][] predecessors;
    private static Float[][] distances;

    public static void floydWarshall(Graph graph) {
        // do a deep copy of the weight matrix
        // D is the distance matrix
        distances = new Float[graph.numNodes][graph.numNodes];
        for (int i = 0; i < graph.numNodes; i++) {
            for (int j = 0; j < graph.numNodes; j++) {
                distances[i][j] = graph.weightMatrix[i][j];
            }
        }

        // predecessor matrix
        // fill predecessors (edges)
        predecessors = new Integer[graph.numNodes][graph.numNodes];
        for (int i = 0; i < graph.numNodes; i++) {
            for (int j = 0; j < graph.numNodes; j++) {
                // there's an edge from i to j
                if (distances[i][j] != Float.POSITIVE_INFINITY && distances[i][j] != 0) {
                    predecessors[i][j] = i;
                } // else predecessors[i][j] = null; (is already null)
            }
        }

        // perform algorithm
        // if distance from i->j is shorter with k in between,
        // use rather the path with k in between
        for (int k = 0; k < graph.numNodes; k++) {
            for (int i = 0; i < graph.numNodes; i++) {
                for (int j = 0; j < graph.numNodes; j++) {
                    float newDistance = distances[i][k] + distances[k][j];
                    if (distances[i][j] > newDistance) {
                        distances[i][j] = newDistance;
                        predecessors[i][j] = predecessors[k][j];
                    }
                }
            }
        }
    }

    private static List<Integer> getPathFromPredecessors(int startNode, int endNode) {
        if (predecessors[startNode][endNode] == null) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        for (Integer at = endNode; at != null; at = predecessors[startNode][at]) {
            path.add(at);
        }

        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        float I = Float.POSITIVE_INFINITY;
        var graph = new Graph(new float[][]{
                {0, I, I, I, -1, I},
                {1, 0, I, 2, I, I},
                {I, 2, 0, I, I, -8},
                {-4, I, I, 0, 3, I},
                {I, 7, I, I, 0, I},
                {I, 5, 10, I, I, 0}
        });

        floydWarshall(graph);

        Graph.printMatrix(predecessors);
        System.out.println();
        Graph.printMatrix(distances);

       System.out.println();
       System.out.println(getPathFromPredecessors(2, 4));
    }
}
