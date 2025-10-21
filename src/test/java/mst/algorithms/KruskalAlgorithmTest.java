package mst.algorithms;

import mst.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class KruskalAlgorithmTest {

    @Test
    @DisplayName("Kruskal's algorithm finds correct MST for simple graph")
    public void testSimpleGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 3);

        MSTResult result = KruskalAlgorithm.findMST(graph);

        assertEquals(3, result.getMstEdges().size());
        assertEquals(6.0, result.getTotalCost(), 0.01);
    }

    @Test
    @DisplayName("Kruskal's algorithm handles duplicate weights")
    public void testDuplicateWeights() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(0, 3, 2);

        MSTResult result = KruskalAlgorithm.findMST(graph);

        assertEquals(3, result.getMstEdges().size());
        assertEquals(3.0, result.getTotalCost(), 0.01);
    }

    @Test
    @DisplayName("Kruskal's algorithm on dense graph")
    public void testDenseGraph() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 4);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 3, 7);
        graph.addEdge(1, 4, 8);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 11);

        MSTResult result = KruskalAlgorithm.findMST(graph);

        assertEquals(4, result.getMstEdges().size());
        assertEquals(14.0, result.getTotalCost(), 0.01);
    }

    @Test
    @DisplayName("Kruskal's algorithm avoids cycles")
    public void testCycleAvoidance() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 0, 3); // Creates cycle
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 5);

        MSTResult result = KruskalAlgorithm.findMST(graph);

        assertEquals(4, result.getMstEdges().size());
        assertFalse(graph.hasCycle(result.getMstEdges()));
    }

    @Test
    @DisplayName("Kruskal's algorithm on medium graph")
    public void testMediumGraph() {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.addEdge(3, 5, 4);
        graph.addEdge(5, 6, 1);
        graph.addEdge(5, 7, 3);
        graph.addEdge(6, 7, 2);

        MSTResult result = KruskalAlgorithm.findMST(graph);

        assertEquals(7, result.getMstEdges().size());
        assertTrue(result.getTotalCost() > 0);
    }
}