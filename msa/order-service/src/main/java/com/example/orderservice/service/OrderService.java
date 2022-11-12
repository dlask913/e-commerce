package com.example.orderservice.service;

import com.example.orderservice.client.CatalogServiceClient;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService{

    private final OrderRepository orderRepository;
    private final CatalogServiceClient catalogServiceClient;

    public Iterable<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<OrderDto> getCatalogs() {
        Iterable<ResponseCatalog> catalogList = catalogServiceClient.getCatalogs();
        List<OrderDto> result = new ArrayList<>();
        catalogList.forEach(v -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setProductId(v.getProductId());
            orderDto.setUnitPrice(v.getUnitPrice());
            orderDto.setStock(v.getQty());
            result.add(orderDto);
        });
        return result;
    }

    public OrderDto getOrderByProductId(String productId) {
        Iterable<ResponseCatalog> catalogList = catalogServiceClient.getCatalogs();
        List<OrderDto> orderList = new ArrayList<>();
        catalogList.forEach(v ->{
            OrderDto orderDto = new OrderDto();
            orderDto.setProductId(v.getProductId());
            orderDto.setUnitPrice(v.getUnitPrice());
            System.out.println("orderDto: "+orderDto.toString());
            System.out.println("v: "+v.toString());
            orderDto.setStock(v.getQty());
            orderList.add(orderDto);
        });
        for (OrderDto order: orderList) {
            if (order.getProductId().equals(productId)) {
                return order;
            }
        }
        return null;
    }

}
