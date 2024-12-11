package com.sparta.logistics.product.application.service;

import com.sparta.logistics.product.application.exception.ProductLogicException;
import com.sparta.logistics.product.application.outputport.HubOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.ProductForCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final HubOutputPort hubOutputPort;

    public Company validateAndGetSupplierCompany(ProductForCreate product) {
        Company company = hubOutputPort.findCompany(product.getCreatedBy())
            .orElseThrow(() -> new ProductLogicException("유저 정보에 해당하는 업체를 찾을 수 없습니다."));

        if (company.isConsumer()) {
            throw new ProductLogicException("생산 업체가 아닙니다.");
        }
        return company;
    }
}
