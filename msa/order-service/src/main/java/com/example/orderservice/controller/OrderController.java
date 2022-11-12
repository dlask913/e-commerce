package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
//    private final OrderProducer orderProducer;


    @GetMapping(value = "order-service/orders/{userId}")
    public String catalogList(@PathVariable("userId") String userId, Model model) {
        List<OrderDto> orderDto = orderService.getCatalogs();
        for (OrderDto order :
                orderDto) {
            order.setUserId(userId);
        }
        model.addAttribute("orderDto", orderDto);
        return "orders/catalogList";
    }

    @GetMapping(value = "order-service/orders/{userId}/{productId}")
    public String orderForm(@PathVariable("userId") String userId, @PathVariable("productId") String productId, Model model) {
        OrderDto orderDto = orderService.getOrderByProductId(productId);
        orderDto.setUserId(userId);
        model.addAttribute("orderDto", orderDto);
        return "orders/orderForm";
    }

    @PostMapping(value = "order-service/orders/{userId}/{productId}")
    public String newOrder(@PathVariable("userId") String userId,@PathVariable("productId") String productId, OrderDto orderDto) {
        OrderDto temp = orderService.getOrderByProductId(productId);
        temp.setUserId(userId);
        temp.setQty(orderDto.getQty());
        temp.setTotalPrice(temp.getUnitPrice()* temp.getQty());

        Order order = Order.createOrder(temp);
        orderService.saveOrder(order);

        kafkaProducer.send("example-catalog-topic",orderDto);

        return "main";
    }

    @GetMapping(value = "order-service/{userId}/orders")
    public ResponseEntity<List<OrderDto>> getOrder(@PathVariable("userId") String userId, Model model) throws Exception{
        Iterable<Order> orderList = orderService.getOrdersByUserId(userId);
        List<OrderDto> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v, OrderDto.class));
        });
        model.addAttribute("orderList", orderList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
