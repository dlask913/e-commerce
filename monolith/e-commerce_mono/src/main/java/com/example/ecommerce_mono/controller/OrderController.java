package com.example.ecommerce_mono.controller;

import com.example.ecommerce_mono.dto.CatalogDto;
import com.example.ecommerce_mono.dto.OrderDto;
import com.example.ecommerce_mono.entity.Catalog;
import com.example.ecommerce_mono.service.CatalogService;
import com.example.ecommerce_mono.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = "/orders/{userId}")
    public String orderForm(@PathVariable("userId") String userId, Model model){
        OrderDto orderDto = new OrderDto();
        model.addAttribute("orderDto", orderDto);
        return "orders/orderForm";
    }

    @PostMapping(value = "/orders/{userId}")
    public @ResponseBody ResponseEntity order (OrderDto orderDto, @PathVariable(value = "userId") String userId){
        Long orderId;
        try {
            orderId = orderService.order(orderDto, userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
}
