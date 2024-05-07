package com.electromart.electromart.repository;

import com.electromart.electromart.entity.OrderItem;
import com.electromart.electromart.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
