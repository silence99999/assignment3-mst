package mst.algorithms;

import mst.model.*;
import mst.model.Edge;
import mst.model.Graph;
import mst.model.MSTResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MSTCorrectnessTest {

    @Test
    @DisplayName("Both algorithms produce same total cost")
    public void testSameTotalCost() {
        Graph graph = createTestGraph1();

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost(), 0.01,
                "Both algorithms should produce the same MST cost");
    }

    @Test
    @DisplayName("MST has V-1 edges")
    public void testMSTEdgeCount() {
        Graph graph = createTestGraph1();

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertEquals(graph.getVertices() - 1, primResult.getMstEdges().size(),
                "Prim's MST should have V-1 edges");
        assertEquals(graph.getVertices() - 1, kruskalResult.getMstEdges().size(),
                "Kruskal's MST should have V-1 edges");
    }

    @Test
    @DisplayName("MST is acyclic")
    public void testMSTAcyclic() {
        Graph graph = createTestGraph2();

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertFalse(graph.hasCycle(primResult.getMstEdges()),
                "Prim's MST should not contain cycles");
        assertFalse(graph.hasCycle(kruskalResult.getMstEdges()),
                "Kruskal's MST should not contain cycles");
    }

    @Test
    @DisplayName("MST connects all vertices")
    public void testMSTConnected() {
        Graph graph = createTestGraph2();

        MSTResult primResult = PrimAlgorithm.findMST(graph);

        Graph mstGraph = new Graph(graph.getVertices());
        for (Edge edge : primResult.getMstEdges()) {
            mstGraph.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }

        assertTrue(mstGraph.isConnected(), "MST should connect all vertices");
    }

    @Test
    @DisplayName("Execution time is non-negative")
    public void testExecutionTimeNonNegative() {
        Graph graph = createTestGraph1();

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertTrue(primResult.getExecutionTimeMs() >= 0,
                "Prim execution time should be non-negative");
        assertTrue(kruskalResult.getExecutionTimeMs() >= 0,
                "Kruskal execution time should be non-negative");
    }

    @Test
    @DisplayName("Operation count is positive")
    public void testOperationCountPositive() {
        Graph graph = createTestGraph1();

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertTrue(primResult.getOperations() > 0,
                "Prim should perform operations");
        assertTrue(kruskalResult.getOperations() > 0,
                "Kruskal should perform operations");
    }

    @Test
    @DisplayName("Results are reproducible")
    public void testReproducibility() {
        Graph graph = createTestGraph1();

        MSTResult result1 = PrimAlgorithm.findMST(graph);
        MSTResult result2 = PrimAlgorithm.findMST(graph);

        assertEquals(result1.getTotalCost(), result2.getTotalCost(), 0.01,
                "Results should be reproducible");
        assertEquals(result1.getMstEdges().size(), result2.getMstEdges().size(),
                "Edge count should be consistent");
    }

    @Test
    @DisplayName("Small graph correctness")
    public void testSmallGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertEquals(19.0, primResult.getTotalCost(), 0.01);
        assertEquals(19.0, kruskalResult.getTotalCost(), 0.01);
        assertEquals(3, primResult.getMstEdges().size());
        assertEquals(3, kruskalResult.getMstEdges().size());
    }

    @Test
    @DisplayName("Empty graph handling")
    public void testEmptyGraph() {
        Graph graph = new Graph(0);

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertEquals(0.0, primResult.getTotalCost(), 0.01);
        assertEquals(0.0, kruskalResult.getTotalCost(), 0.01);
        assertEquals(0, primResult.getMstEdges().size());
        assertEquals(0, kruskalResult.getMstEdges().size());
    }

    @Test
    @DisplayName("Single vertex graph")
    public void testSingleVertex() {
        Graph graph = new Graph(1);

        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertEquals(0.0, primResult.getTotalCost(), 0.01);
        assertEquals(0.0, kruskalResult.getTotalCost(), 0.01);
        assertEquals(0, primResult.getMstEdges().size());
        assertEquals(0, kruskalResult.getMstEdges().size());
    }


    private Graph createTestGraph1() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        return graph;
    }

    private Graph createTestGraph2() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 3);
        return graph;
    }
}