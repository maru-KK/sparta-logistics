package com.sparta.logistics.hubroute.infrastructure.client;

import com.sparta.logistics.hubroute.appliation.dto.HubResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hub-company-service")
public interface HubCompanyServiceFeignClient {

    @GetMapping("/api/v1/hubs/{hubId}")
    HubResponseDto getHubById(@PathVariable("hubId") Long hubId);

    @GetMapping("/api/v1/hubs")
    List<HubResponseDto> getAllHubs();
}