package graph;

import util.Pair;

import java.util.*;

public class ShortestPathFinder {

    public static List<Integer> dijkstra(Graph graph, int startNode, int endNode) {
        var explored = new boolean[graph.numNodes];
        var previous = new int[graph.numNodes];
        var distances = new float[graph.numNodes];

        Arrays.fill(distances, Float.MAX_VALUE);
        Arrays.fill(previous, -1);

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
                        previous[neighbor] = current.first;
                        priorityQueue.add(new Pair<>(neighbor, newDist));
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = endNode; at != -1; at = previous[at]) {
            path.add(at);
        }

        Collections.reverse(path);

        return path.size() == 1 && path.get(0) != startNode ? Collections.emptyList() : path;
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
    }
}
