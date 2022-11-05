package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    @PostMapping("/{userId}/orders")
//    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
//                                                     @RequestBody RequestOrder orderDetails) {
//        log.info("Before add orders data");
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
//        orderDto.setUserId(userId);
//        /* Jpa */
//        OrderDto createdOrder = orderService.createOrder(orderDto);
//        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);
//
//        /* Kafka */
////        orderDto.setOrderId(UUID.randomUUID().toString());
////        orderDto.setTotalPrice(orderDetails.getQty()*orderDetails.getUnitPrice());
//
//        /* send this order to the kafka */
//        kafkaProducer.send("example-catalog-topic",orderDto);
////        orderProducer.send("orders",orderDto);
//
////        ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);
//
//        log.info("After add orders data");
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
//    }

    @GetMapping("/{userId}/orders")
    public String getOrder(@PathVariable("userId") String userId, Model model) throws Exception{
//        log.info("Before retrieve orders data");
        Iterable<Order> orderList = orderService.getOrdersByUserId(userId);
        List<Order> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v, Order.class));
        });

        model.addAttribute("orderList", orderList);
        return "orders/orderList";
    }

    @GetMapping("/{userId}/new")
    public String orderForm(Model model) {
        model.addAttribute("orderDto", new OrderDto());
        return "orders/orderForm";
    }

    @PostMapping("/{userId}/new")
    public String newOrder(OrderDto orderDto, Model model) {
        Order order = Order.createOrder(orderDto);
        orderService.saveOrder(order);
        return "redirect:/";
    }
}
