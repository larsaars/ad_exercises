package graph;

import java.util.*;

// assuming directed graph
public class Graph {
    public final float[][] weightMatrix;  // weight matrix in form of adjacency matrix
    public final int numNodes;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        weightMatrix = new float[numNodes][numNodes];
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
            for (int j = i + 1; j < numNodes; j++) {
                if (weightMatrix[i][j] != 0) {
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
                if (weightMatrix[current][i] > 0 && !visited[i]) {
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
            if (weightMatrix[vertex][i] > 0 && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }


    public void printWeightMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                System.out.print(weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}