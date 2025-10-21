package mst.algorithms;

import mst.model.Edge;
import mst.model.Graph;
import mst.model.MSTResult;

import java.util.*;

public class PrimAlgorithm {

    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();

        if (graph.getVertices() == 0) {
            return new MSTResult(new ArrayList<>(), 0.0, 0, 0, 0, 0, "Prim");
        }

        List<Edge> mstEdges = new ArrayList<>();
        boolean[] inMST = new boolean[graph.getVertices()];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int operationCount = 0;
        double totalCost = 0.0;

        inMST[0] = true;
        Map<Integer, List<Edge>> adjList = graph.getAdjacencyList();

        for (Edge edge : adjList.get(0)) {
            pq.offer(edge);
            operationCount++;
        }

        while (!pq.isEmpty() && mstEdges.size() < graph.getVertices() - 1) {
            Edge edge = pq.poll();
            operationCount++;

            int dest = edge.getDestination();

            if (inMST[dest]) {
                continue;
            }

            mstEdges.add(edge);
            totalCost += edge.getWeight();
            inMST[dest] = true;

            for (Edge nextEdge : adjList.get(dest)) {
                if (!inMST[nextEdge.getDestination()]) {
                    pq.offer(nextEdge);
                    operationCount++;
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(mstEdges, totalCost, graph.getVertices(),
                graph.getEdges().size(), operationCount,
                executionTimeMs, "Prim");
    }
}
