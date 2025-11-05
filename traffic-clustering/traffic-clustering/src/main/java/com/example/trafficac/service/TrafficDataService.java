package com.example.trafficac.service;

import com.example.trafficac.entity.TrafficPoint;
import com.example.trafficac.repository.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficDataService {

    @Autowired
    private TrafficRepository trafficRepository;

    /**
     * Save CSV data to database.
     * CSV format: lat,lon,speed,timestamp,city,density
     */
    public List<TrafficPoint> saveCsvToDatabase(MultipartFile file) throws Exception {
        List<TrafficPoint> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String header = br.readLine(); // Skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 6) continue; // skip invalid lines

                TrafficPoint tp = new TrafficPoint();
                try { tp.setLat(Double.parseDouble(p[0].trim())); } catch(Exception e) { tp.setLat(0); }
                try { tp.setLon(Double.parseDouble(p[1].trim())); } catch(Exception e) { tp.setLon(0); }
                try { tp.setSpeed(Double.parseDouble(p[2].trim())); } catch(Exception e) { tp.setSpeed(0); }

                tp.setTimestamp(p[3].trim());
                tp.setCity(p[4].trim());

                try { tp.setDensity(Double.parseDouble(p[5].trim())); } catch (Exception e) { tp.setDensity(0); }

                points.add(tp);
            }
        }

        // Clear old data and save new CSV points
        trafficRepository.deleteAll();
        return trafficRepository.saveAll(points);
    }

    /**
     * Get all points from the database
     */
    public List<TrafficPoint> getAllPoints() {
        return trafficRepository.findAll();
    }
}
