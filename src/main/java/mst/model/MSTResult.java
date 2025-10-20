package mst.model;

import java.util.List;

public class MSTResult {
    private List<Edge> mstEdges;
    private double totalCost;
    private int vertices;
    private int originalEdges;
    private int operations;
    private long executionTimeMs;
    private String algorithm;

    public MSTResult(List<Edge> mstEdges, double totalCost, int vertices,
                     int originalEdges, int operations, long executionTimeMs,
                     String algorithm) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.vertices = vertices;
        this.originalEdges = originalEdges;
        this.operations = operations;
        this.executionTimeMs = executionTimeMs;
        this.algorithm = algorithm;
    }

    public List<Edge> getMstEdges() { return mstEdges; }
    public double getTotalCost() { return totalCost; }
    public int getVertices() { return vertices; }
    public int getOriginalEdges() { return originalEdges; }
    public int getOperations() { return operations; }
    public long getExecutionTimeMs() { return executionTimeMs; }
    public String getAlgorithm() { return algorithm; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Algorithm: %s\n", algorithm));
        sb.append(String.format("Total Cost: %.2f\n", totalCost));
        sb.append(String.format("MST Edges: %d\n", mstEdges.size()));
        sb.append(String.format("Operations: %d\n", operations));
        sb.append(String.format("Execution Time: %d ms\n", executionTimeMs));
        sb.append("Edges in MST:\n");
        for (Edge edge : mstEdges) {
            sb.append(String.format("  %s\n", edge));
        }
        return sb.toString();
    }
}