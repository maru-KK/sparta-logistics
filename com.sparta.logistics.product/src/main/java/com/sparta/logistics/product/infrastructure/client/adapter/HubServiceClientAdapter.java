package com.sparta.logistics.product.infrastructure.client.adapter;

import com.sparta.logistics.product.application.outputport.HubOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.infrastructure.client.dto.CompanyDetailClientResponse;
import com.sparta.logistics.product.infrastructure.client.feignclient.HubServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class HubServiceClientAdapter implements HubOutputPort {

    private final HubServiceClient hubServiceClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackFindCompany")
    @Override
    public Optional<Company> findCompany(Long userId) {
        return Optional.of(hubServiceClient.getCompanyByUserId(userId).getBody().getData())
            .map(CompanyDetailClientResponse::toDomain)
            .or(Optional::empty);
    }

    public Optional<Company> fallbackFindCompany(Long userId, Throwable t) {
        log.error("product-service findCompany fallback: ", t);
        return Optional.empty();
    }
}
