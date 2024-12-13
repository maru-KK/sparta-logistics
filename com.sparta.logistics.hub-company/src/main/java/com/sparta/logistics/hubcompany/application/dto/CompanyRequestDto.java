package com.sparta.logistics.hubcompany.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {
    private String name;
    private String type;
    private String address;
    private Long hubId;
    private Long userId;
}
