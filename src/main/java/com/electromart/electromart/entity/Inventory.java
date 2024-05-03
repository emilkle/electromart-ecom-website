package com.electromart.electromart.entity;

import jakarta.persistence.*;

/*
@Entity
@Table(name="inventorys")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryID;

    @Id
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productID;

    private int quantity;
    private String lastRestockDate;

    public Inventory () {}

    public Inventory (Product productID, int quantity, String lastRestockDate) {
        this.productID = productID;
        this.quantity = quantity;
        this.lastRestockDate = lastRestockDate;
    }

    public long getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(long inventoryID) {
        this.inventoryID = inventoryID;
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
 */

@Entity
@Table(name="Inventory")
public class Inventory {
    @EmbeddedId
    private InventoryId id;

    @MapsId("productID")
    @OneToOne
    @JoinColumn(name = "product_ID")
    private Product product;

    private int quantity;
    private String lastRestockDate;

    public Inventory() {}

    public Inventory(InventoryId id, Product product, int quantity, String lastRestockDate) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.lastRestockDate = lastRestockDate;
    }

    public InventoryId getId() {
        return id;
    }

    public void setId(InventoryId id) {
        this.id = id;
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