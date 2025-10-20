package mst.algorithms;

import mst.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class PrimAlgorithmTest {

    @Test
    @DisplayName("Prim's algorithm finds correct MST for simple graph")
    public void testSimpleGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 3);

        MSTResult result = PrimAlgorithm.findMST(graph);

        assertEquals(3, result.getMstEdges().size());
        assertEquals(6.0, result.getTotalCost(), 0.01);
    }

    @Test
    @DisplayName("Prim's algorithm handles star graph")
    public void testStarGraph() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 2);
        graph.addEdge(0, 3, 3);
        graph.addEdge(0, 4, 4);

        MSTResult result = PrimAlgorithm.findMST(graph);

        assertEquals(4, result.getMstEdges().size());
        assertEquals(10.0, result.getTotalCost(), 0.01);
    }

    @Test
    @DisplayName("Prim's algorithm handles linear graph")
    public void testLinearGraph() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 4, 4);

        MSTResult result = PrimAlgorithm.findMST(graph);

        assertEquals(4, result.getMstEdges().size());
        assertEquals(10.0, result.getTotalCost(), 0.01);
    }

    @Test
    @DisplayName("Prim's algorithm produces connected MST")
    public void testMSTIsConnected() {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 4);
        graph.addEdge(4, 6, 10);
        graph.addEdge(5, 6, 2);

        MSTResult result = PrimAlgorithm.findMST(graph);

        Graph mstGraph = new Graph(graph.getVertices());
        for (Edge edge : result.getMstEdges()) {
            mstGraph.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }

        assertTrue(mstGraph.isConnected());
        assertEquals(6, result.getMstEdges().size());
    }

    @Test
    @DisplayName("Prim's algorithm on complete graph K4")
    public void testCompleteGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 2);
        graph.addEdge(0, 3, 3);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 6);

        MSTResult result = PrimAlgorithm.findMST(graph);

        assertEquals(3, result.getMstEdges().size());
        assertEquals(6.0, result.getTotalCost(), 0.01);
    }
}