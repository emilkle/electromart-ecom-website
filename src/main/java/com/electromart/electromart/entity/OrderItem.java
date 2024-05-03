package com.electromart.electromart.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

/*
@Entity
@Table(name="Orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="order_ID")
    private int orderID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="product_ID")
    @OneToOne(cascade=CascadeType.ALL)
    private Product productID;

    private String itemQuantity;
    private String itemSubtotal;

    public OrderItem(){}

    public OrderItem(String itemQuantity, String itemSubtotal) {
        this.itemQuantity = itemQuantity;
        this.itemSubtotal = itemSubtotal;
    }


    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
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