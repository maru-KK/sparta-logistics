package com.sparta.logistics.hubroute.infrastructure.cache.dto;

import com.sparta.logistics.hubroute.domain.HubRoute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HubRouteCache implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String KEY_FORMAT = "hub-route:%d:%d";

    private Long id;
    private Long originHubId;
    private Long destinationHubId;
    private int actualDuration;
    private double actualDistance;

    public static String findKeyFrom(Long originHubId, Long destinationHubId) {
        return String.format(KEY_FORMAT, originHubId, destinationHubId);
    }

    public static String findKeyFrom(Long id) {
        return String.format("hub-route:%d", id);
    }

    public HubRoute toDomain() {
        return new HubRoute(
                id, originHubId, destinationHubId, actualDuration, actualDistance
        );
    }

    public static HubRouteCache from(HubRoute hubRoute) {
        return new HubRouteCache(
                hubRoute.gethubRouteId(),
                hubRoute.getOriginHubId(),
                hubRoute.getDestinationHubId(),
                hubRoute.getActualDuration(),
                hubRoute.getActualDistance()
        );
    }

    public String key() {
        return String.format(KEY_FORMAT, originHubId, destinationHubId);
    }
}