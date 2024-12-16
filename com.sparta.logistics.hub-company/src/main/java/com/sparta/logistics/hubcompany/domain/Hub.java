package com.sparta.logistics.hubcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class Hub {
    private Long hubId;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long userId;

    public Long getHubId() {return hubId;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public BigDecimal getLatitude() {return latitude;}
    public BigDecimal getLongitude() {return longitude;}
    public Long getUserId() {return userId;}
}
