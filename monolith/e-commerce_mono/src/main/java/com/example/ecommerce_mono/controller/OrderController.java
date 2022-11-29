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
    private final CatalogService catalogService;

    @GetMapping(value = "/order-service/{userId}/{productName}")
    public String orderForm(@PathVariable("userId") String userId,@PathVariable("productName") String productName, Model model){
        OrderDto orderDto = new OrderDto();
        Catalog catalog = catalogService.findByProductName(productName);
        orderDto.setUserId(userId);
        orderDto.setProductName(catalog.getProductName());
        orderDto.setUnitPrice(catalog.getUnitPrice());
        orderDto.setStock(catalog.getStock());

        model.addAttribute("orderDto", orderDto);
        return "orders/orderForm";
    }

    @PostMapping(value = "/order-service/{userId}/{productName}")
    public String order (OrderDto orderDto, @PathVariable(value = "userId") String userId, @PathVariable(value = "productName") String productName, Model model){
        Catalog catalog = catalogService.findByProductName(productName);
        orderDto.setProductName(catalog.getProductName());
        orderDto.setUnitPrice(catalog.getUnitPrice());
        orderDto.setStock(catalog.getStock());
        orderService.order(orderDto, userId);
        model.addAttribute("userId", userId);
        return "orders/main";
    }
}
