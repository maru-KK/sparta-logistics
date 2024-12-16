package com.sparta.logistics.hubcompany.domain;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
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

    public HubEntity toEntity() {
        return new HubEntity(
                this.hubId,
                this.name,
                this.address,
                this.latitude,
                this.longitude,
                this.userId
        );
    }

    public Long getHubId() {return hubId;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public BigDecimal getLatitude() {return latitude;}
    public BigDecimal getLongitude() {return longitude;}
    public Long getUserId() {return userId;}
}
