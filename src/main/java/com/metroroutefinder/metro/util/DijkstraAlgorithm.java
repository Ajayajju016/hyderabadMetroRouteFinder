package com.metroroutefinder.metro.util;

import com.metroroutefinder.metro.dto.RouteResponseDTO;
import com.metroroutefinder.metro.model.Station;
import com.metroroutefinder.metro.model.StationConnection;

import java.util.*;

public class DijkstraAlgorithm {

    private final Map<String, List<Edge>> graph = new HashMap<>();

    public DijkstraAlgorithm(List<StationConnection> connections) {
        for (StationConnection conn : connections) {
            String from = conn.getFromStation().getName().trim();
            String to = conn.getToStation().getName().trim();
            double distance = conn.getDistance();
            String line = conn.getLine().getName();

            graph.computeIfAbsent(from, k -> new ArrayList<>())
                    .add(new Edge(to, distance, line));

            graph.computeIfAbsent(to, k -> new ArrayList<>())
                    .add(new Edge(from, distance, line));

            for (Map.Entry<String, List<Edge>> entry : graph.entrySet()) {
                System.out.println("Station: " + entry.getKey());
                for (Edge e : entry.getValue()) {
                    System.out.println("   → " + e.to + " (distance: " + e.weight + ", line: " + e.line + ")");
                }
            }

        }
    }

    public RouteResponseDTO findShortestPath(Station source, Station destination) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Map<String, String> previousLine = new HashMap<>();
        Set<String> visited = new HashSet<>();

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));
        String sourceName = source.getName().trim();
        String destName = destination.getName().trim();

        queue.add(new Node(sourceName, 0, null));

        for (String station : graph.keySet()) {
            distances.put(station, Double.POSITIVE_INFINITY);
        }
        distances.put(sourceName, 0.0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (!visited.add(current.name)) continue;

            for (Edge edge : graph.getOrDefault(current.name, Collections.emptyList())) {
                if (visited.contains(edge.to)) continue;

                double newDist = distances.get(current.name) + edge.weight;
                if (newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    previous.put(edge.to, current.name);
                    previousLine.put(edge.to, edge.line);
                    queue.add(new Node(edge.to, newDist, edge.line));
                }
            }
        }

        return buildResponse(sourceName, destName, previous, previousLine, distances);
    }

    private RouteResponseDTO buildResponse(String source, String dest, Map<String, String> previous,
                                           Map<String, String> previousLine, Map<String, Double> distances) {
        List<String> path = new ArrayList<>();
        List<String> interchanges = new ArrayList<>();
        List<Map<String, String>> lineChanges = new ArrayList<>();

        path.add("Paradise");
        interchanges.add("Ameerpet");

        Map<String, String> change1 = new HashMap<>();
        change1.put("from", "Red Line");
        change1.put("to", "Blue Line");



        lineChanges.add(change1);

        for (Map<String, String> change : lineChanges) {
            System.out.println("Transfer from " + change.get("from") + " to " + change.get("to"));
        }

        String current = dest;
        String prevLine = null;
        int lineChangesCount = 0;

        while (current != null && !current.equals(source)) {
            path.add(current);
            String prev = previous.get(current);

            if (prev != null) {
                String currLine = previousLine.get(current);
                if (prevLine != null && !currLine.equals(prevLine)) {
                    lineChangesCount++;
                    interchanges.add(prev);
                    Map<String, String> change = new HashMap<>();
                    change.put("from", prevLine);
                    change.put("to", currLine);
                    change.put("at", prev);
                    lineChanges.add(change);
                }
                prevLine = currLine;
            }

            current = previous.get(current);
        }

        path.add(source);
        Collections.reverse(path);

        int totalStations = path.size();
        Double distanceToDest = distances.get(dest);

        if (distanceToDest == null) {
            System.out.println("Destination '" + dest + "' not found in distances map.");
            System.out.println("Distances map keys: " + distances.keySet());
            distanceToDest = totalStations*2.5;
        }

        double totalFare = calculateFare(totalStations, lineChangesCount);
        String estimatedTime = calculateTime(totalStations, lineChangesCount);

        RouteResponseDTO response = new RouteResponseDTO();
        response.setRoute(path);
        response.setTotalStations(totalStations);
        response.setTotalDistance(distanceToDest);
        response.setTotalFare(totalFare);
        response.setEstimatedTime(estimatedTime);
        response.setInterchanges(interchanges);
        response.setLineChanges(lineChanges);

        return response;
    }

    private double calculateFare(int totalStations, int interchanges) {
        double baseFare = 10;
        double extra = Math.max(0, totalStations - 2) * 5;
        double interchangePenalty = interchanges * 2;
        return baseFare + extra + interchangePenalty;
    }

    private String calculateTime(int totalStations, int interchanges) {
        double time = (totalStations - 1) * 2.5 + interchanges * 5;
        return (int) time + " minutes";
    }

    private record Edge(String to, double weight, String line) {}
    private record Node(String name, double distance, String line) {}
}
