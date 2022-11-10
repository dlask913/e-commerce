package com.example.memberservice.service;

import com.example.memberservice.client.OrderServiceClient;
import com.example.memberservice.dto.MemberFormDto;
import com.example.memberservice.entity.Member;
import com.example.memberservice.repository.MemberRepository;
import com.example.memberservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderServiceClient orderServiceClient;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUserId(member.getUserId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member findByUserId(String userId) {
        Member findMember = memberRepository.findByUserId(userId);
        return findMember;
    }

    public MemberFormDto getUserByUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);

//        if (member == null)
//            throw new UsernameNotFoundException("User not found");

        MemberFormDto memberFormDto = new ModelMapper().map(member, MemberFormDto.class);

        /* ErrorDecoder */
        log.info("Before called orders microservice");
        List<ResponseOrder> ordersList =orderServiceClient.getOrders(userId);
        memberFormDto.setOrders(ordersList);
        log.info("After called orders microservice");

        return memberFormDto;
    }

    /* 없는 유저 예외처리하기 */

}
