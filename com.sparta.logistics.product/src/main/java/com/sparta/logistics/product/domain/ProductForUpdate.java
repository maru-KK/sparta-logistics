package com.sparta.logistics.product.domain;

import com.sparta.logistics.product.domain.vo.Quantity;

public class ProductForUpdate {

    private Long id;
    private String name;
    private Quantity quantity;

    public ProductForUpdate(Long id, String name, Quantity quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
