package com.gmail.sharshneu.statistic_api.service;

import com.gmail.sharshneu.statistic_api.dto.AuthResponse;
import com.gmail.sharshneu.statistic_api.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAuthToken() {
        LoginRequest loginRequest = new LoginRequest("admin", "admin123");
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
                "http://localhost:8080/api/login",
                loginRequest,
                AuthResponse.class
        );
        return response.getBody().token();
    }
}

