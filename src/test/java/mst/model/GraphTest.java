package mst.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GraphTest {

    @Test
    @DisplayName("Graph initialization")
    public void testGraphInitialization() {
        Graph graph = new Graph(5);

        assertEquals(5, graph.getVertices());
        assertEquals(0, graph.getEdges().size());
        assertNotNull(graph.getAdjacencyList());
    }

    @Test
    @DisplayName("Adding edges to graph")
    public void testAddEdge() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 5.0);
        graph.addEdge(1, 2, 3.0);

        assertEquals(2, graph.getEdges().size());
        assertEquals(1, graph.getAdjacencyList().get(0).size());
        assertEquals(2, graph.getAdjacencyList().get(1).size());
        assertEquals(1, graph.getAdjacencyList().get(2).size());
    }

    @Test
    @DisplayName("Graph connectivity check - connected")
    public void testConnectedGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);

        assertTrue(graph.isConnected());
    }

    @Test
    @DisplayName("Graph connectivity check - disconnected")
    public void testDisconnectedGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);

        assertFalse(graph.isConnected());
    }

    @Test
    @DisplayName("Cycle detection - no cycle")
    public void testNoCycle() {
        Graph graph = new Graph(4);

        List<Edge> treeEdges = List.of(
                new Edge(0, 1, 1),
                new Edge(1, 2, 2),
                new Edge(2, 3, 3)
        );

        assertFalse(graph.hasCycle(treeEdges));
    }

    @Test
    @DisplayName("Cycle detection - has cycle")
    public void testHasCycle() {
        Graph graph = new Graph(4);

        List<Edge> cycleEdges = List.of(
                new Edge(0, 1, 1),
                new Edge(1, 2, 2),
                new Edge(2, 3, 3),
                new Edge(3, 0, 4)
        );

        assertTrue(graph.hasCycle(cycleEdges));
    }

    @Test
    @DisplayName("Empty graph is connected")
    public void testEmptyGraphConnected() {
        Graph graph = new Graph(0);
        assertTrue(graph.isConnected());
    }

    @Test
    @DisplayName("Single vertex graph is connected")
    public void testSingleVertexConnected() {
        Graph graph = new Graph(1);
        assertTrue(graph.isConnected());
    }
}