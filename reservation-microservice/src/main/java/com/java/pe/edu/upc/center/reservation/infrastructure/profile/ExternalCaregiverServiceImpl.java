package com.java.pe.edu.upc.center.reservation.infrastructure.profile;

import com.java.pe.edu.upc.center.reservation.application.internal.outboundservices.acl.ExternalCaregiverService;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Service
public class ExternalCaregiverServiceImpl implements ExternalCaregiverService {
    private final HttpClient httpClient;

    public ExternalCaregiverServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    @Override
    public boolean checkCaregiverExists(Long caregiverId) {
        String baseUrl = "http://localhost:8095/api/v1/caregiver";
        var response = httpClient.sendAsync(
                java.net.http.HttpRequest.newBuilder()
                        .uri(java.net.URI.create(baseUrl + "/" + caregiverId))
                        .GET()
                        .build(),
                java.net.http.HttpResponse.BodyHandlers.ofString()
        ).join();
        return response.statusCode() == 200;
    }
}
