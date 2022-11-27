package com.example.ecommerce_mono.entity;

import com.example.ecommerce_mono.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@Table(name = "member")
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String userName;
    private String pwd;
//    private String orderId;

    public static Member createMember(MemberFormDto memberFormDto) {
        Member member = new Member();
        member.setUserId(memberFormDto.getUserId());
        member.setUserName(memberFormDto.getUserName());
        member.setPwd(memberFormDto.getPwd());
        return member;
    }
}
