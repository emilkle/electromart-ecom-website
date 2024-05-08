package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The OrderItem class represents the order item entities that are stored in the database.
 */
@Entity
@Table(name="Orderitem")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("productID")
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="product_ID")
    private Product productID;

    private int itemQuantity;
    private float itemSubtotal;

    public OrderItem(){}

    public OrderItem(OrderItemId id, Product product, int itemQuantity, float itemSubtotal) {
        this.id = id;
        this.productID = product;
        this.itemQuantity = itemQuantity;
        this.itemSubtotal = itemSubtotal;
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Product getProduct() {
        return productID;
    }

    public void setProduct(Product product) {
        this.productID = product;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public float getItemSubtotal() {
        return itemSubtotal;
    }

    public void setItemSubtotal(float itemSubtotal) {
        this.itemSubtotal = itemSubtotal;
    }
}