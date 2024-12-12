package com.sparta.logistics.order.domain.order;


import java.util.Objects;

public class Product {

    public static final Product ERROR_PRODUCT;

    static {
        ERROR_PRODUCT = new Product(-1L, "error", -1, -1L, -1L);
    }

    private Long id;
    private String name;
    private Integer quantity;
    private Long supplyCompany;
    private Long supplyHub;

    public Product(Long id, String name, Integer quantity, Long supplyCompany, Long supplyHub) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.supplyCompany = supplyCompany;
        this.supplyHub = supplyHub;
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

    public Long getSupplyHub() {
        return supplyHub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name)
            && Objects.equals(quantity, product.quantity) && Objects.equals(
            supplyCompany, product.supplyCompany) && Objects.equals(supplyHub,
            product.supplyHub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, supplyCompany, supplyHub);
    }
}
