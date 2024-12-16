package com.sparta.logistics.order.domain.order.vo;

public class ProductForChangeQuantity {

    private Long productId;
    private Integer changedCount;
    private boolean isDecrease;
    private Long changedBy;

    public ProductForChangeQuantity(
        Long productId, Integer changedCount, boolean isDecrease, Long changedBy
    ) {
        this.productId = productId;
        this.changedCount = changedCount;
        this.isDecrease = isDecrease;
        this.changedBy = changedBy;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getChangedCount() {
        return changedCount;
    }

    public boolean isDecrease() {
        return isDecrease;
    }

    public Long getChangedBy() {
        return changedBy;
    }
}
