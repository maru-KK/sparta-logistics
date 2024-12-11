package com.sparta.logistics.hubroute.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HubDto {
    private Long hubId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}
