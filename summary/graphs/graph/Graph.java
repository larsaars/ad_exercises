package graph;

import java.util.*;

// assuming directed graph
public class Graph {
    // weight matrix in form of adjacency matrix
    // i == j: w = 0; w[i, j] = inf: no edge between i and j
    public final float[][] weightMatrix;
    public final int numNodes;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        weightMatrix = new float[numNodes][numNodes];

        // fill the weightMatrix
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (i != j) {
                    weightMatrix[i][j] = Float.POSITIVE_INFINITY;
                }
            }
        }
    }

    public Graph(float[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.numNodes = weightMatrix.length;
    }

    public Graph(List<Edge> edges, int numNodes) {
        this.numNodes = numNodes;
        weightMatrix = new float[numNodes][numNodes];

        for (Edge edge : edges) {
            addEdge(edge.src, edge.dest, edge.weight);
        }
    }

    public void addEdge(int src, int dest, float weight) {
        weightMatrix[src][dest] = weight;
    }

    public void addEdge(int src, int dest) {
        weightMatrix[src][dest] = 1;
    }

    public void addEdge(Edge edge) {
        weightMatrix[edge.src][edge.dest] = edge.weight;
    }

    public void addEdges(Edge... edges) {
        for (Edge e : edges) {
            addEdge(e);
        }
    }

    public void addEdges(int[]... edges) {
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }
    }

    public void removeEdge(int src, int dest) {
        weightMatrix[src][dest] = 0;
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (weightMatrix[i][j] != Float.POSITIVE_INFINITY) {
                    edges.add(new Edge(i, j, weightMatrix[i][j]));
                }
            }
        }

        return edges;
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[numNodes];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int i = 0; i < numNodes; i++) {
                if (weightMatrix[current][i] != Float.POSITIVE_INFINITY && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[numNodes];
        dfsUtil(startVertex, visited);
        System.out.println();
    }

    private void dfsUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int i = 0; i < numNodes; i++) {
            if (weightMatrix[vertex][i] != Float.POSITIVE_INFINITY && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    public static <T> void printMatrix(T[][] matrix) {
        for (T[] ts : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (ts[j] == null) {
                    System.out.print("N ");
                } else {
                    System.out.print(ts[j] + " ");
                }
            }
            System.out.println();
        }
    }
}