package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The OrderItem class represents the order item entities that are stored in the database.
 */
@Entity
@Table(name="OrderItem")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("productID")
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="product_ID")
    private Product productID;

    private String itemQuantity;
    private String itemSubtotal;

    public OrderItem(){}

    public OrderItem(OrderItemId id, Product product, String itemQuantity, String itemSubtotal) {
        this.id = id;
        this.productID = product;
        this.itemQuantity = itemQuantity;
        this.itemSubtotal = itemSubtotal;
    }

    public Product getProduct() {
        return productID;
    }

    public void setProduct(Product product) {
        this.productID = product;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemSubtotal() {
        return itemSubtotal;
    }

    public void setItemSubtotal(String itemSubtotal) {
        this.itemSubtotal = itemSubtotal;
    }
}