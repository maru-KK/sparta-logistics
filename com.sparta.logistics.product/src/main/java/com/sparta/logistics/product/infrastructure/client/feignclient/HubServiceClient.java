package com.sparta.logistics.product.infrastructure.client.feignclient;

import com.sparta.logistics.product.infrastructure.client.dto.CompanyDetailClientResponse;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse.Success;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubServiceClient {

    @GetMapping("/api/v1/company/user/{userId}")
    ResponseEntity<Success<CompanyDetailClientResponse>> getCompanyByUserId(@PathVariable("userId") Long userId);
}
