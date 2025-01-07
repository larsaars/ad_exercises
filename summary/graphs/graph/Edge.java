package graph;

public class Edge implements Comparable<Edge>{
    public int src, dest;
    public float weight;

    public Edge(int src, int dest, float weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return Float.compare(this.weight, edge.weight);
    }
}
