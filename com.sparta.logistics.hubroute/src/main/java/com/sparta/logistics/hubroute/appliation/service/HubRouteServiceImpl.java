package com.sparta.logistics.hubroute.appliation.service;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;
import com.sparta.logistics.hubroute.infrastructure.client.HubCompanyClient;
import com.sparta.logistics.hubroute.infrastructure.dto.HubDto;
import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import com.sparta.logistics.hubroute.infrastructure.persistence.repository.HubRouteRepository;
import com.sparta.logistics.hubroute.presentation.exception.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HubRouteServiceImpl implements HubRouteService {

    private final HubRouteRepository hubRouteRepository;
    private final HubCompanyClient hubCompanyClient;
    private final RestTemplate restTemplate;

    @Value("${service.api.key}")
    private String apiKey;

    @Override
    public void calculateAndSaveAllRoutes() {
        List<HubDto> hubs = hubCompanyClient.getAllHubs();

        for (int i = 0; i < hubs.size(); i++) {
            for (int j = i + 1; j < hubs.size(); j++) {
                Long originHubId = hubs.get(i).getHubId();
                Long destinationHubId = hubs.get(j).getHubId();

                calculateAndSaveRoute(originHubId, destinationHubId);
            }
        }
    }

    @Override
    public HubRouteEntity calculateAndSaveRoute(Long originHubId, Long destinationHubId) {
        HubRouteEntity existingRoute = hubRouteRepository.findByOriginHubIdAndDestinationHubId(originHubId, destinationHubId).orElse(null);
        if (existingRoute != null) {
            return existingRoute;
        }

        HubDto originHub = hubCompanyClient.getHubById(originHubId);
        HubDto destinationHub = hubCompanyClient.getHubById(destinationHubId);

        String url = "https://api.openrouteservice.org/v2/directions/driving-car";
        String response = callApi(url, originHub.getLatitude(), originHub.getLongitude(), destinationHub.getLatitude(), destinationHub.getLongitude());

        double distance = extractDistanceFromGeoJson(response);
        double duration = extractDurationFromGeoJson(response);

        HubRouteEntity hubRouteEntity = HubRouteEntity.builder()
                .originHubId(originHubId)
                .destinationHubId(destinationHubId)
                .actualDistance(distance)
                .actualDuration((int) duration)
                .build();

        return hubRouteRepository.save(hubRouteEntity);
    }

    private String callApi(String url, Double originLat, Double originLon, Double destLat, Double destLon) {
        String apiUrl = String.format("%s?api_key=%s&start=%f,%f&end=%f,%f", url, apiKey, originLon, originLat, destLon, destLat);
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        return response.getBody();
    }

    private double extractDistanceFromGeoJson(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray features = jsonResponse.getJSONArray("features");
            JSONObject properties = features.getJSONObject(0).getJSONObject("properties");
            return properties.getJSONArray("segments").getJSONObject(0).getDouble("distance");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private double extractDurationFromGeoJson(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray features = jsonResponse.getJSONArray("features");
            JSONObject properties = features.getJSONObject(0).getJSONObject("properties");
            return properties.getJSONArray("segments").getJSONObject(0).getDouble("duration");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public HubRouteResponseDto getRouteById(Long hubRouteId) {
        HubRouteEntity hubRoute = hubRouteRepository.findById(hubRouteId)
                .orElseThrow(() -> new ResourceNotFoundException("경로를 찾을 수 없습니다: " + hubRouteId));
        return new HubRouteResponseDto(hubRoute);
    }

    @Override
    public List<HubRouteResponseDto> getAllRoutes() {
        List<HubRouteEntity> routes = hubRouteRepository.findAll();
        return routes.stream()
                .map(HubRouteResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId) {
        HubRouteEntity hubRoute = hubRouteRepository.findByOriginHubIdAndDestinationHubId(originHubId, destinationHubId)
                .orElseThrow(() -> new ResourceNotFoundException("경로를 찾을 수 없습니다: 출발 허브(" + originHubId + "), 도착 허브(" + destinationHubId + ")"));
        return new HubRouteResponseDto(hubRoute);
    }
}
