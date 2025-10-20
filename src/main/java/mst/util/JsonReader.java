package mst.util;

import com.google.gson.*;
import mst.model.Graph;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    public static JsonObject readJsonFile(String filepath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filepath)));
        return JsonParser.parseString(content).getAsJsonObject();
    }

    public static Graph buildGraphFromJson(JsonObject graphObj) {
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