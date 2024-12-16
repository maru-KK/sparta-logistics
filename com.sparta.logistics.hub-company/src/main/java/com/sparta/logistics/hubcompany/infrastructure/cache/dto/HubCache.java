package com.sparta.logistics.hubcompany.infrastructure.cache.dto;

import com.sparta.logistics.hubcompany.domain.Hub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HubCache implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String KEY_FORMAT = "hub:%d";

    private Long id;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long userId;

    public static String findKeyFrom(Long hubId) {
        return String.format(KEY_FORMAT, hubId);
    }

    public Hub toDomain() {
        return new Hub(
                id, name, address, latitude, longitude, userId
        );
    }

    public static HubCache from(Hub hub) {
        return new HubCache(
                hub.getHubId(),
                hub.getName(),
                hub.getAddress(),
                hub.getLatitude(),
                hub.getLongitude(),
                hub.getUserId()
        );
    }

    public String key() {
        return String.format(KEY_FORMAT, id);
    }
}
