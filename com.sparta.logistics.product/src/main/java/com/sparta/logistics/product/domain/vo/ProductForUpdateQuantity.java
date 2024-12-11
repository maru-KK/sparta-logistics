package com.sparta.logistics.product.domain.vo;


public class ProductForUpdateQuantity {

    private Long id;
    private Quantity changedCount;
    private boolean isDecrease;

    public ProductForUpdateQuantity(Long id, Quantity changedCount, boolean isDecrease) {
        this.id = id;
        this.changedCount = changedCount;
        this.isDecrease = isDecrease;
    }

    public Long getId() {
        return id;
    }

    public int getChangedCount() {
        return changedCount.getQuantity();
    }

    public boolean isDecrease() {
        return isDecrease;
    }
}
