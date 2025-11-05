package com.example.trafficac.controller;

import com.example.trafficac.entity.TrafficPoint;
import com.example.trafficac.service.ClusteringService;
import com.example.trafficac.service.TrafficDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private TrafficDataService trafficDataService;

    @Autowired
    private ClusteringService clusteringService;

    @PostMapping("/upload-csv")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            // Save CSV data to database
            List<TrafficPoint> savedPoints = trafficDataService.saveCsvToDatabase(file);

            // Cluster the points
            List<Map<String, Object>> clusters = clusteringService.clusterPoints(savedPoints);

            // Ensure clusters is never null
            if (clusters == null) clusters = List.of();

            // Prepare response
            Map<String, Object> response = Map.of(
                    "status", "ok",
                    "points", savedPoints,
                    "clusters", clusters
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(
                    Map.of(
                            "status", "error",
                            "message", e.getMessage(),
                            "clusters", List.of(),       // return empty list to avoid frontend errors
                            "points", List.of()
                    )
            );
        }
    }

    @GetMapping("/refresh-random")
    public ResponseEntity<?> refreshRandom(@RequestParam(defaultValue = "80") int count) {
        var result = clusteringService.generateRandomAndCluster(count);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/clusters")
    public ResponseEntity<?> getClusters() {
        var points = trafficDataService.getAllPoints();
        var clusters = clusteringService.clusterPoints(points);
        if (clusters == null) clusters = List.of();
        return ResponseEntity.ok(Map.of("points", points, "clusters", clusters));
    }
}
