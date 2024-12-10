package com.sparta.logistics.product.domain;

import com.sparta.logistics.product.domain.vo.Quantity;

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

    public Long getId() {
        return id;
    }

    public Long getHubId() {
        return hubId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
