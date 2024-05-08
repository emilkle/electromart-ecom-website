package com.electromart.electromart.dto;


/**
 * Data Transfer Object for the (DTO) for orderItem data.
 * It is used to encapsulate the orderItem data that is transferred
 between the OrderItemService and the OrderItemController.
 * This ensures that the service layer are not directly interacting with the database.
 */
public class OrderItemDTO {
    private Long orderID;
    private Long productID;
    private int itemQuantity;
    private float itemSubtotal;

    public OrderItemDTO() {}

    public OrderItemDTO(Long orderId, Long productId, int itemQuantity, float itemSubtotal) {
        this.orderID = orderId;
        this.productID = productId;
        this.itemQuantity = itemQuantity;
        this.itemSubtotal = itemSubtotal;
    }

    public Long getOrderId() {
        return orderID;
    }

    public void setOrderId(Long orderId) {
        this.orderID = orderId;
    }

    public Long getProductId() {
        return productID;
    }

    public void setProductId(Long productId) {
        this.productID = productId;
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
