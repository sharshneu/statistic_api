package com.gmail.sharshneu.statistic_api.repository;

import com.gmail.sharshneu.statistic_api.dto.TYPE;
import com.gmail.sharshneu.statistic_api.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface SensorRepository extends JpaRepository<Sensor, Long> {


    @Query("SELECT s.type, COUNT(s) FROM Sensor s GROUP BY s.type")
    List<Object[]> countSensorsByTypeMap();

    List<Sensor> findByCreatedAtBetween(
            LocalDateTime from,
            LocalDateTime to
    );

}
