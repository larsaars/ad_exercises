package graph;

import util.Pair;

import java.util.*;

public class ShortestPathFinder {

    public static List<Integer> dijkstra(Graph graph, int startNode, int endNode) {
        var explored = new boolean[graph.numNodes];
        var predecessors = new int[graph.numNodes];
        var distances = new float[graph.numNodes];

        Arrays.fill(distances, Float.MAX_VALUE);
        Arrays.fill(predecessors, -1);

        // priority queue of Vertex, Distance
        var priorityQueue = new PriorityQueue<Pair<Integer, Float>>(Comparator.comparingDouble(pair -> pair.second));

        distances[startNode] = 0;
        priorityQueue.add(new Pair<>(startNode, 0f));

        while (!priorityQueue.isEmpty()) {
            // find next unexplored vertex
            Pair<Integer, Float> current = priorityQueue.poll();

            if (explored[current.first])
                continue;
            explored[current.first] = true;

            if (current.first == endNode) break;

            for (int neighbor = 0; neighbor < graph.numNodes; neighbor++) {
                // is a neighbor
                if (graph.weightMatrix[current.first][neighbor] != 0 && !explored[neighbor]) {
                    float newDist = distances[current.first] + graph.weightMatrix[current.first][neighbor];
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        predecessors[neighbor] = current.first;
                        priorityQueue.add(new Pair<>(neighbor, newDist));
                    }
                }
            }
        }

        return getPathFromPredecessors(predecessors, endNode);
    }

    public static List<Integer> getPathFromPredecessors(int[] predecessors, int endNode) {
        List<Integer> path = new ArrayList<>();
        for (int at = endNode; at != -1; at = predecessors[at]) {
            path.add(at);
        }

        Collections.reverse(path);

        return path;
    }

    public static List<Integer> bellmanFord(Graph graph, int sourceNode, int endNode) {
        var predecessors = new int[graph.numNodes];
        var distances = new float[graph.numNodes];

        List<Edge> edges = graph.getEdges();

        // initialize
        Arrays.fill(predecessors, -1);
        Arrays.fill(distances, Float.MAX_VALUE);

        distances[sourceNode] = 0;


        // |V|-1 iterations (max) for relaxation of all vertices
        for (int i = 1; i < graph.numNodes; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                if (distances[e.src] != Float.MAX_VALUE && distances[e.src] + e.weight < distances[e.dest]) {
                    distances[e.dest] = distances[e.src] + e.weight;
                    predecessors[e.dest] = e.src;
                    updated = true;
                }
                if (!updated) {
                    break;
                }
            }
        }

        // check for negative cycles
        for (Edge e : edges) {
            if (distances[e.src] != Float.MAX_VALUE && distances[e.src] + e.weight < distances[e.dest]) {
                throw new IllegalArgumentException("Graph contains a negative weight cycle");
            }
        }


        return getPathFromPredecessors(predecessors, endNode);
    }


    public static void main(String[] args) {
        var graph = new Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 6);

        var path = dijkstra(graph, 0, 4);
        System.out.println(path);

        path = bellmanFord(graph, 0, 4);
        System.out.println(path);
    }
}
