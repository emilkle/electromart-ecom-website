package com.electromart.electromart.entity;

public class CartItem {
    private Product product;
    private int cartAmount;

    public CartItem(Product productItem, int cartAmount) {
        product = productItem;
        this.cartAmount = cartAmount;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(int cartAmount) {
        this.cartAmount = cartAmount;
    }

    public long getCartItemID() {return product.getProductId();}
    //public long getCartItemID(Product product) {
    //    return product.getProductId();
    //}


}
