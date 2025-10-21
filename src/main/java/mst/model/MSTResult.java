package mst.model;

import java.util.List;
import java.util.Map;

public class MSTResult {
    private List<Edge> mstEdges;
    private double totalCost;
    private int vertices;
    private int originalEdges;
    private int operations;
    private double executionTimeMs;
    private String algorithm;
    private Map<Integer, String> nodeNames; // NEW

    public MSTResult(List<Edge> mstEdges, double totalCost, int vertices,
                     int originalEdges, int operations, double executionTimeMs,
                     String algorithm) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.vertices = vertices;
        this.originalEdges = originalEdges;
        this.operations = operations;
        this.executionTimeMs = executionTimeMs;
        this.algorithm = algorithm;
        this.nodeNames = null;
    }


    public MSTResult(List<Edge> mstEdges, double totalCost, int vertices,
                     int originalEdges, int operations, double executionTimeMs,
                     String algorithm, Map<Integer, String> nodeNames) {
        this(mstEdges, totalCost, vertices, originalEdges, operations,
                executionTimeMs, algorithm);
        this.nodeNames = nodeNames;
    }

    public List<Edge> getMstEdges() { return mstEdges; }
    public double getTotalCost() { return totalCost; }
    public int getVertices() { return vertices; }
    public int getOriginalEdges() { return originalEdges; }
    public int getOperations() { return operations; }
    public double getExecutionTimeMs() { return executionTimeMs; }
    public String getAlgorithm() { return algorithm; }
    public void setNodeNames(Map<Integer, String> nodeNames) {
        this.nodeNames = nodeNames;
    }

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
            if (nodeNames != null) {
                String fromName = nodeNames.getOrDefault(edge.getSource(),
                        String.valueOf(edge.getSource()));
                String toName = nodeNames.getOrDefault(edge.getDestination(),
                        String.valueOf(edge.getDestination()));
                sb.append(String.format("  %s --[%.2f]-- %s\n",
                        fromName, edge.getWeight(), toName));
            } else {
                sb.append(String.format("  %s\n", edge));
            }
        }
        return sb.toString();
    }
}