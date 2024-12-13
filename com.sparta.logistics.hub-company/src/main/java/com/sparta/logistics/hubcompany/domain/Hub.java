package com.sparta.logistics.hubcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Hub {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Long userId;
}
