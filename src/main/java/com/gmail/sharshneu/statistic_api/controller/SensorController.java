package com.gmail.sharshneu.statistic_api.controller;


import com.gmail.sharshneu.statistic_api.dto.SensorDto;
import com.gmail.sharshneu.statistic_api.dto.TYPE;
import com.gmail.sharshneu.statistic_api.service.SensorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public ResponseEntity<Map<TYPE, Long>> getStatistic() {
        return ResponseEntity.ok(sensorService.getSensorStatistic());
    }

    @GetMapping("/by-time-range")
    public ResponseEntity<List<SensorDto>> getSensorsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        List<SensorDto> result = sensorService.getSensorsByTimeRange(from, to);
        return ResponseEntity.ok(result);
    }

}
