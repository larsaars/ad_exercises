package graph;

import util.DisjointSet;

import java.util.Collections;
import java.util.PriorityQueue;

public class MST {
    public static Graph kruskal(Graph graph) {
        var edges = graph.getEdges();

        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(graph.numNodes);
        Graph mst = new Graph(graph.numNodes);

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

    public static Graph prim(Graph graph, int startNode) {
        var visited = new boolean[graph.numNodes];
        var mst = new Graph(graph.numNodes);
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

            for (int node = 0; node < graph.numNodes; node++) {
                // is neighbor and has not been visited yet
                if (graph.weightMatrix[edge.dest][node] != Float.POSITIVE_INFINITY && !visited[node]) {
                    pq.add(new Edge(edge.dest, node, graph.weightMatrix[edge.dest][node]));
                }
            }
        }

        return mst;
    }
}
