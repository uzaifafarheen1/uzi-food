	package com.example.uzi_food.repository;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import com.example.uzi_food	.model.Order;
	
	public interface OrderRepository extends JpaRepository<Order, Integer> {
	}
