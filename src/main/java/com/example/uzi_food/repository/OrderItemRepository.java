package com.example.uzi_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.uzi_food.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
