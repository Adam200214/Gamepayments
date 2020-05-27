package com.teamgames.gamepayments.product;

import lombok.Getter;

@Getter
public class Sale {
    private String saleType;
    private double discountAmount;
    private int discountPercentage;
}
