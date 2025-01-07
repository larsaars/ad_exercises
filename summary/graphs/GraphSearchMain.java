import graph.Graph;

public class GraphSearchMain {
    public static void main(String[] args) {
        Graph graph = new Graph(new float[][]{
                {0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 0},
        });

        // Perform BFS and DFS
        graph.bfs(0);
        graph.dfs(0);
    }
}
