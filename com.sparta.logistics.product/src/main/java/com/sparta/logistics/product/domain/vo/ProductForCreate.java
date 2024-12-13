package com.sparta.logistics.product.domain.vo;

public class ProductForCreate {

    private final String name;
    private final Integer quantity;
    private final Long createdBy;

    public ProductForCreate(String name, Integer quantity, Long createdBy) {
        validateQuantity(quantity);
        this.name = name;
        this.quantity = quantity;
        this.createdBy = createdBy;
    }

    private void validateQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("수량은 음수가 될 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getCreatedBy() {
        return createdBy;
    }
}
