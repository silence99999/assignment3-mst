package mst;

import com.google.gson.*;
import mst.algorithms.*;
import mst.model.*;
import mst.util.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            String inputPath = "src/main/resources/assign_3_input.json";
            String outputPath = "src/main/resources/output.json";

            JsonObject inputData = JsonReader.readJsonFile(inputPath);
            JsonArray graphsArray = inputData.getAsJsonArray("graphs");

            List<Map<String, Object>> results = new ArrayList<>();

            for (JsonElement graphElement : graphsArray) {
                JsonObject graphObj = graphElement.getAsJsonObject();
                String graphName = "Graph " + graphObj.get("id").getAsInt();


                Graph graph = JsonReader.buildGraphFromJson(graphObj);

                System.out.println("\n=== Processing: " + graphName + " ===");

                MSTResult primResult = PrimAlgorithm.findMST(graph);
                System.out.println(primResult);

                MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
                System.out.println(kruskalResult);

                Map<String, Object> result = new HashMap<>();
                result.put("graph_name", graphName);
                result.put("vertices", graph.getVertices());
                result.put("edges", graph.getEdges().size());
                result.put("prim_cost", primResult.getTotalCost());
                result.put("prim_time_ms", primResult.getExecutionTimeMs());
                result.put("prim_operations", primResult.getOperations());
                result.put("kruskal_cost", kruskalResult.getTotalCost());
                result.put("kruskal_time_ms", kruskalResult.getExecutionTimeMs());
                result.put("kruskal_operations", kruskalResult.getOperations());

                results.add(result);
            }

            JsonWriter.writeJsonFile(outputPath, results);
            JsonWriter.writeResultsToConsole(results);

            System.out.println("Results written to: " + outputPath);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}