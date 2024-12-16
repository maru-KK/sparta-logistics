package com.sparta.logistics.product.infrastructure.client.feignclient;

import com.sparta.logistics.product.infrastructure.client.dto.CompanyDetailClientResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-company-service")
public interface HubServiceClient {

    @GetMapping("/api/v1/company/users/{userId}")
    ResponseEntity<List<CompanyDetailClientResponse>> getCompanyByUserId(@PathVariable("userId") Long userId);
}
