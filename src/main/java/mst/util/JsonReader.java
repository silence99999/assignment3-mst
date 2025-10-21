package mst.util;

import com.google.gson.*;
import mst.model.Graph;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonReader {

    public static JsonObject readJsonFile(String filepath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filepath)));
        return JsonParser.parseString(content).getAsJsonObject();
    }


    public static Graph buildGraphFromJson(JsonObject graphObj) {

        JsonArray nodesArray = graphObj.getAsJsonArray("nodes");
        int vertices = nodesArray.size();

        Map<String, Integer> nodeToIndex = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            String nodeName = nodesArray.get(i).getAsString();
            nodeToIndex.put(nodeName, i);
        }

        Graph graph = new Graph(vertices);

        graph.setNodeNames(nodeToIndex);


        JsonArray edgesArray = graphObj.getAsJsonArray("edges");
        for (JsonElement edgeElement : edgesArray) {
            JsonObject edge = edgeElement.getAsJsonObject();

            String fromNode = edge.get("from").getAsString();
            String toNode = edge.get("to").getAsString();
            double weight = edge.get("weight").getAsDouble();


            int source = nodeToIndex.get(fromNode);
            int destination = nodeToIndex.get(toNode);

            graph.addEdge(source, destination, weight);
        }

        return graph;
    }

    public static Graph buildGraphFromJsonNumeric(JsonObject graphObj) {
        int vertices = graphObj.get("vertices").getAsInt();
        Graph graph = new Graph(vertices);

        JsonArray edgesArray = graphObj.getAsJsonArray("edges");
        for (JsonElement edgeElement : edgesArray) {
            JsonObject edge = edgeElement.getAsJsonObject();
            int source = edge.get("source").getAsInt();
            int destination = edge.get("destination").getAsInt();
            double weight = edge.get("weight").getAsDouble();
            graph.addEdge(source, destination, weight);
        }

        return graph;
    }
}