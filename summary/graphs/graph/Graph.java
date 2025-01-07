package graph;

import util.DisjointSet;

import java.util.*;

public class Graph {
    public final float[][] weightMatrix;  // weight matrix in form of adjacency matrix
    public final int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        weightMatrix = new float[numVertices][numVertices];
    }

    public Graph(float[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.numVertices = weightMatrix.length;
    }

    public Graph(List<Edge> edges, int numVertices) {
        this.numVertices = numVertices;
        weightMatrix = new float[numVertices][numVertices];

        for (Edge edge : edges) {
            addEdge(edge.src, edge.dest, edge.weight);
        }
    }

    public void addEdge(int src, int dest, float weight) {
        weightMatrix[src][dest] = weight;
        weightMatrix[dest][src] = weight; // Assuming undirected graph
    }

    public void addEdge(int src, int dest) {
        weightMatrix[src][dest] = 1;
        weightMatrix[dest][src] = 1; // Assuming undirected graph
    }

    public void removeEdge(int src, int dest) {
        weightMatrix[src][dest] = 0;
        weightMatrix[dest][src] = 0; // Assuming undirected graph
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (weightMatrix[i][j] > 0) {
                    edges.add(new Edge(i, j, weightMatrix[i][j]));
                }
            }
        }

        return edges;
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int i = 0; i < numVertices; i++) {
                if (weightMatrix[current][i] > 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        dfsUtil(startVertex, visited);
        System.out.println();
    }

    private void dfsUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int i = 0; i < numVertices; i++) {
            if (weightMatrix[vertex][i] > 0 && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    public Graph kruskalMST() {
        var edges = getEdges();

        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(numVertices);
        Graph mst = new Graph(numVertices);

        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            if (rootSrc != rootDest) {
                mst.addEdge(edge.src, edge.dest, edge.weight);
                ds.union(rootSrc, rootDest);
            }
        }

        return mst;
    }

    public Graph primMST(int startNode) {
        var visited = new boolean[numVertices];
        var mst = new Graph(numVertices);
        var pq = new PriorityQueue<Edge>();

        pq.add(new Edge(startNode, startNode, 0));

        while (!pq.isEmpty()) {
            var edge = pq.poll();
            if (visited[edge.dest]) {
                continue;
            }

            visited[edge.dest] = true;
            if (edge.src != edge.dest) {
                mst.addEdge(edge.src, edge.dest, edge.weight);
            }

            for (int i = 0; i < numVertices; i++) {
                if (weightMatrix[edge.dest][i] > 0 && !visited[i]) {
                    pq.add(new Edge(edge.dest, i, weightMatrix[edge.dest][i]));
                }
            }
        }

        return mst;
    }

    public void printWeightMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}