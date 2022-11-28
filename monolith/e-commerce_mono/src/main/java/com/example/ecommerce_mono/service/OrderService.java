package com.example.ecommerce_mono.service;

import com.example.ecommerce_mono.dto.OrderDto;
import com.example.ecommerce_mono.entity.Catalog;
import com.example.ecommerce_mono.entity.Member;
import com.example.ecommerce_mono.entity.Order;
import com.example.ecommerce_mono.repository.CatalogRepository;
import com.example.ecommerce_mono.repository.MemberRepository;
import com.example.ecommerce_mono.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final CatalogRepository catalogRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String userId){
        Catalog catalog = catalogRepository.findByProductName(orderDto.getProductName());

        Member member = memberRepository.findByUserId(userId);

//        List<OrderCatalog> orderCatalogList = new ArrayList<>();
//        OrderCatalog orderCatalog = OrderCatalog.createOrderCatalog(catalog, orderDto.getQty());
//        orderCatalogRepository.save(orderCatalog);

//        orderCatalogList.add(orderCatalog);

        Order order = Order.createOrder(member, orderDto);
        orderRepository.save(order);

        return order.getId();
    }
}
