package com.example.memberservice.entity;

import com.example.memberservice.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String pwd;
    @Column
    private Date createdAt;
    @Column(unique = true)
    private String userId;

    public static Member createMember(MemberFormDto memberFormDto) {
        Member member = new Member();
        member.setUserId(memberFormDto.getUserId());
        member.setName(memberFormDto.getName());
        member.setPwd(memberFormDto.getPwd());
        return member;
    }
}


