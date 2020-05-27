package com.teamgames.gamepayments.product;

public class Sale {
    private String saleType;

    public String getSaleType() {
        return saleType;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    private double discountAmount;
    private int discountPercentage;
}
