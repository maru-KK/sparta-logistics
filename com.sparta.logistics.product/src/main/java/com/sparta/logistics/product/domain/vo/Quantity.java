package com.sparta.logistics.product.domain.vo;

public class Quantity {

    private int quantity;

    private Quantity(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("수량은 0이 될 수 없음");
        }
    }

    public static Quantity valueOf(int quantity) {
        return new Quantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }
}
