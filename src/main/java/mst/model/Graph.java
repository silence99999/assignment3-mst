package mst.model;

import java.util.*;

public class Graph {
    private int vertices;
    private List<Edge> edges;
    private Map<Integer, List<Edge>> adjacencyList;

    private Map<String, Integer> nodeNameToIndex;
    private Map<Integer, String> indexToNodeName;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        this.nodeNameToIndex = new HashMap<>();
        this.indexToNodeName = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
        adjacencyList.get(source).add(edge);
        adjacencyList.get(destination).add(new Edge(destination, source, weight));
    }


    public void setNodeNames(Map<String, Integer> nodeMapping) {
        this.nodeNameToIndex = nodeMapping;

        for (Map.Entry<String, Integer> entry : nodeMapping.entrySet()) {
            this.indexToNodeName.put(entry.getValue(), entry.getKey());
        }
    }

    public String getNodeName(int index) {
        return indexToNodeName.getOrDefault(index, String.valueOf(index));
    }

    public Integer getNodeIndex(String name) {
        return nodeNameToIndex.get(name);
    }

    public Map<Integer, String> getNodeNames() {
        return new HashMap<>(indexToNodeName);
    }

    public int getVertices() { return vertices; }
    public List<Edge> getEdges() { return new ArrayList<>(edges); }
    public Map<Integer, List<Edge>> getAdjacencyList() { return adjacencyList; }

    public boolean isConnected() {
        if (vertices == 0) return true;

        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (Edge edge : adjacencyList.get(vertex)) {
                int neighbor = edge.getDestination();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    count++;
                }
            }
        }
        return count == vertices;
    }

    public boolean hasCycle(List<Edge> mstEdges) {
        Map<Integer, List<Integer>> mstGraph = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            mstGraph.put(i, new ArrayList<>());
        }

        for (Edge edge : mstEdges) {
            mstGraph.get(edge.getSource()).add(edge.getDestination());
            mstGraph.get(edge.getDestination()).add(edge.getSource());
        }

        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (hasCycleUtil(i, -1, visited, mstGraph)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleUtil(int v, int parent, boolean[] visited,
                                 Map<Integer, List<Integer>> graph) {
        visited[v] = true;

        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                if (hasCycleUtil(neighbor, v, visited, graph)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public void printGraphStructure() {
        System.out.println("Graph with " + vertices + " vertices:");
        System.out.println("Nodes: " + indexToNodeName.values());
        System.out.println("Edges:");
        for (Edge edge : edges) {
            String fromName = getNodeName(edge.getSource());
            String toName = getNodeName(edge.getDestination());
            System.out.printf("  %s --[%.2f]-- %s%n",
                    fromName, edge.getWeight(), toName);
        }
    }
}