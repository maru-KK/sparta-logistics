package com.sparta.logistics.hubroute.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "p_hub_route")
public class HubRouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_route_id")
    private Long hubRouteId;

    @Column(name = "origin_hub_id", nullable = false)
    private Long originHubId;

    @Column(name = "destination_hub_id", nullable = false)
    private Long destinationHubId;

    @Column(name = "actual_distance", nullable = false)
    private Double actualDistance;

    @Column(name = "actual_duration", nullable = false)
    private Integer actualDuration;

}
