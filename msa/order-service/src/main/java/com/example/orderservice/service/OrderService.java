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

    public List<OrderDto> getCatalog() {

        Iterable<ResponseCatalog> catalogList = catalogServiceClient.getOrders();
        List<OrderDto> result = new ArrayList<>();
        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, OrderDto.class));
        });
        return result;
    }

}
