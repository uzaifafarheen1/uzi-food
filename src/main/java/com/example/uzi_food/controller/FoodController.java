package com.example.uzi_food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import com.example.uzi_food.repository.*;
import com.example.uzi_food.model.*;

@Controller
public class FoodController {

   
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;   // ✅ NEW

    public FoodController(OrderRepository orderRepo,
                          OrderItemRepository orderItemRepo) {
    
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/order")
    public String order() {
        return "order";
    }
    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "orders";
    }

    /* ✅ SAME URL — EXTENDED ONLY */
    @PostMapping("/confirmOrder")
    @ResponseBody
    public String confirmOrder(@RequestParam int totalPrice,
                               @RequestBody Map<String, Integer> items) {

        Order o = new Order();
        o.setTotalPrice(totalPrice);
        o.setOrderTime(LocalDateTime.now());   // ✅ Store Date & Time

        Order savedOrder = orderRepo.save(o);

        for (String foodName : items.keySet()) {

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);              // ✅ FIX
            item.setFoodName(foodName);
            item.setQuantity(items.get(foodName));

            orderItemRepo.save(item);
        }

        return "Order Confirmed. Total ₹" + totalPrice;
    }
}
