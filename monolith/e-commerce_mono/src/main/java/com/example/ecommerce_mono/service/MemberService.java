package com.example.ecommerce_mono.service;

import com.example.ecommerce_mono.entity.Member;
import com.example.ecommerce_mono.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

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

}
