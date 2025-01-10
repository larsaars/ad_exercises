package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The CycleFinder class provides functionality to find all cycles in a given graph using Depth-First Search (DFS).
 */
public class CycleFinder {
    private final Graph graph;
    private final List<List<Integer>> cycles = new ArrayList<>();
    private final Stack<Integer> pathStack = new Stack<>();
    private final boolean[] visited;

    public CycleFinder(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.numNodes];
    }

    public List<List<Integer>> findAllCycles() {
        for (int node = 0; node < graph.numNodes; node++) {
            if (!visited[node]) {
                dfs(node, -1);
            }
        }
        return cycles;
    }

    private void dfs(int current, int parent) {
        visited[current] = true;
        pathStack.push(current);

        for (int neighbor = 0; neighbor < graph.numNodes; neighbor++) {
            // if is no neighbor, continue
            if (graph.weightMatrix[current][neighbor] == 0) {
                continue;
            }

            // is neighbor
            // if neighbor is not visited, visit it
            if (!visited[neighbor]) {
                dfs(neighbor, current);
                // 0 -> 1 -> 0 is not a cycle,
                // so make sure that parent is not the neighbor
                // if neighbor is contained in the pathStack, then it is a cycle
            } else if (parent != neighbor && pathStack.contains(neighbor)) {
                List<Integer> cycle = new ArrayList<>();
                cycle.add(current);

                for (int i = pathStack.indexOf(neighbor); i < pathStack.size(); i++) {
                    cycle.add(pathStack.get(i));
                }

                cycles.add(cycle);
            }
        }

        pathStack.pop();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        var cycles = new CycleFinder(graph).findAllCycles();

        for (List<Integer> cycle : cycles) {
            System.out.println(cycle);
        }
    }
}