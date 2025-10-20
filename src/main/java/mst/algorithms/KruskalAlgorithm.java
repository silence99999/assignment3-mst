package mst.algorithms;


import mst.model.Edge;
import mst.model.Graph;
import mst.model.MSTResult;

import java.util.*;

public class KruskalAlgorithm {

    public static MSTResult findMST(Graph graph) {
        long startTime = System.currentTimeMillis();

        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);

        UnionFind uf = new UnionFind(graph.getVertices());
        int operationCount = 0;
        double totalCost = 0.0;

        for (Edge edge : edges) {
            operationCount++;
            int root1 = uf.find(edge.getSource());
            int root2 = uf.find(edge.getDestination());

            if (root1 != root2) {
                mstEdges.add(edge);
                uf.union(edge.getSource(), edge.getDestination());
                totalCost += edge.getWeight();

                if (mstEdges.size() == graph.getVertices() - 1) {
                    break;
                }
            }
        }

        operationCount += uf.getOperations();
        long endTime = System.currentTimeMillis();

        return new MSTResult(mstEdges, totalCost, graph.getVertices(),
                graph.getEdges().size(), operationCount,
                endTime - startTime, "Kruskal");
    }
}
