package mst;

import com.google.gson.*;
import mst.algorithms.*;
import mst.model.*;
import mst.util.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            String basePath = "src/main/resources/";
            String[] testFiles = {
                    "test_small_graph.json",
                    "test_medium_graph.json",
                    "test_large_graph.json",
                    "assign_3_input.json"
            };

            for (String fileName : testFiles) {
                String inputPath = basePath + fileName;
                String outputPath = basePath + fileName.replace(".json", "_output.json");

                System.out.println("\n============================");
                System.out.println("Running test: " + fileName);
                System.out.println("============================");

                JsonObject fileObj = JsonReader.readJsonFile(inputPath);

                JsonArray graphsArray;
                if (fileObj.has("graphs")) {
                    graphsArray = fileObj.getAsJsonArray("graphs");
                } else {
                    graphsArray = new JsonArray();
                    graphsArray.add(fileObj);
                }


                List<Map<String, Object>> allResults = new ArrayList<>();


                for (JsonElement element : graphsArray) {
                    JsonObject graphObj = element.getAsJsonObject();

                    String graphName = graphObj.has("name")
                            ? graphObj.get("name").getAsString()
                            : fileName + " (id=" + graphObj.get("id").getAsInt() + ")";

                    Graph graph = JsonReader.buildGraphFromJson(graphObj);

                    MSTResult primResult = PrimAlgorithm.findMST(graph);
                    MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

                    Map<String, Object> result = new LinkedHashMap<>();
                    result.put("graph_name", graphName);
                    result.put("vertices", graph.getVertices());
                    result.put("edges", graph.getEdges().size());
                    result.put("prim_cost", primResult.getTotalCost());
                    result.put("prim_time_ms", primResult.getExecutionTimeMs());
                    result.put("prim_operations", primResult.getOperations());
                    result.put("kruskal_cost", kruskalResult.getTotalCost());
                    result.put("kruskal_time_ms", kruskalResult.getExecutionTimeMs());
                    result.put("kruskal_operations", kruskalResult.getOperations());

                    allResults.add(result);

                    JsonWriter.writeResultsToConsole(Collections.singletonList(result));
                }

                JsonWriter.writeJsonFile(outputPath, allResults);

                System.out.println("✅ Results written to: " + outputPath);
            }

            System.out.println("\nAll tests completed successfully.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
