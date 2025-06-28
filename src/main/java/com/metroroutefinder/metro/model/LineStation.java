package com.metroroutefinder.metro.model;


import jakarta.persistence.*;

@Entity

public class LineStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MetroLine line;

    @ManyToOne
    private Station station;

    private int stationNumber;
    private float distanceFromPrev;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetroLine getLine() {
        return line;
    }

    public void setLine(MetroLine line) {
        this.line = line;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public float getDistanceFromPrev() {
        return distanceFromPrev;
    }

    public void setDistanceFromPrev(float distanceFromPrev) {
        this.distanceFromPrev = distanceFromPrev;
    }


}
