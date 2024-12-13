package com.sparta.logistics.hubcompany.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HubCreationRequestDto {
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long userId;
}
