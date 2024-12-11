package com.sparta.logistics.hubroute.infrastructure.client;

import com.sparta.logistics.hubroute.infrastructure.dto.HubDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hub-company-service")
public interface HubCompanyClient {

    @GetMapping("/api/v1/hubs")
    List<HubDto> getAllHubs();

    @GetMapping("/api/v1/hubs/{hubId}")
    HubDto getHubById(@PathVariable("hubId") Long hubId);

}
