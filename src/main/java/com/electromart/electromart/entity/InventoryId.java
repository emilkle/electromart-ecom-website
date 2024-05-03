package com.electromart.electromart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InventoryId implements Serializable {
    @Column(name = "inventory_ID")
    private long inventoryID;

    @Column(name = "product_ID")
    private long productID;
}
