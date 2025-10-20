package mst.util;

import com.google.gson.*;

import java.io.*;
import java.util.List;
import java.util.Map;

public class JsonWriter {

    public static void writeJsonFile(String filepath, List<Map<String, Object>> data)
            throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filepath)) {
            gson.toJson(data, writer);
        }
    }

    public static void writeResultsToConsole(List<Map<String, Object>> results) {
        System.out.println("\n========================================");
        System.out.println("           SUMMARY OF RESULTS           ");
        System.out.println("========================================\n");

        for (Map<String, Object> result : results) {
            System.out.println("Graph: " + result.get("graph_name"));
            System.out.println("  Vertices: " + result.get("vertices"));
            System.out.println("  Edges: " + result.get("edges"));
            System.out.println("  Prim Cost: " + result.get("prim_cost"));
            System.out.println("  Prim Time: " + result.get("prim_time_ms") + " ms");
            System.out.println("  Prim Operations: " + result.get("prim_operations"));
            System.out.println("  Kruskal Cost: " + result.get("kruskal_cost"));
            System.out.println("  Kruskal Time: " + result.get("kruskal_time_ms") + " ms");
            System.out.println("  Kruskal Operations: " + result.get("kruskal_operations"));
            System.out.println();
        }
    }
}