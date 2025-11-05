package com.example.trafficac.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "traffic_points")
public class TrafficPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double lat;
    private double lon;
    private double speed;
    private String timestamp;
    private String city;
    private double density;
    private String clusterLabel;

    public TrafficPoint() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getDensity() { return density; }
    public void setDensity(double density) { this.density = density; }

    public String getClusterLabel() { return clusterLabel; }
    public void setClusterLabel(String clusterLabel) { this.clusterLabel = clusterLabel; }
}
