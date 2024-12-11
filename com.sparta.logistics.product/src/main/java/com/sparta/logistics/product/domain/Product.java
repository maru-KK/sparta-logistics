package com.sparta.logistics.product.domain;

import com.sparta.logistics.product.domain.exception.DomainException;
import com.sparta.logistics.product.domain.vo.ProductForUpdate;
import com.sparta.logistics.product.domain.vo.Quantity;
import java.util.Objects;

public class Product {

    private Long id;
    private Long hubId;
    private Long companyId;

    private Quantity quantity;
    private String name;

    public Product(Long id, Long hubId, Long productId, Quantity quantity, String name) {
        this.id = id;
        this.hubId = hubId;
        this.companyId = productId;
        this.quantity = quantity;
        this.name = name;
    }

    public Product updateFrom(ProductForUpdate update, Company company) {
        validateMatchedCompany(company);
        this.quantity = update.getQuantity();
        this.name = update.getName();
        return this;
    }

    public void validateMatchedCompany(Company company) {
        if (!Objects.equals(id, company.getId()) || !Objects.equals(hubId, company.getHubId())) {
            throw new DomainException("본인 업체의 상품이 아닙니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getHubId() {
        return hubId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public String getName() {
        return name;
    }
}
