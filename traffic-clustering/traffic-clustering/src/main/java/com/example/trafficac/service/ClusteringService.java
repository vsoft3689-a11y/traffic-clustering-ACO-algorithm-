package com.example.trafficac.service;

import com.example.trafficac.entity.TrafficPoint;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClusteringService {

    // Fixed cluster colors: red, yellow, green
    private static final String[] COLORS = {"#e74c3c", "#f1c40f", "#2ecc71"};

    /**
     * Generate random points and cluster them (used for /refresh-random)
     */
    public Map<String, Object> generateRandomAndCluster(int count) {
        List<TrafficPoint> points = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            TrafficPoint p = new TrafficPoint();
            p.setLat(12.9 + r.nextDouble() * 0.4);
            p.setLon(77.4 + r.nextDouble() * 0.4);
            p.setSpeed(10 + r.nextDouble() * 80);
            p.setTimestamp(String.valueOf(System.currentTimeMillis()));
            p.setCity("RandomCity");
            p.setDensity(r.nextDouble() * 100);
            points.add(p);
        }
        List<Map<String, Object>> clusters = clusterPoints(points);

        Map<String, Object> res = new HashMap<>();
        res.put("points", points);
        res.put("clusters", clusters);
        return res;
    }

    /**
     * Cluster points into 3 groups based on speed thresholds
     */
    public List<Map<String, Object>> clusterPoints(List<TrafficPoint> points) {
        if (points == null) points = new ArrayList<>();

        List<TrafficPoint> c0 = new ArrayList<>();
        List<TrafficPoint> c1 = new ArrayList<>();
        List<TrafficPoint> c2 = new ArrayList<>();

        for (TrafficPoint p : points) {
            double s = p.getSpeed();
            if (s < 30) c0.add(p);       // slow / red
            else if (s < 60) c1.add(p);  // medium / yellow
            else c2.add(p);               // fast / green
        }

        List<Map<String, Object>> clusters = new ArrayList<>();
        clusters.add(makeCluster(0, COLORS[0], c0));
        clusters.add(makeCluster(1, COLORS[1], c1));
        clusters.add(makeCluster(2, COLORS[2], c2));

        return clusters != null ? clusters : List.of();
    }

    /**
     * Create a single cluster map
     */
    private Map<String, Object> makeCluster(int id, String color, List<TrafficPoint> members) {
        Map<String, Object> m = new HashMap<>();
        m.put("id", id);
        m.put("color", color);
        m.put("members", members != null ? members : new ArrayList<>());
        return m;
    }
}
