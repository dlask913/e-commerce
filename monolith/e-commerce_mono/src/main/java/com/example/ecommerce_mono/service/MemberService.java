package com.example.ecommerce_mono.service;

import com.example.ecommerce_mono.entity.Member;
import com.example.ecommerce_mono.entity.Order;
import com.example.ecommerce_mono.repository.MemberRepository;
import com.example.ecommerce_mono.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public void saveMember(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUserId(member.getUserId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }
    public Member findByUserId(String userId){
        Member findMember = memberRepository.findByUserId(userId);
        return findMember;
    }

    public List<Order> getOrders(String userId) {
        List<Order> result = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order: orders) {
            if (order.getMember().getUserId().equals(userId)) {
                System.out.println(order.toString());
                result.add(order);
            }
        }
        return result;
    }
}
