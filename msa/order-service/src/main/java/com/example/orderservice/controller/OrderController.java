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
    private final OrderProducer orderProducer;



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

    @GetMapping(value = "order-service/{userId}/new")
    public String orderForm(@PathVariable("userId") String userId, Model model) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(userId);
        model.addAttribute("orderDto", orderDto);
        return "orders/orderForm";
    }

    @PostMapping(value = "order-service/{userId}/new")
    public String newOrderForm(@PathVariable("userId") String userId, OrderDto orderDto) {
        System.out.println("userId: "+userId);
        orderDto.setTotalPrice(orderDto.getUnitPrice()* orderDto.getQty());

        //JPA
        Order order = Order.createOrder(orderDto);
        orderService.saveOrder(order);
//        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

        kafkaProducer.send("example-catalog-topic",orderDto);

        return "redirect:/order-service/{userId}/new";
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
