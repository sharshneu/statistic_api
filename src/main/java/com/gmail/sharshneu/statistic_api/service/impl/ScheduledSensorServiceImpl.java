package com.gmail.sharshneu.statistic_api.service.impl;

import com.gmail.sharshneu.statistic_api.dto.SensorDto;

import com.gmail.sharshneu.statistic_api.service.AuthService;
import com.gmail.sharshneu.statistic_api.service.ScheduledSensorService;
import com.gmail.sharshneu.statistic_api.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ScheduledSensorServiceImpl implements ScheduledSensorService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledSensorService.class);
    private final RestTemplate restTemplate;
    private final AuthService authService;

    private final SensorService sensorService;

    public ScheduledSensorServiceImpl(RestTemplate restTemplate, AuthService authService, SensorService sensorService) {
        this.restTemplate = restTemplate;
        this.authService = authService;
        this.sensorService = sensorService;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void fetchAndStoreSensors() {
        try {
            String token = authService.getAuthToken();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            sendRequestForSensors(entity);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void sendRequestForSensors(HttpEntity<String> entity) {
        ResponseEntity<SensorDto[]> response = restTemplate.exchange(
                "http://localhost:8080/sensors",
                HttpMethod.GET,
                entity,
                SensorDto[].class
        );
        sensorService.saveStatisticsSensors(Arrays.asList(response.getBody()));
        if (!response.getStatusCode().is2xxSuccessful() && response.getBody() == null) {
            log.error("Request error " + response.getStatusCode());
        }
    }
}
