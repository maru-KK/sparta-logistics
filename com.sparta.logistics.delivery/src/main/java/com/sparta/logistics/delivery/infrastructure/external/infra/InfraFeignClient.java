package com.sparta.logistics.delivery.infrastructure.external.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("infra-service")
public interface InfraFeignClient {
    @PostMapping("/api/v1/infra/ai")
    void send(@RequestBody InfraRequestDto infraRequestDto);
}
