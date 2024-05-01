package com.electromart.electromart.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

@Entity
@Table(name="orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="order_id")
    private String orderID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="product_id")
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
