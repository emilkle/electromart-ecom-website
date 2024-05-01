package com.electromart.electromart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="inventory")
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
