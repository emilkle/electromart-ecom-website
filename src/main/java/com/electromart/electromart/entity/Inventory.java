package com.electromart.electromart.entity;

import jakarta.persistence.*;

/**
 * The Inventory class represents the inventory entities that are stored in the database.
 */
@Entity
@Table(name="Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventory_ID")
    private Long inventoryID;

    @OneToOne
    @JoinColumn(name = "product_ID")
    private Product product;

    private int quantity;
    private String lastRestockDate;

    public Inventory() {}

    public Inventory(Product product, int quantity, String lastRestockDate) {
        this.product = product;
        this.quantity = quantity;
        this.lastRestockDate = lastRestockDate;
    }

    public Long getId() {
        return inventoryID;
    }

    public void setId(Long id) {
        this.inventoryID = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(String lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }
}