package com.sparta.logistics.order.domain.order;


public class Product {

    private Long id;
    private String name;
    private Integer quantity;
    private Long supplyCompany;

    public Product(Long id, String name, Integer quantity, Long supplyCompany) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.supplyCompany = supplyCompany;
    }

    public boolean isEnoughQuantity(Integer requireQuantity) {
        return this.quantity >= requireQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getSupplyCompany() {
        return supplyCompany;
    }
}
