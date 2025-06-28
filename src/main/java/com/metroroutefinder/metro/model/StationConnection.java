package com.metroroutefinder.metro.model;


import jakarta.persistence.*;

@Entity
public class StationConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Station fromStation;

    @ManyToOne
    private Station toStation;

    @ManyToOne
    private MetroLine line;

    private float distance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public MetroLine getLine() {
        return line;
    }

    public void setLine(MetroLine line) {
        this.line = line;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
