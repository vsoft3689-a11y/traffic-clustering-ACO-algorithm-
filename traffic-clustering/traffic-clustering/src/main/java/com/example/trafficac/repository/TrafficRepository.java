package com.example.trafficac.repository;

import com.example.trafficac.entity.TrafficPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRepository extends JpaRepository<TrafficPoint, Long> {
}
