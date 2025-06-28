package com.metroroutefinder.metro.dto;



import java.util.*;

public class RouteResponseDTO {
    private List<String> route;
    private int totalStations;
    private double totalDistance;
    private double totalFare;
    private List<String> interchanges;
    private String estimatedTime;
    private List<Map<String, String>> lineChanges;


    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public int getTotalStations() {
        return totalStations;
    }

    public void setTotalStations(int totalStations) {
        this.totalStations = totalStations;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public List<String> getInterchanges() {
        return interchanges;
    }

    public void setInterchanges(List<String> interchanges) {
        this.interchanges = interchanges;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public List<Map<String, String>> getLineChanges() {
        return lineChanges;
    }

    public void setLineChanges(List<Map<String, String>> lineChanges) {
        this.lineChanges = lineChanges;
    }
}



