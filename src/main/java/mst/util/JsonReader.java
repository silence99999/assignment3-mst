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

        JsonArray nodesArray = graphObj.getAsJsonArray("nodes");
        int vertices = nodesArray.size();
        Graph graph = new Graph(vertices);


        JsonArray edgesArray = graphObj.getAsJsonArray("edges");
        for (JsonElement edgeElement : edgesArray) {
            JsonObject edge = edgeElement.getAsJsonObject();


            String from = edge.get("from").getAsString();
            String to = edge.get("to").getAsString();
            double weight = edge.get("weight").getAsDouble();


            int sourceIndex = findNodeIndex(nodesArray, from);
            int destIndex = findNodeIndex(nodesArray, to);

            if (sourceIndex == -1 || destIndex == -1) {
                throw new IllegalArgumentException("Указан неверный узел: " + from + " или " + to);
            }

            graph.addEdge(sourceIndex, destIndex, weight);
        }

        return graph;
    }


    private static int findNodeIndex(JsonArray nodes, String nodeName) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getAsString().equals(nodeName)) {
                return i;
            }
        }
        return -1;
    }


}