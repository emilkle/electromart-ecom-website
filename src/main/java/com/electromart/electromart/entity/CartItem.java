package com.electromart.electromart.entity;

public class CartItem {
    private Product product;
    private int productAmount;

    public CartItem(Product productItem, int cartAmount) {
        product = productItem;
        productAmount =cartAmount;
    }


}
